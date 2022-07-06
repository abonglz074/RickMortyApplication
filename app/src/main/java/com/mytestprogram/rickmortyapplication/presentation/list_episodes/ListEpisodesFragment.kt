package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FilterCharactersBinding
import com.mytestprogram.rickmortyapplication.databinding.FilterEpisodesBinding
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

        var isScrolling = false
        binding.listEpisodesRecyclerviewItem.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalVisibleItems = recyclerView.layoutManager!!.itemCount
                val pastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()

                if (isScrolling && visibleItemCount + pastVisibleItem >= totalVisibleItems) {
                    isScrolling = false
                    viewModel.loadAllEpisodes()
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
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
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show()
                }
            }

            val searchItem = binding.toolbar.menu.findItem(R.id.search)
            val searchView = searchItem.actionView as SearchView
            searchView.maxWidth = Integer.MAX_VALUE
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (!query.isNullOrBlank()) {
                        viewModel.onSearch(query)
                        viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                            adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()
                            binding.noData.isVisible = adapter.episodes.isEmpty()
                        }
                    } else {
                        viewModel.loadAllEpisodes()
                        adapter.episodes = viewModel.episodesList.value ?: emptyList()
                        binding.noData.isVisible = false


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
            binding.refreshListEpisodes.setOnRefreshListener {
                viewModel.loadAllEpisodes()
                viewModel.episodesList.observe(viewLifecycleOwner) {
                    adapter.episodes = viewModel.episodesList.value ?: emptyList()

                }
                binding.refreshListEpisodes.isRefreshing = false
            }

        }
        return binding.root
    }

    private fun showFilterDialog(inflater: LayoutInflater) {
        val binding = FilterEpisodesBinding.inflate(inflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        binding.season1.setOnClickListener {
            val idSeason1 = listOf(1,2,3,4,5,6,7,8,9,10,11)
            viewModel.loadMultipleEpisodes(idSeason1)
            dialog.dismiss()
            viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()

            }
        }
        binding.season2.setOnClickListener {
            val idSeason2 = listOf(12,13,14,15,16,17,18,19,20,21)
            viewModel.loadMultipleEpisodes(idSeason2)
            dialog.dismiss()
            viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()
            }
        }
        binding.season3.setOnClickListener {
            val idSeason3 = listOf(22,23,24,25,26,27,28,29,30,31)
            viewModel.loadMultipleEpisodes(idSeason3)
            dialog.dismiss()
            viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()
            }
        }
        binding.season4.setOnClickListener {
            val idSeason4 = listOf(32,33,34,35,36,37,38,39,40,41)
            viewModel.loadMultipleEpisodes(idSeason4)
            dialog.dismiss()
            viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()
            }
        }
        binding.season5.setOnClickListener {
            val idSeason5 = listOf(42,43,44,45,46,47,48,49,50,51)
            viewModel.loadMultipleEpisodes(idSeason5)
            dialog.dismiss()
            viewModel.filterEpisodes.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.filterEpisodes.value ?: emptyList()
            }
        }
        binding.allEpisodes.setOnClickListener {
            viewModel.loadAllEpisodes()
            dialog.dismiss()
            viewModel.episodesList.observe(viewLifecycleOwner) {
                adapter.episodes = viewModel.episodesList.value ?: emptyList()
            }
        }
        dialog.show()
    }
}