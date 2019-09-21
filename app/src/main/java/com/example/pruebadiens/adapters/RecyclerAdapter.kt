package com.example.pruebadiens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadiens.R
import com.example.pruebadiens.models.Result
import com.example.pruebadiens.util.Constants
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var movies: MutableList<Result> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(movies: MutableList<Result>, context: Context) {
        this.movies = movies
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        val realName = view.findViewById(R.id.tvRealName) as TextView
        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val avatar = view.findViewById(R.id.ivAvatar) as ImageView

        fun bind(movie: Result, context: Context) {
            superheroName.text = movie.title
            realName.text = movie.original_title
            publisher.text = movie.release_date
            avatar.loadUrl(Constants.URL_IMAGES+movie.poster_path)
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context,
                    movie.overview,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}