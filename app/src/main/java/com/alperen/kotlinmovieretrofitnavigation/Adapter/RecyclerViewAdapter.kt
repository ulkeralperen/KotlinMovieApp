package com.alperen.kotlinmovieretrofitnavigation.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.kotlinmovieretrofitnavigation.Model.Result
import com.alperen.kotlinmovieretrofitnavigation.Model.getMovieInfoModel
import com.alperen.kotlinmovieretrofitnavigation.R
import com.alperen.kotlinmovieretrofitnavigation.View.FirstFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val movieList:getMovieInfoModel,private val listener:FirstFragment):RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(movieList:Result)
    }

    class RowHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bind(movieList: Result,position: Int,listener: FirstFragment){
            itemView.setOnClickListener {
                listener.onItemClick(movieList,it)
            }
       val base_Image_url="https://image.tmdb.org/t/p/w500/${movieList.poster_path}"

         Picasso.get().load(base_Image_url)
               .resize(180,180)
                .centerCrop()
                .into(itemView.imageView)

            itemView.textViewOverview.text=movieList.overview
            itemView.textViewMovieName.text=movieList.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(movieList.results[position],position,listener)
    }

    override fun getItemCount(): Int {
        return movieList.results.count()
    }
}