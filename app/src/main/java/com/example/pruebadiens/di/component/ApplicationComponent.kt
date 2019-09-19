package com.example.pruebadiens.di.component

import com.example.pruebadiens.BaseApp
import com.example.pruebadiens.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}