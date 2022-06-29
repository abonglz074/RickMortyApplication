package com.mytestprogram.rickmortyapplication.di

import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsFragment
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.ListCharactersFragment
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ListCharactersFragment)
    fun inject(fragment: CharacterDetailsFragment)
    fun inject(fragment: ListEpisodesFragment)

}