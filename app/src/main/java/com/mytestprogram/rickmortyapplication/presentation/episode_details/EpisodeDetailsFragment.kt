package com.mytestprogram.rickmortyapplication.presentation.episode_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.databinding.FragmentEpisodeDetailsBinding
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsViewModel
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.ListCharactersActionListener
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class EpisodeDetailsFragment: Fragment() {

    @Inject
    lateinit var vmFactory: EpisodeDetailsViewModelFactory
    private lateinit var binding: FragmentEpisodeDetailsBinding
//    private lateinit var adapter: EpisodeDetailsAdapter
    private lateinit var viewModel: EpisodeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavigationGone()

        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(EpisodeDetailsViewModel::class.java)

        viewModel.loadEpisodeById(requireArguments().getInt(ARG_EPISODE_ID))

//        binding.episodeDetailsCharactersRecyclerview.layoutManager = GridLayoutManager(context, 2)
//        binding.episodeDetailsCharactersRecyclerview.adapter = adapter

//        adapter = EpisodeDetailsAdapter(object: ListCharactersActionListener {
//            override fun onCharacterDetailsScreen(characterId: Int) {
//                navigator().showCharacterDetails(characterId)
//            }
//        })

        viewModel.singleEpisode.observe(viewLifecycleOwner){
            binding.episodeName.text = viewModel.singleEpisode.value!!.name
            binding.episodeNumber.text = viewModel.singleEpisode.value!!.episode
            binding.episodeAirdate.text = viewModel.singleEpisode.value!!.air_date

//            val characterUrlsList: List<String> = viewModel.episodeDetails.value!!.characters
//            val characterIds = mutableListOf<Int>()
//            characterUrlsList.forEach { i ->
//                characterIds.add(i.substring(42).toInt())
//            }
//            viewModel.loadMultipleCharactersDetails(characterIds)

//            viewModel.characterDetails.observe(viewLifecycleOwner, Observer {
//                adapter.characters = viewModel.characterDetails.value!!
//            })
        }


        return binding.root
    }
    companion object {

        private const val ARG_EPISODE_ID = "ARG_EPISODE_ID"

        fun newInstance(id: Int): EpisodeDetailsFragment {
            val args = Bundle()

            val fragment = EpisodeDetailsFragment()
            fragment.arguments = bundleOf(ARG_EPISODE_ID to id)
            return fragment
        }
    }
}