package com.alperen.kotlinmovieretrofitnavigation.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoDetailModel
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoModel
import com.alperen.kotlinmovieretrofitnavigation.R
import com.alperen.kotlinmovieretrofitnavigation.Service.movieAPI
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.imageView
import kotlinx.android.synthetic.main.fragment_second.textViewMovieName
import kotlinx.android.synthetic.main.row_layout.*
import kotlinx.android.synthetic.main.row_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class SecondFragment : Fragment() {
    private val BASE_URL="https://api.themoviedb.org/3/movie/"
    private var movieModels:getMovieInfoDetailModel?=null
    private var compositeDisposable: CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable= CompositeDisposable()
        loadData()
    }

    private fun loadData(){

        var id:String
        arguments?.let {
            id=SecondFragmentArgs.fromBundle(it).id
            println("ID: ${id}")

            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(movieAPI::class.java)

            compositeDisposable?.add(retrofit.getDataDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

        }

    }

    private fun handleResponse(movieList:getMovieInfoDetailModel){
        movieModels=movieList
        movieModels?.let {
            textViewMovieName.text=it.title
            val base_Image_url="https://image.tmdb.org/t/p/w500/${it.poster_path}"

            Picasso.get().load(base_Image_url)
                .resize(200,200)
                .centerCrop()
                .into(imageView)

            textViewGenres.text="Genre: ${it.genres[0].name}"
            textViewLanguage.text="Language: ${it.original_language}"
            textViewPopularity.text="Popularity: ${it.popularity}"
            textViewProductionCountries.text="Production Country: ${it.production_countries[0].name}"
            textViewSpokenLangueage.text="Spoken Language: ${it.spoken_languages[0].english_name}"
            textViewTagline.text="TagLine: ${it.tagline}"
            textViewOverView.text="Overview: ${it.overview}"
            textViewVoteAverage.text="Average: ${it.vote_average.toString()}"
            textViewHomepage.text="Homepage: ${it.homepage}"

        }
    }

}