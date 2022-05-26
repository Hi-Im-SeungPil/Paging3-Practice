package com.example.paging3practice

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import com.google.gson.JsonArray

class DataSource(
    private val service: RetrofitService
) : PagingSource<Int,DownloadUrl>() {
    override fun getRefreshKey(state: PagingState<Int, DownloadUrl>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DownloadUrl> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.requestImages(nextPageNumber)
            val lst = ArrayList<DownloadUrl>()

            for (i in 0 until (response.body() ?: JsonArray()).size()) {
                lst.add(Gson().fromJson(response.body()!![i], DownloadUrl::class.java))
            }

            LoadResult.Page(
                data = lst,
                prevKey = null,
                nextKey = if (lst.count() >= 20) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}