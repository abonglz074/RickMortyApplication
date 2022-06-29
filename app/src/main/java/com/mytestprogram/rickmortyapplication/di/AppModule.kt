package com.mytestprogram.rickmortyapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mytestprogram.rickmortyapplication.data.CharactersRepositoryImpl
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleCharacterByIdUseCase
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsViewModel
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.CharactersViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideListEpisodesViewModelFactory(loadAllEpisodesUseCase: LoadAllEpisodesUseCase): ListEpisodesViewModelFactory {
        return ListEpisodesViewModelFactory(loadAllEpisodesUseCase)
    }

    @Provides
    fun provideLoadAllEpisodesUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadAllEpisodesUseCase {
        return LoadAllEpisodesUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideCharacterDetailsViewModelFactory(loadSingleCharacterByIdUseCase: LoadSingleCharacterByIdUseCase): CharacterDetailsViewModelFactory {
        return CharacterDetailsViewModelFactory(loadSingleCharacterByIdUseCase)
    }

    @Provides
    fun provideSingleCharacterByIdUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadSingleCharacterByIdUseCase {
        return LoadSingleCharacterByIdUseCase(charactersRepositoryImpl)
    }
    @Provides
    fun provideCharactersViewModelFactory(loadAllCharactersUseCase: LoadAllCharactersUseCase): CharactersViewModelFactory {
        return CharactersViewModelFactory(loadAllCharactersUseCase)
    }

    @Provides
    fun provideLoadAllCharactersUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadAllCharactersUseCase {
        return LoadAllCharactersUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideCharactersRepository(characterRetrofitService: CharacterRetrofitService): CharactersRepositoryImpl {
        return CharactersRepositoryImpl(characterRetrofitService)
    }
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): CharacterRetrofitService {
        return retrofit.create(CharacterRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()
}