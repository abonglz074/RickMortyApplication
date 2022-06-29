package com.mytestprogram.rickmortyapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mytestprogram.rickmortyapplication.databinding.ActivityMainBinding
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsFragment
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsFragment
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.ListCharactersFragment
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesFragment
import com.mytestprogram.rickmortyapplication.presentation.location_details.LocationDetailsFragment
import com.mytestprogram.rickmortyapplication.utils.Navigation

class MainActivity : AppCompatActivity(), Navigation {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_characters -> {
                    showCharactersList()
                }
                R.id.ic_locations -> {
                    showLocationsList()
                }
                R.id.ic_episodes -> {
                    showEpisodesList()
                }
            }
            return@setOnItemSelectedListener true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ListCharactersFragment())
                .commit()
        }
    }

    override fun showCharactersList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListCharactersFragment())
            .commit()
    }

    override fun showLocationsList() {
        TODO("Not yet implemented")
    }

    override fun showEpisodesList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListEpisodesFragment())
            .commit()
    }

    override fun showCharacterDetails(characterId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CharacterDetailsFragment.newInstance(characterId))
            .addToBackStack(null)
            .commit()
    }

    override fun showLocationDetails(locationId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LocationDetailsFragment.newInstance(locationId))
            .addToBackStack(null)
            .commit()
    }

    override fun showEpisodeDetails(episodeId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EpisodeDetailsFragment.newInstance(episodeId))
            .addToBackStack(null)
            .commit()
    }


    fun bottomNavigationGone() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun bottomNavigationVisible() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

}