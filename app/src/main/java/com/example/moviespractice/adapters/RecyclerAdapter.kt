package com.example.moviespractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.moviespractice.R
import com.example.moviespractice.api.ApiServiceInterface
import com.example.moviespractice.models.Response
import com.example.moviespractice.models.Result
import com.example.moviespractice.util.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
            avatar.loadUrl(Constants.URL_IMAGES + movie.poster_path)
            itemView.setOnClickListener(View.OnClickListener {

                //Inflate the dialog with custom view
                val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(context)
                    .setView(mDialogView)
                //show dialog
                val mAlertDialog = mBuilder.show()
                //login button click of custom layout
                mDialogView.btnaceptar.setOnClickListener {
                    //dismiss dialog
                    mAlertDialog.dismiss()
                }

                mDialogView.youtube_player_view.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId = getVideo(movie.id.toString())
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })

                mDialogView.txttitle.setText(movie.original_title)
                mDialogView.txtdetail.setText(movie.overview)

            })
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getVideo(idMovie: String): String {

            var resp: String = ""
            doAsync {
                val call = getRetrofit().create(ApiServiceInterface::class.java)
                    .getVideo("3/movie/" + idMovie + Constants.VIDEO).execute()
                val respuesta = call.body() as Response
                uiThread {
                    resp = respuesta.results.get(0).key
                }
            }
            return resp
        }
    }


}