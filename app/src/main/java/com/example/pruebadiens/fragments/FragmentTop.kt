package com.example.pruebadiens.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadiens.R
import com.example.pruebadiens.adapters.RecyclerAdapter
import com.example.pruebadiens.api.ApiServiceInterface
import com.example.pruebadiens.models.Genre
import com.example.pruebadiens.models.Movie
import com.example.pruebadiens.models.Result
import com.example.pruebadiens.util.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentTop : Fragment() {


    lateinit var mProgressBar: ProgressBar
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSpinner: Spinner

    val mAdapter: RecyclerAdapter =
        RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(
            R.layout.fragment_list,
            container, false
        )


        getTopMovies(view)

        // Inflate the layout for this fragment
        return view
    }


    private fun getRetrofit(): Retrofit {

        val cache = context?.cacheDir?.let { Cache(it, 10485760) }

        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client((okHttpClient))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun getTopMovies(view: View) {
        doAsync {
            val call = getRetrofit().create(ApiServiceInterface::class.java)
                .getAllMovies(Constants.TOP_RATED).execute()
            val movies = call.body() as Movie
            uiThread {
                if (movies.total_results > 0) {
                    loadData(
                        movies,
                        view
                    )
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun getGenre(view: View, movies: Movie) {
        doAsync {
            val call = getRetrofit().create(ApiServiceInterface::class.java)
                .getAllGenre(Constants.GENRE).execute()
            val genres = call.body() as Genre
            uiThread {
                if (genres.genres.size > 0) {
                    loadSpinner(
                        genres,
                        view,
                        movies
                    )
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        context?.alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }?.show()
    }


    private fun loadData(movies: Movie, view: View) {
        mRecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        context?.let { mAdapter.RecyclerAdapter(movies.results, it) }
        mRecyclerView.adapter = mAdapter

        getGenre(view, movies)

    }

    private fun loadSpinner(genre: Genre, view: View, movies: Movie) {
        mSpinner = view.findViewById(R.id.spinner) as Spinner

        val itemsSp: MutableList<String> = ArrayList()

        genre.genres.forEach {
            itemsSp.add(it.name)
        }

        val aa = ArrayAdapter(
            context,
            R.layout.support_simple_spinner_dropdown_item, itemsSp
        )

        mProgressBar = view.findViewById(R.id.progressBar) as ProgressBar

        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {


                mProgressBar.visibility = View.VISIBLE

                val filtermodelist =
                    filter(movies.results, genre.genres.get(position).id.toString())
                context?.let { mAdapter.RecyclerAdapter(filtermodelist, it) }
                mProgressBar.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner!!.setAdapter(aa)


        mProgressBar = view.findViewById(R.id.progressBar) as ProgressBar
        mProgressBar.visibility = View.GONE
    }

    fun filter(pl: MutableList<Result>, query: String): MutableList<Result> {

        var query = query
        query = query.toLowerCase()
        val filteredModeList = ArrayList<Result>()
        pl.forEach {
            val movie = it
            movie.genre_ids.forEach {
                if (it.toString().equals(query)) {
                    filteredModeList.add(movie)
                }
            }
        }
        return filteredModeList
    }


}