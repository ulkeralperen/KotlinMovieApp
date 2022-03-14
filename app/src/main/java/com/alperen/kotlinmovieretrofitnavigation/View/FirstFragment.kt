package com.alperen.kotlinmovieretrofitnavigation.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alperen.kotlinmovieretrofitnavigation.Adapter.RecyclerViewAdapter
import com.alperen.kotlinmovieretrofitnavigation.Model.Result
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoModel
import com.alperen.kotlinmovieretrofitnavigation.R
import com.alperen.kotlinmovieretrofitnavigation.Service.movieAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_first.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class FirstFragment : Fragment() {
    private val BASE_URL="https://api.themoviedb.org/3/movie/"
    private var movieModels: getMovieInfoModel?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null
    private var compositeDisposable: CompositeDisposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable= CompositeDisposable()
        var layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(context)
        recyclerview.layoutManager=layoutManager
      loadData()

    }

        private fun loadData(){
            val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(movieAPI::class.java)

            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

        }

    private fun handleResponse(movieList:getMovieInfoModel){
        movieModels=movieList
        movieModels?.let {
            recyclerViewAdapter= RecyclerViewAdapter(it,this)
            recyclerview.adapter=recyclerViewAdapter
        }
    }

     fun onItemClick(movieList: Result,view: View){
        val action=FirstFragmentDirections.actionFirstFragmentToSecondFragment(movieList.id)
         Navigation.findNavController(view).navigate(action)
    }


}