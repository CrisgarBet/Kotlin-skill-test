package com.example.pruebadiens.di.module

import com.example.pruebadiens.api.ApiServiceInterface
import com.example.pruebadiens.ui.list.ListContract
import com.example.pruebadiens.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {


    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}