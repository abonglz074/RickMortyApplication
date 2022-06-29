package com.mytestprogram.rickmortyapplication

import android.app.Application
import com.mytestprogram.rickmortyapplication.di.AppComponent
import com.mytestprogram.rickmortyapplication.di.AppModule
import com.mytestprogram.rickmortyapplication.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()


        appComponent = DaggerAppComponent.builder().appModule(AppModule(context = this)).build()
    }
}