package com.example.paging3practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3practice.databinding.ActivityMainBinding
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = MainRecyclerViewAdapter()
    private val viewModel: ViewModelg by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvMain.adapter = adapter
        afterOnCreate()
    }

    private fun afterOnCreate() {
        lifecycleScope.launchWhenCreated {
            viewModel.getData1().collect(){
                adapter.submitData(it)
            }
        }
    }
}

interface RetrofitService {
    @GET("/v2/list")
    suspend fun requestImages(
        @Query("page") page: Int?,
        @Query("limit") limit: Int? = 20
    ): Response<JsonArray>
}