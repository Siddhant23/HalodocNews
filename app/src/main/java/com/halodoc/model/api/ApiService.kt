package com.halodoc.model.api

import com.halodoc.model.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("search?")
    fun getNews(@QueryMap options: Map<String, String>): Call<NewsResponse>

}