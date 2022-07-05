package com.mytestprogram.rickmortyapplication.presentation.list_locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FragmentListEpisodesBinding
import com.mytestprogram.rickmortyapplication.databinding.FragmentListLocationsBinding
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesActionListener
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesAdapter
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesViewModel
import com.mytestprogram.rickmortyapplication.presentation.list_episodes.ListEpisodesViewModelFactory
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class ListLocationsFragment : Fragment() {
    @Inject
    lateinit var vmFactory: ListLocationsViewModelFactory
    private lateinit var binding: FragmentListLocationsBinding
    private lateinit var adapter: ListLocationsAdapter
    private lateinit var viewModel: ListLocationsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListLocationsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).bottomNavigationVisible()
        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory).get(ListLocationsViewModel::class.java)


        adapter = ListLocationsAdapter(object : ListLocationsActionListener {
            override fun onLocationDetailsScreen(locationId: Int) {
                navigator().showLocationDetails(locationId)
            }
        })
        binding.listLocationsRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.listLocationsRecyclerview.adapter = adapter

        viewModel.locationsList.observe(viewLifecycleOwner) {
            adapter.locations = viewModel.locationsList.value ?: emptyList()
            viewModel.isDataLoading.observe(viewLifecycleOwner) {
                binding.listLocationsProgressBar.isVisible = it
            }

            viewModel.isError.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, "Check internet connection", Toast.LENGTH_LONG).show()
                }
            }


            val searchItem = binding.toolbar.menu.findItem(R.id.search)
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Integer.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        viewModel.onSearch(query)
                        viewModel.filterLocations.observe(viewLifecycleOwner) {
                            adapter.locations = viewModel.filterLocations.value ?: emptyList()
                        }
                    } else {
                        viewModel.loadAllLocations()
                        adapter.locations = viewModel.locationsList.value ?: emptyList()
                    }

                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        viewModel.onSearch(query)
                        viewModel.filterLocations.observe(viewLifecycleOwner) {
                            adapter.locations = viewModel.filterLocations.value ?: emptyList()
                        }
                    } else {
                        viewModel.loadAllLocations()
                        adapter.locations = viewModel.locationsList.value ?: emptyList()
                    }

                    return true
                }
            })

        }
        return binding.root
    }
}