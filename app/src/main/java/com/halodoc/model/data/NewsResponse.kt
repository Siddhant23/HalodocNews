package com.halodoc.model.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("nbPages")
    val nbPages: String? = null,

    @SerializedName("hitsPerPage")
    val hitsPerPage: String? = null,

    @SerializedName("hits")
    val hits: ArrayList<Hits>? = null

)