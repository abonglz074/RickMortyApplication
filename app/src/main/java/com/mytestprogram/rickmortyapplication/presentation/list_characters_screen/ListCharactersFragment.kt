package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FilterCharactersBinding
import com.mytestprogram.rickmortyapplication.databinding.FragmentListCharactersBinding
import com.mytestprogram.rickmortyapplication.utils.navigator
import javax.inject.Inject

class ListCharactersFragment : Fragment() {

    @Inject
    lateinit var vmFactory: CharactersViewModelFactory
    private lateinit var binding: FragmentListCharactersBinding
    private lateinit var adapter: ListCharactersAdapter
    lateinit var viewModel: ListCharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCharactersBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).bottomNavigationVisible()
        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory).get(ListCharactersViewModel::class.java)


        adapter = ListCharactersAdapter(object : ListCharactersActionListener {
            override fun onCharacterDetailsScreen(characterId: Int) {
                navigator().showCharacterDetails(characterId)
            }

        })

        binding.charactersListRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.charactersListRecyclerview.adapter = adapter


        var isScrolling = false
        binding.charactersListRecyclerview.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = recyclerView.layoutManager!!.childCount
                val totalVisibleItems = recyclerView.layoutManager!!.itemCount
                val pastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()

                if (isScrolling && visibleItemCount + pastVisibleItem >= totalVisibleItems) {
                    isScrolling = false
                    viewModel.loadAllCharacters()
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        })


        viewModel.allCharacters.observe(viewLifecycleOwner) {
            adapter.characters = viewModel.allCharacters.value ?: emptyList()

            viewModel.isDataLoading.observe(viewLifecycleOwner) {
                binding.listCharactersProgressBar.isVisible = it
            }

            viewModel.isError.observe(viewLifecycleOwner) {
                if (it == true) {
                    Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
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
                        viewModel.filterCharacter.observe(viewLifecycleOwner) {
                            adapter.characters = viewModel.filterCharacter.value ?: emptyList()
                            binding.noData.isVisible = adapter.characters.isEmpty()
                        }
                    } else {
//                        viewModel.loadAllCharacters()
                        adapter.characters = viewModel.allCharacters.value ?: emptyList()
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


            binding.refreshListCharacters.setOnRefreshListener {
                viewModel.allCharacters.observe(viewLifecycleOwner) {
                    adapter.characters = viewModel.allCharacters.value ?: emptyList()
                }
                binding.refreshListCharacters.isRefreshing = false
            }

        }
        return binding.root
    }

    private fun showFilterDialog(inflater: LayoutInflater) {
        val binding = FilterCharactersBinding.inflate(inflater)
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)
        binding.statusAlive.setOnClickListener {
            viewModel.filterByStatus(binding.statusAlive.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }

        binding.statusDead.setOnClickListener {
            viewModel.filterByStatus(binding.statusDead.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.statusUnknown.setOnClickListener {
            viewModel.filterByStatus(binding.statusUnknown.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.genderMale.setOnClickListener {
            viewModel.filterByGender(binding.genderMale.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.genderFemale.setOnClickListener {
            viewModel.filterByGender(binding.genderFemale.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.genderGenderless.setOnClickListener {
            viewModel.filterByGender(binding.genderGenderless.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.genderUnknown.setOnClickListener {
            viewModel.filterByGender(binding.genderUnknown.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.cancelFilter.setOnClickListener {
//            viewModel.loadAllCharacters()
            dialog.dismiss()
            viewModel.allCharacters.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.allCharacters.value ?: emptyList()
            }
        }
        binding.speciesHuman.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesHuman.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesAlien.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesAlien.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesHumanoid.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesHumanoid.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesAnimal.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesAnimal.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesRobot.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesRobot.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesPoopy.setOnClickListener {
            viewModel.filterBySpecies("Poopybutthole")
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesCronenberg.setOnClickListener {
            viewModel.filterBySpecies(binding.speciesCronenberg.text.toString())
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        binding.speciesMythological.setOnClickListener {
            viewModel.filterBySpecies("Mythological Creature")
            dialog.dismiss()
            viewModel.filterCharacter.observe(viewLifecycleOwner) {
                adapter.characters = viewModel.filterCharacter.value ?: emptyList()
            }
        }
        dialog.show()
    }


}