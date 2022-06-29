package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.isError.observe(viewLifecycleOwner) {
            if (viewModel.isError.value == true) {
                binding.listEpisodesErrorMessage.visibility = View.VISIBLE
            } else {
                binding.listEpisodesErrorMessage.visibility = View.GONE
            }
        }
        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (viewModel.isDataLoading.value == true) {
                binding.listEpisodesProgressBar.visibility = View.VISIBLE
            } else {
                binding.listEpisodesProgressBar.visibility = View.GONE
            }
        }

        viewModel.episodesList.observe(viewLifecycleOwner, Observer {
            adapter.episodes = viewModel.episodesList.value!!.results
        })
        return binding.root
    }
}