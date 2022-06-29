package com.mytestprogram.rickmortyapplication.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mytestprogram.rickmortyapplication.data.CharactersRepositoryImpl
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.presentation.CharactersViewModelFactory
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