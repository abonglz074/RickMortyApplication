package com.mytestprogram.rickmortyapplication.presentation.list_locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
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

        viewModel.isError.observe(viewLifecycleOwner) {
            if (viewModel.isError.value == true) {
                binding.listLocationsErrorMessage.visibility = View.VISIBLE
            } else {
                binding.listLocationsErrorMessage.visibility = View.GONE
            }
        }
        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (viewModel.isDataLoading.value == true) {
                binding.listLocationsProgressBar.visibility = View.VISIBLE
            } else {
                binding.listLocationsProgressBar.visibility = View.GONE
            }
        }

        viewModel.locationsList.observe(viewLifecycleOwner) {
            adapter.locations = viewModel.locationsList.value!!.results
        }
        return binding.root
    }
}