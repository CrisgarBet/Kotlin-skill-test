package com.example.pruebadiens.ui.list

import com.example.pruebadiens.models.Movie
import com.example.pruebadiens.ui.BaseContract

class ListContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Movie>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}