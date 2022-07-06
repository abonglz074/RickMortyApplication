package com.mytestprogram.rickmortyapplication.data.repository

import com.mytestprogram.rickmortyapplication.data.local.RickMortyDatabase
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterRetrofitService: CharacterRetrofitService,
    private val db: RickMortyDatabase
) : CharactersRepository {

    private val singleCharacterDao = db.getDatabase()

    override fun loadAllCharacters(page: Int): Flow<Resource<List<SingleCharacter>>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getAllCharacters().map { it.toSingleCharacter() }
        emit(Resource.Loading(dataFromDb))
        try {
            val remoteCharacters = characterRetrofitService.loadAllCharacters(page)
//            singleCharacterDao.deleteAllCharacters()
            singleCharacterDao.insertAllCharacters(remoteCharacters.results.map { it.toSingleCharacterEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again",
                    data = dataFromDb
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check internet connection!",
                    data = dataFromDb
                )
            )
        }

        val newData = singleCharacterDao.getAllCharacters().map { it.toSingleCharacter() }
        emit(Resource.Success(newData))

    }

    override fun loadCharacterById(characterId: Int): Flow<Resource<SingleCharacter>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getCharacterById(characterId).toSingleCharacter()
//        emit(Resource.Loading(dataFromDb))
//        try {
//            val remoteCharacters = characterRetrofitService.loadCharacterById(characterId)
//            singleCharacterDao.deleteCharacterById(characterId)
//            singleCharacterDao.insert(remoteCharacters.toSingleCharacterEntity())
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Oops, something went wrong!",
//                data = dataFromDb
//            ))
//        } catch (e: IOException) {
//            emit(Resource.Error(
//                message = "Check internet connection!",
//                data = dataFromDb
//            ))
//        }
//
//        val newData = singleCharacterDao.getCharacterById(characterId).toSingleCharacter()
        emit(Resource.Success(dataFromDb))
    }

    override fun filterCharacterByName(characterName: String): Flow<Resource<List<SingleCharacter>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getCharacterByName(characterName).map { it.toSingleCharacter() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterCharacterByName(characterName).results
                        .map { it.toSingleCharacterEntity() }
                singleCharacterDao.insertAllCharacters(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getCharacterByName(characterName)
                .map { it.toSingleCharacter() }
            emit(Resource.Success(newData))
        }

    override fun filterCharacterByStatus(status: String): Flow<Resource<List<SingleCharacter>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getCharacterByStatus(status).map { it.toSingleCharacter() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterCharactersByStatus(status).results
                        .map { it.toSingleCharacterEntity() }
                singleCharacterDao.insertAllCharacters(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getCharacterByStatus(status)
                .map { it.toSingleCharacter() }
            emit(Resource.Success(newData))
        }

    override fun filterCharacterByGender(gender: String): Flow<Resource<List<SingleCharacter>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getCharacterByGender(gender).map { it.toSingleCharacter() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterCharactersByGender(gender).results
                        .map { it.toSingleCharacterEntity() }
                singleCharacterDao.insertAllCharacters(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getCharacterByGender(gender)
                .map { it.toSingleCharacter() }
            emit(Resource.Success(newData))
        }

    override fun filterCharacterBySpecies(species: String): Flow<Resource<List<SingleCharacter>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getCharacterBySpecies(species).map { it.toSingleCharacter() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterCharactersBySpecies(species).results
                        .map { it.toSingleCharacterEntity() }
                singleCharacterDao.insertAllCharacters(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getCharacterBySpecies(species)
                .map { it.toSingleCharacter() }
            emit(Resource.Success(newData))
        }


    override fun loadMultipleCharacters(characterIds: List<Int>) = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getMultipleCharactersById(characterIds)
            .map { it.toSingleCharacter() }
//        emit(Resource.Loading(dataFromDb))
//        try {
//            val remoteCharacters = characterRetrofitService.loadMultipleCharactersById(characterIds)
//            singleCharacterDao.deleteMultipleCharacterById(characterIds)
//            singleCharacterDao.insertAll(remoteCharacters.map { it.toSingleCharacterEntity() })
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Oops, something went wrong!",
//                data = dataFromDb
//            ))
//        } catch (e: IOException) {
//            emit(Resource.Error(
//                message = "Check internet connection!",
//                data = dataFromDb
//            ))
//        }
//
//        val newData = singleCharacterDao.getMultipleCharactersById(characterIds).map { it.toSingleCharacter() }
        emit(Resource.Success(dataFromDb))

    }

    override fun loadAllEpisodes(page: Int): Flow<Resource<List<SingleEpisode>>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getAllEpisodes().map { it.toSingleEpisode() }
        emit(Resource.Loading(dataFromDb))
        try {
            val remoteEpisodes = characterRetrofitService.loadAllEpisodes(page)
            singleCharacterDao.insertAllEpisodes(remoteEpisodes.results.map { it.toSingleEpisodeEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again",
                    data = dataFromDb
                )
            )
        }

        val newData = singleCharacterDao.getAllEpisodes().map { it.toSingleEpisode() }
        emit(Resource.Success(newData))

    }

    override fun loadEpisodesById(episodeId: Int): Flow<Resource<SingleEpisode>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getEpisodeById(episodeId).toSingleEpisode()
//        emit(Resource.Loading(dataFromDb))
//        try {
//            val remoteCharacters = characterRetrofitService.loadCharacterById(characterId)
//            singleCharacterDao.deleteCharacterById(characterId)
//            singleCharacterDao.insert(remoteCharacters.toSingleCharacterEntity())
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Oops, something went wrong!",
//                data = dataFromDb
//            ))
//        } catch (e: IOException) {
//            emit(Resource.Error(
//                message = "Check internet connection!",
//                data = dataFromDb
//            ))
//        }
//
//        val newData = singleCharacterDao.getCharacterById(characterId).toSingleCharacter()
        emit(Resource.Success(dataFromDb))
    }

    override fun loadMultipleEpisodes(episodeIds: List<Int>): Flow<Resource<List<SingleEpisode>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb = singleCharacterDao.getMultipleEpisodesById(episodeIds)
                .map { it.toSingleEpisode() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteEpisodes = characterRetrofitService.loadMultipleEpisodesById(episodeIds)
                singleCharacterDao.deleteAllEpisodes()
                singleCharacterDao.insertAllEpisodes(remoteEpisodes.map { it.toSingleEpisodeEntity() })
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = dataFromDb
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = dataFromDb
                    )
                )
            }
            val newData =
                singleCharacterDao.getMultipleEpisodesById(episodeIds).map { it.toSingleEpisode() }
            emit(Resource.Success(newData))
        }

    override fun filterEpisodeByName(episodeName: String): Flow<Resource<List<SingleEpisode>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getEpisodeByName(episodeName).map { it.toSingleEpisode() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters = characterRetrofitService.filterEpisodesByName(episodeName).results
                    .map { it.toSingleEpisodeEntity() }
                singleCharacterDao.insertAllEpisodes(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getEpisodeByName(episodeName)
                .map { it.toSingleEpisode() }
            emit(Resource.Success(newData))
        }

    override fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getAllLocations().map { it.toSingleLocation() }
        emit(Resource.Loading(dataFromDb))
        try {
            val remoteLocations = characterRetrofitService.loadAllLocations(page)
            singleCharacterDao.insertAllLocations(remoteLocations.results.map { it.toSingleLocationEntity() })
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong! Try again",
                    data = dataFromDb
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check internet connection!",
                    data = dataFromDb
                )
            )
        }

        val newData = singleCharacterDao.getAllLocations().map { it.toSingleLocation() }
        emit(Resource.Success(newData))

    }

    override fun loadLocationById(locationId: Int): Flow<Resource<SingleLocation>> = flow {
        emit(Resource.Loading())
        val dataFromDb = singleCharacterDao.getLocationsById(locationId).toSingleLocation()
//        emit(Resource.Loading(dataFromDb))
//        try {
//            val remoteCharacters = characterRetrofitService.loadCharacterById(characterId)
//            singleCharacterDao.deleteCharacterById(characterId)
//            singleCharacterDao.insert(remoteCharacters.toSingleCharacterEntity())
//        } catch (e: HttpException){
//            emit(Resource.Error(
//                message = "Oops, something went wrong!",
//                data = dataFromDb
//            ))
//        } catch (e: IOException) {
//            emit(Resource.Error(
//                message = "Check internet connection!",
//                data = dataFromDb
//            ))
//        }
//
//        val newData = singleCharacterDao.getCharacterById(characterId).toSingleCharacter()
        emit(Resource.Success(dataFromDb))
    }

    override fun filterLocationByName(locationName: String): Flow<Resource<List<SingleLocation>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getLocationsByName(locationName).map { it.toSingleLocation() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterLocations(locationName).results
                        .map { it.toSingleLocationEntity() }
                singleCharacterDao.insertAllLocations(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getLocationsByName(locationName)
                .map { it.toSingleLocation() }
            emit(Resource.Success(newData))
        }

    override fun filterLocationByType(locationType: String): Flow<Resource<List<SingleLocation>>> =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getLocationsByType(locationType).map { it.toSingleLocation() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterLocationsByType(locationType).results
                        .map { it.toSingleLocationEntity() }
                singleCharacterDao.insertAllLocations(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getLocationsByType(locationType)
                .map { it.toSingleLocation() }
            emit(Resource.Success(newData))
        }

    override fun filterLocationByDimension(locationDimension: String): Flow<Resource<List<SingleLocation>>>  =
        flow {
            emit(Resource.Loading())
            val dataFromDb =
                singleCharacterDao.getLocationsByDimension(locationDimension).map { it.toSingleLocation() }
            emit(Resource.Loading(dataFromDb))
            try {
                val remoteCharacters =
                    characterRetrofitService.filterLocationsByDimension(locationDimension).results
                        .map { it.toSingleLocationEntity() }
                singleCharacterDao.insertAllLocations(remoteCharacters)
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Check internet connection!",
                        data = emptyList()
                    )
                )
            }

            val newData = singleCharacterDao.getLocationsByDimension(locationDimension)
                .map { it.toSingleLocation() }
            emit(Resource.Success(newData))
        }
}