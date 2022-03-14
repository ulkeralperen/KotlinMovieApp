package com.alperen.kotlinmovieretrofitnavigation.Service

import android.database.Observable
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoDetailModel
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface movieAPI {

    //https://api.themoviedb.org/3/movie/popular?api_key=e878b7d0eb62bddf803d8fba9cfc8703&language=en-US&page=1
    @GET("popular?api_key=e878b7d0eb62bddf803d8fba9cfc8703&language=en-US&page=1")
    fun getData():io.reactivex.Observable<getMovieInfoModel>

    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=e878b7d0eb62bddf803d8fba9cfc8703>&language=en-US
    //https://api.themoviedb.org/3/movie/634649?api_key=e878b7d0eb62bddf803d8fba9cfc8703&language=en-US
   @GET("{id}?api_key=e878b7d0eb62bddf803d8fba9cfc8703&language=en-US")
    fun getDataDetail(@Path("id")id:String):io.reactivex.Observable<getMovieInfoDetailModel>




}