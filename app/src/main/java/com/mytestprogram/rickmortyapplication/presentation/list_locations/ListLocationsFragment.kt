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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FilterEpisodesBinding
import com.mytestprogram.rickmortyapplication.databinding.FilterLocationsBinding
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

            binding.toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.filter_button -> {
                        showFilterDialog(inflater)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

            binding.refreshListLocations.setOnRefreshListener {
                viewModel.loadAllLocations()
                viewModel.locationsList.observe(viewLifecycleOwner) {
                    adapter.locations = viewModel.locationsList.value ?: emptyList()
                }
                binding.refreshListLocations.isRefreshing = false
            }

        }
        return binding.root
    }

    private fun showFilterDialog(inflater: LayoutInflater) {
        val binding = FilterLocationsBinding.inflate(inflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        binding.typePlanet.setOnClickListener {
            viewModel.filterByType(binding.typePlanet.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.typeSpaceStation.setOnClickListener {
            viewModel.filterByType(binding.typeSpaceStation.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.typeMicroverse.setOnClickListener {
            viewModel.filterByType(binding.typeMicroverse.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.replacementDimension.setOnClickListener {
            viewModel.filterByDimension(binding.replacementDimension.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.dimensionC137.setOnClickListener {
            viewModel.filterByDimension(binding.dimensionC137.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.dimensionUnknown.setOnClickListener {
            viewModel.filterByDimension(binding.dimensionUnknown.text.toString())
            dialog.dismiss()
            viewModel.filterLocations.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.filterLocations.value ?: emptyList()
            }
        }
        binding.allLocations.setOnClickListener {
            viewModel.loadAllLocations()
            dialog.dismiss()
            viewModel.locationsList.observe(viewLifecycleOwner) {
                adapter.locations = viewModel.locationsList.value ?: emptyList()
            }
        }
        dialog.show()
    }
}