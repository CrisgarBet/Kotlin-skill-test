package com.example.pruebadiens.ui.main

import com.example.pruebadiens.ui.BaseContract

class MainContract {

    interface View : BaseContract.View {
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {

    }

}