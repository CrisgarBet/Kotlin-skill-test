package com.example.pruebadiens.di.module

import android.app.Application
import com.example.pruebadiens.BaseApp
import com.example.pruebadiens.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}