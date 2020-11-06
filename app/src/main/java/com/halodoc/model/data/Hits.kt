package com.halodoc.model.data

import com.google.gson.annotations.SerializedName

data class Hits(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("author")
    val author: String? = null
)