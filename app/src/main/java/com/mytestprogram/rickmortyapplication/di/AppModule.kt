package com.mytestprogram.rickmortyapplication.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mytestprogram.rickmortyapplication.data.repository.CharactersRepositoryImpl
import com.mytestprogram.rickmortyapplication.data.local.RickMortyDatabase
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadSingleCharacterByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadSingleCharacterByName
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadAllEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadMultipleEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadSingleEpisodeByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadAllLocationsUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadSingleLocationByIdUseCase
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.CharactersViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_locations.ListLocationsViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.location_details.LocationDetailsViewModelFactory
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
    fun provideLoadMultipleCharactersUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadMultipleCharactersUseCase {
        return LoadMultipleCharactersUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideLoadMultipleEpisodesUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadMultipleEpisodesUseCase {
        return LoadMultipleEpisodesUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideLocationDetailsViewModelFactory(
        loadSingleLocationByIdUseCase: LoadSingleLocationByIdUseCase,
        loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
    ): LocationDetailsViewModelFactory {
        return LocationDetailsViewModelFactory(
            loadSingleLocationByIdUseCase,
            loadMultipleCharactersUseCase
        )
    }

    @Provides
    fun provideLoadLocationByIdUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadSingleLocationByIdUseCase {
        return LoadSingleLocationByIdUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideListLocationsViewModelFactory(loadAllLocationsUseCase: LoadAllLocationsUseCase): ListLocationsViewModelFactory {
        return ListLocationsViewModelFactory(loadAllLocationsUseCase)
    }

    @Provides
    fun provideLoadAllLocationsUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadAllLocationsUseCase {
        return LoadAllLocationsUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideEpisodeDetailsViewModelFactory(
        loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase,
        loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
    ): EpisodeDetailsViewModelFactory {
        return EpisodeDetailsViewModelFactory(
            loadSingleEpisodeByIdUseCase,
            loadMultipleCharactersUseCase
        )
    }

    @Provides
    fun provideLoadEpisodeByIdUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadSingleEpisodeByIdUseCase {
        return LoadSingleEpisodeByIdUseCase(charactersRepositoryImpl)
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
    fun provideCharacterDetailsViewModelFactory(
        loadSingleCharacterByIdUseCase: LoadSingleCharacterByIdUseCase,
        loadMultipleEpisodesUseCase: LoadMultipleEpisodesUseCase
    ): CharacterDetailsViewModelFactory {
        return CharacterDetailsViewModelFactory(
            loadSingleCharacterByIdUseCase,
            loadMultipleEpisodesUseCase
        )
    }

    @Provides
    fun provideSingleCharacterByIdUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadSingleCharacterByIdUseCase {
        return LoadSingleCharacterByIdUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideSingleCharacterByName(charactersRepositoryImpl: CharactersRepositoryImpl): LoadSingleCharacterByName {
        return LoadSingleCharacterByName(charactersRepositoryImpl)
    }

    @Provides
    fun provideCharactersViewModelFactory(
        loadAllCharactersUseCase: LoadAllCharactersUseCase,
        loadSingleCharacterByName: LoadSingleCharacterByName
    ): CharactersViewModelFactory {
        return CharactersViewModelFactory(
            loadAllCharactersUseCase,
            loadSingleCharacterByName
        )
    }

    @Provides
    fun provideLoadAllCharactersUseCase(charactersRepositoryImpl: CharactersRepositoryImpl): LoadAllCharactersUseCase {
        return LoadAllCharactersUseCase(charactersRepositoryImpl)
    }

    @Provides
    fun provideCharactersRepository(
        characterRetrofitService: CharacterRetrofitService,
        db: RickMortyDatabase
    ): CharactersRepositoryImpl {
        return CharactersRepositoryImpl(characterRetrofitService, db)
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

    @Singleton
    @Provides
    fun provideDatabase(context: Context): RickMortyDatabase =
        Room.databaseBuilder(context, RickMortyDatabase::class.java, "database")
            .build()
}