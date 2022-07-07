package com.mytestprogram.rickmortyapplication.domain.repository

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.LocationEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.OriginEntity
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: CharactersRepository {

    val characters = listOf(
        SingleCharacter(
            created = "test",
            episode = listOf("test", "test"),
            gender = "test",
            id = 1,
            image = "test",
            location = LocationEntity(name = "test", url = "test"),
            name = "test",
            origin = OriginEntity(name = "test", url = "test"),
            species = "test",
            status = "test",
            type = "test",
            url = "test"
        ),
        SingleCharacter(
            created = "test",
            episode = listOf("test", "test"),
            gender = "test",
            id = 2,
            image = "test",
            location = LocationEntity(name = "test", url = "test"),
            name = "test",
            origin = OriginEntity(name = "test", url = "test"),
            species = "test",
            status = "test",
            type = "test",
            url = "test"
        )

    )

    override fun loadAllCharacters(page: Int): Flow<Resource<List<SingleCharacter>>> {
        return flow { emit(Resource.Success(characters)) }
    }

    override fun loadCharacterById(characterId: Int): Flow<Resource<SingleCharacter>> {
        TODO("Not yet implemented")
    }

    override fun filterCharacterByName(characterName: String): Flow<Resource<List<SingleCharacter>>> {
        TODO("Not yet implemented")
    }

    override fun filterCharacterByStatus(status: String): Flow<Resource<List<SingleCharacter>>> {
        TODO("Not yet implemented")
    }

    override fun filterCharacterByGender(gender: String): Flow<Resource<List<SingleCharacter>>> {
        TODO("Not yet implemented")
    }

    override fun filterCharacterBySpecies(species: String): Flow<Resource<List<SingleCharacter>>> {
        TODO("Not yet implemented")
    }

    override fun loadMultipleCharacters(characterIds: List<Int>): Flow<Resource<List<SingleCharacter>>> {
        TODO("Not yet implemented")
    }

    override fun loadAllEpisodes(page: Int): Flow<Resource<List<SingleEpisode>>> {
        TODO("Not yet implemented")
    }

    override fun loadEpisodesById(episodeId: Int): Flow<Resource<SingleEpisode>> {
        TODO("Not yet implemented")
    }

    override fun filterEpisodeByName(episodeName: String): Flow<Resource<List<SingleEpisode>>> {
        TODO("Not yet implemented")
    }

    override fun loadMultipleEpisodes(episodeIds: List<Int>): Flow<Resource<List<SingleEpisode>>> {
        TODO("Not yet implemented")
    }

    override fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>> {
        TODO("Not yet implemented")
    }

    override fun loadLocationById(locationId: Int): Flow<Resource<SingleLocation>> {
        TODO("Not yet implemented")
    }

    override fun filterLocationByName(locationName: String): Flow<Resource<List<SingleLocation>>> {
        TODO("Not yet implemented")
    }

    override fun filterLocationByType(locationType: String): Flow<Resource<List<SingleLocation>>> {
        TODO("Not yet implemented")
    }

    override fun filterLocationByDimension(locationDimension: String): Flow<Resource<List<SingleLocation>>> {
        TODO("Not yet implemented")
    }
}