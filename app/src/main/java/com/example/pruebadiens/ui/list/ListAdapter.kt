package com.example.pruebadiens.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadiens.R
import com.example.pruebadiens.models.Movie
import com.squareup.picasso.Picasso

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var movies: MutableList<Movie> = ArrayList()
    lateinit var context: Context
    fun RecyclerAdapter(movies: MutableList<Movie>, context: Context) {
        this.movies = movies
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        val realName = view.findViewById(R.id.tvRealName) as TextView
        val publisher = view.findViewById(R.id.tvPublisher) as TextView
        val avatar = view.findViewById(R.id.ivAvatar) as ImageView

        fun bind(movie: Movie, context: Context) {
            superheroName.text = movie.original_title
            realName.text = movie.overview
            publisher.text = movie.release_date
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context,
                    movie.title,
                    Toast.LENGTH_SHORT
                ).show()
            })
            avatar.loadUrl(movie.poster_path)
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }

}