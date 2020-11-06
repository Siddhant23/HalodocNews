package com.halodoc.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.halodoc.model.data.Hits
import com.halodoc.model.repository.NewsRepo

class NewsVM(application: Application) : AndroidViewModel(application) {
    internal var newsListLiveData = MutableLiveData<ArrayList<Hits>>()

    internal fun fetchNewsList() {
        newsListLiveData = NewsRepo(getApplication()).getResponse()
    }
}