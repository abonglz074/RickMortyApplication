package com.mytestprogram.rickmortyapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.engine.Resource
import com.mytestprogram.rickmortyapplication.App
import com.mytestprogram.rickmortyapplication.MainActivity
import com.mytestprogram.rickmortyapplication.R
import com.mytestprogram.rickmortyapplication.databinding.FragmentListCharactersBinding
import javax.inject.Inject

class ListCharactersFragment: Fragment() {

    private lateinit var binding: FragmentListCharactersBinding
//    private lateinit var loadingBinding: LoadingResultBinding
    private lateinit var adapter: ListCharactersAdapter

    lateinit var viewModel: ListCharactersViewModel
    @Inject
    lateinit var vmFactory: CharactersViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListCharactersBinding.inflate(inflater, container, false)
//        loadingBinding = LoadingResultBinding.bind(binding.root)
//        (requireActivity() as MainActivity).bottomNavigationVisible()
        ((requireActivity() as MainActivity).applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, vmFactory).get(ListCharactersViewModel::class.java)


        adapter = ListCharactersAdapter(object : ListCharactersActionListener {
            override fun onCharacterDetailsScreen(characterId: Int) {

            }

        })



        binding.charactersListRecyclerview.layoutManager = GridLayoutManager(context, 2)
        binding.charactersListRecyclerview.adapter = adapter


        viewModel.allCharacters.observe(viewLifecycleOwner, Observer {
            adapter.characters = viewModel.allCharacters.value!!.results
        })


        return binding.root
    }

}