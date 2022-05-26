package com.example.paging3practice

import com.google.gson.annotations.SerializedName

data class DownloadUrl(
    val id: Int,
    @SerializedName("download_url")
    val downloadUrl: String
)
