package com.mytestprogram.rickmortyapplication.di

import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.presentation.ListCharactersFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ListCharactersFragment)

}