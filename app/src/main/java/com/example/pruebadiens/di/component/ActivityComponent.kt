package com.example.pruebadiens.di.component

import com.example.pruebadiens.di.module.ActivityModule
import com.example.pruebadiens.ui.main.MainActivity
import dagger.Component


@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}