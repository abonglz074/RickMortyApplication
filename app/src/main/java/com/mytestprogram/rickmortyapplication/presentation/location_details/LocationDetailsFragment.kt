package com.mytestprogram.rickmortyapplication.presentation.location_details

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FragmentEpisodeDetailsBinding
import com.mytestprogram.rickmortyapplication.databinding.FragmentLocationDetailsBinding
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsAdapter
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsFragment
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsViewModel
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsViewModelFactory
import com.mytestprogram.rickmortyapplication.presentation.list_characters_screen.ListCharactersActionListener
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class LocationDetailsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: LocationDetailsViewModelFactory
    private lateinit var binding: FragmentLocationDetailsBinding
    private lateinit var adapter: LocationDetailsAdapter
    private lateinit var viewModel: LocationDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationDetailsBinding.inflate(layoutInflater, container, false)
        (requireActivity() as MainActivity).bottomNavigationGone()

        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory).get(LocationDetailsViewModel::class.java)

        viewModel.loadLocationById(requireArguments().getInt(ARG_LOCATION_ID))


        adapter = LocationDetailsAdapter(object : ListCharactersActionListener {
            override fun onCharacterDetailsScreen(characterId: Int) {
                navigator().showCharacterDetails(characterId)
            }
        })

        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                navigator().showLocationsList()
            }
            menu.findItem(R.id.search).isVisible = false
            menu.findItem(R.id.filter_button).isVisible = false
        }

        binding.locationDetailsCharactersRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.locationDetailsCharactersRecyclerview.adapter = adapter

        viewModel.singleLocation.observe(viewLifecycleOwner) {
            binding.locationName.text = viewModel.singleLocation.value!!.name
            binding.locationDimension.text = viewModel.singleLocation.value!!.dimension
            binding.locationsType.text = viewModel.singleLocation.value!!.type

            val characterUrlsList: List<String> = viewModel.singleLocation.value!!.residents
            val characterIds = mutableListOf<Int>()
            characterUrlsList.forEach { i ->
                characterIds.add(i.substring(42).toInt())
            }
            viewModel.loadMultipleCharacters(characterIds)

            viewModel.charactersList.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.charactersList.value!!
            }
            viewModel.isDataLoading.observe(viewLifecycleOwner) {
                binding.locationDetailsProgressBar.isVisible = it
            }

            viewModel.isError.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, "Check internet connection", Toast.LENGTH_LONG).show()
                }
            }

            binding.refreshLocationDetails.setOnRefreshListener {
                viewModel.loadLocationById(requireArguments().getInt(ARG_LOCATION_ID))
                viewModel.loadMultipleCharacters(characterIds)
                viewModel.charactersList.observe(viewLifecycleOwner) {
                    adapter.characters = viewModel.charactersList.value!!
                }
                binding.refreshLocationDetails.isRefreshing = false
            }
        }


        return binding.root

    }

    companion object {

        private const val ARG_LOCATION_ID = "ARG_LOCATION_ID"

        fun newInstance(id: Int): LocationDetailsFragment {
            val args = Bundle()

            val fragment = LocationDetailsFragment()
            fragment.arguments = bundleOf(ARG_LOCATION_ID to id)
            return fragment
        }
    }

}

