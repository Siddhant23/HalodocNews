package com.halodoc.view

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.halodoc.R
import com.halodoc.model.data.Hits
import com.halodoc.utils.Util.Companion.inflate

class NewsAdapter(
    private val items: List<Hits>,
    private val clickListener: ItemClickListener?
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflatedView = parent.inflate(R.layout.item_news, false)
        return NewsViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItems(items[position], clickListener)
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal fun bindItems(hits: Hits, clickListener: ItemClickListener?) {
            val tvTitle = itemView.findViewById(R.id.tvTitle) as TextView
            val tvAuthor = itemView.findViewById(R.id.tvAuthor) as TextView
            val mCardLayout = itemView.findViewById(R.id.mCardLayout) as LinearLayout

            tvTitle.text = hits.title
            tvAuthor.text = hits.author

            mCardLayout.setOnClickListener { clickListener?.onItemClick(hits.url) }
        }
    }
}