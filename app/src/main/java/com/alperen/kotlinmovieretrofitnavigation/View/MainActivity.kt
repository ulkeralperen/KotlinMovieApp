package com.alperen.kotlinmovieretrofitnavigation.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoModel
import com.alperen.kotlinmovieretrofitnavigation.R
import com.alperen.kotlinmovieretrofitnavigation.Service.movieAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://api.themoviedb.org/3/movie/popular?api_key=e878b7d0eb62bddf803d8fba9cfc8703&language=en-US&page=1


        //loadData()
    }




}