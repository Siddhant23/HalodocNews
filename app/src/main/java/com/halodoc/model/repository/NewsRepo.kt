package com.halodoc.model.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.halodoc.model.api.ApiBuilder
import com.halodoc.model.data.Hits
import com.halodoc.model.data.NewsResponse
import com.halodoc.utils.AppConstant
import com.halodoc.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepo(var application: Application) {

    private var responseLiveData = MutableLiveData<ArrayList<Hits>>()

    internal fun getResponse(): MutableLiveData<ArrayList<Hits>> {

        return when {
            Util.isNetworkAvailable(application) -> {
                fetchfromRemote()
            }
            else -> {
                sendInternetError()
            }
        }
    }

    private fun sendInternetError(): MutableLiveData<ArrayList<Hits>> {
        responseLiveData.value = null
        Toast.makeText(application, AppConstant.ERR_INTERNET_MSG, Toast.LENGTH_LONG).show()
        return responseLiveData
    }


    private fun fetchfromRemote(): MutableLiveData<ArrayList<Hits>> {

        val data: MutableMap<String, String> = HashMap()
        data["query"] = "sports"
        data["page"] = "0"

        val call: Call<NewsResponse> = ApiBuilder.apiService.getNews(data)
        call.enqueue(object : Callback<NewsResponse> {

            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val articleResponse = response.body()
                    responseLiveData.value = (articleResponse?.hits)
                } else {
                    responseLiveData.value = null
                    Toast.makeText(application, AppConstant.ERR_PARSING, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                responseLiveData.value = null
                Toast.makeText(application, t.message, Toast.LENGTH_LONG).show()
            }
        })
        return responseLiveData
    }

}