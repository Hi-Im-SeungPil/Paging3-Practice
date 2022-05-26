package com.example.paging3practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelg() : ViewModel() {

//    private val _data = MutableLiveData<List<String>>()
//    val data: LiveData<List<String>> get() = _data

    val retrofitClient = Retrofit.Builder().baseUrl("https://picsum.photos")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofitClient.create(RetrofitService::class.java)

    fun getData1(): Flow<PagingData<DownloadUrl>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {DataSource(service = service)}
        ).flow
    }
}