package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

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

        viewModel.isError.observe(viewLifecycleOwner) {
            if (viewModel.isError.value == true) {
                binding.listCharactersErrorMessage.visibility = View.VISIBLE
            } else {
                binding.listCharactersErrorMessage.visibility = View.GONE
            }
        }
        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (viewModel.isDataLoading.value == true) {
                binding.listCharactersProgressBar.visibility = View.VISIBLE
            } else {
                binding.listCharactersProgressBar.visibility = View.GONE
            }
        }

        viewModel.allCharacters.observe(viewLifecycleOwner, Observer {
            adapter.characters = viewModel.allCharacters.value!!.results

        })

        return binding.root
    }

}