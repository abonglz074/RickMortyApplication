package com.mytestprogram.rickmortyapplication.presentation.character_details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.databinding.FragmentCharacterDetailsBinding
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.ListCharactersViewModel
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesActionListener
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class CharacterDetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: CharacterDetailsViewModelFactory
    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var viewModel: CharacterDetailsViewModel
    private lateinit var adapter: CharacterDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(CharacterDetailsViewModel::class.java)

        viewModel.loadCharacterById(requireArguments().getInt(ARG_CHARACTER_ID))


        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavigationGone()

        adapter = CharacterDetailsAdapter(object : ListEpisodesActionListener {
            override fun showEpisodeDetailsScreen(episodeId: Int) {
                navigator().showEpisodeDetails(episodeId)
            }
        })


        binding.characterDetailsRecyclerviewEpisodes.layoutManager = LinearLayoutManager(context)
        binding.characterDetailsRecyclerviewEpisodes.adapter = adapter

        viewModel.singleCharacter.observe(viewLifecycleOwner) {
            binding.characterDetailsName.text = viewModel.singleCharacter.value!!.name
            binding.characterDetailsGender.text = viewModel.singleCharacter.value!!.gender
            binding.characterDetailsLocation.text = viewModel.singleCharacter.value!!.location.name
            binding.characterDetailsOrigin.text = viewModel.singleCharacter.value!!.origin.name
            binding.characterDetailsSpecies.text = viewModel.singleCharacter.value!!.species
            binding.characterDetailsStatus.text = viewModel.singleCharacter.value!!.status
            Glide.with(binding.characterDetailsImageview.context)
                .load(viewModel.singleCharacter.value!!.image)
                .into(binding.characterDetailsImageview)


//            val locationUrl = viewModel.singleCharacter.value!!.location.url
//            val locationId = locationUrl.substring(41).toInt()
//            binding.characterDetailsLocation.setOnClickListener {
//                navigator().showLocationDetails(locationId)
//            }
//            binding.characterDetailsOrigin.setOnClickListener {
//                navigator().showLocationDetails(locationId)
//            }

            val episodesUrlsList: List<String> = viewModel.singleCharacter.value!!.episode
            val episodeIds = mutableListOf<Int>()
            episodesUrlsList.forEach { i ->
                episodeIds.add(i.substring(40).toInt())
            }
            viewModel.loadMultipleEpisodes(episodeIds)
            viewModel.episodesList.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.episodesList.value!!
            }






//            viewModel.isError.observe(viewLifecycleOwner) {
//                if (viewModel.isError.value == true) {
//                    binding.characterDetailsErrorMessage.visibility = View.VISIBLE
//                } else {
//                    binding.characterDetailsErrorMessage.visibility = View.GONE
//                }
//            }
//            viewModel.isDataLoading.observe(viewLifecycleOwner) {
//                if (viewModel.isDataLoading.value == true) {
//                    binding.characterDetailsProgressBar.visibility = View.VISIBLE
//                } else {
//                    binding.characterDetailsProgressBar.visibility = View.GONE
//                }
//            }

        }

        return binding.root
    }


    companion object {

        private const val ARG_CHARACTER_ID = "ARG_CHARACTER_ID"

        fun newInstance(id: Int): CharacterDetailsFragment {
            val args = Bundle()

            val fragment = CharacterDetailsFragment()
            fragment.arguments = bundleOf(ARG_CHARACTER_ID to id)
            return fragment
        }
    }
}