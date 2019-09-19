package com.example.pruebadiens.di.component

import com.example.pruebadiens.di.module.FragmentModule
import com.example.pruebadiens.ui.list.ListFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(listFragment: ListFragment)

}