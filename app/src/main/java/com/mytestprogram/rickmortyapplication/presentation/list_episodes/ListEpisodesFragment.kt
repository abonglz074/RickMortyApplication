package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.databinding.FragmentListEpisodesBinding
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class ListEpisodesFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ListEpisodesViewModelFactory
    private lateinit var binding: FragmentListEpisodesBinding
    private lateinit var adapter: ListEpisodesAdapter
    private lateinit var viewModel: ListEpisodesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListEpisodesBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).bottomNavigationVisible()
        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory).get(ListEpisodesViewModel::class.java)


        adapter = ListEpisodesAdapter(object : ListEpisodesActionListener {
            override fun showEpisodeDetailsScreen(episodeId: Int) {
                navigator().showEpisodeDetails(episodeId)
            }
        })
        binding.listEpisodesRecyclerviewItem.layoutManager = GridLayoutManager(context, 2)
        binding.listEpisodesRecyclerviewItem.adapter = adapter

        viewModel.episodesList.observe(viewLifecycleOwner) {
            adapter.episodes = viewModel.episodesList.value ?: emptyList()
            viewModel.isDataLoading.observe(viewLifecycleOwner) {
                binding.listEpisodesProgressBar.isVisible = it
            }

            viewModel.isError.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, "Check internet connection", Toast.LENGTH_LONG).show()
                }
            }
        }
        return binding.root
    }
}