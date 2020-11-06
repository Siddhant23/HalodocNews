package com.halodoc.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.halodoc.R
import com.halodoc.model.data.Hits
import com.halodoc.utils.AppConstant
import com.halodoc.viewModel.NewsVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setLoader(true)
        val (list, adapter) = initAdapter()
        initVM(list, adapter)
    }

    private fun initView() {
        supportActionBar?.title = getString(R.string.titleName)
    }

    private fun initAdapter(): Pair<ArrayList<Hits>, NewsAdapter> {
        val list = ArrayList<Hits>()
        val adapter = NewsAdapter(list, object : ItemClickListener {
            override fun onItemClick(obj: Any?) {
                val url = obj as String
                startActivity(
                    Intent(this@MainActivity, WebViewActivity::class.java)
                        .putExtra(AppConstant.KEY_INTENT_DATA, url)
                )
            }
        })
        rvNews.adapter = adapter
        return Pair(list, adapter)
    }

    private fun initVM(list: ArrayList<Hits>, adapter: NewsAdapter) {
        val viewModel = ViewModelProviders.of(this).get(NewsVM::class.java)
        viewModel.fetchNewsList()
        updateNewsList(viewModel, list, adapter)
    }

    private fun updateNewsList(
        viewModel: NewsVM,
        list: ArrayList<Hits>,
        adapter: NewsAdapter
    ) {
        viewModel.newsListLiveData.observe(this, Observer {
            setLoader(false)
            it?.let {
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setLoader(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}