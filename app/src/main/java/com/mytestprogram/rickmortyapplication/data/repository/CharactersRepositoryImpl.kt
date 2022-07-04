package com.mytestprogram.rickmortyapplication.data

import androidx.room.withTransaction
import com.mytestprogram.rickmortyapplication.data.local.RickMortyDatabase
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.networkBoundService
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterRetrofitService: CharacterRetrofitService,
    private val db: RickMortyDatabase
) : CharactersRepository {

    private val singleCharacterDao = db.getDatabase()

    override fun loadAllCharacters() = networkBoundService(
        query = {
            singleCharacterDao.getAllCharacters()
        },
        fetch = {
            characterRetrofitService.loadAllCharacters().results
        },
        saveFetchResults = { allcharacters ->
            db.withTransaction {
                singleCharacterDao.deleteAllCharacters()
                singleCharacterDao.insertAll(allcharacters)
            }
        }
    )

//    override suspend fun loadAllCharacters(): AllCharacters =
//        characterRetrofitService.loadAllCharacters()

    override fun loadCharacterById(characterId: Int) = networkBoundService(
        query = {
            singleCharacterDao.getCharacterById(characterId)
        },
        fetch = {
            characterRetrofitService.loadCharacterById(characterId)
        },
        saveFetchResults = { singleCharacter ->
            db.withTransaction {
                singleCharacterDao.deleteCharacterById(characterId)
                singleCharacterDao.insert(singleCharacter)
            }
        }
    )


    override fun loadMultipleCharacters(characterIds: List<Int>) = networkBoundService(
        query = {
            singleCharacterDao.getMultipleCharactersById(characterIds)
        },
        fetch = {
            characterRetrofitService.loadMultipleCharactersById(characterIds)
        },
        saveFetchResults = { characters ->
            db.withTransaction {
                singleCharacterDao.deleteMultipleCharacterById(characterIds)
                singleCharacterDao.insertAll(characters)
            }
        }
    )

    override suspend fun loadAllEpisodes(): AllEpisodes =
        characterRetrofitService.loadAllEpisodes()

    override suspend fun loadEpisodesById(episodeId: Int): SingleEpisode =
        characterRetrofitService.loadEpisodeById(episodeId)

    override suspend fun loadMultipleEpisodes(episodeIds: List<Int>): List<SingleEpisode> =
        characterRetrofitService.loadMultipleEpisodesById(episodeIds)

    override suspend fun loadAllLocations(): AllLocations =
        characterRetrofitService.loadAllLocations()

    override suspend fun loadLocationById(locationId: Int): SingleLocation =
        characterRetrofitService.loadLocationById(locationId)

}