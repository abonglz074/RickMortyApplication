package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.databinding.CharactersListRecyclerviewItemBinding
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter

class ListCharactersAdapter(
    private val actionListener: ListCharactersActionListener
) :
    RecyclerView.Adapter<ListCharactersAdapter.ListCharactersViewHolder>(), View.OnClickListener {

    var characters: List<SingleCharacterEntity> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }



//
//    private val diffCallback = object : DiffUtil.ItemCallback<GetCharacterByIdResponse>() {
//
//        override fun areItemsTheSame(oldItem: GetCharacterByIdResponse, newItem: GetCharacterByIdResponse): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: GetCharacterByIdResponse, newItem: GetCharacterByIdResponse): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharactersListRecyclerviewItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return ListCharactersViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListCharactersViewHolder, position: Int) {
        val singleCharacter = characters[position]
        holder.itemView.tag = singleCharacter
        Glide.with(holder.itemView)
            .load(singleCharacter.image)
            .into(holder.binding.characterImageView)
        with(holder.binding) {
            characterNameTextView.text = singleCharacter.name
            characterGenderTextView.text = singleCharacter.gender
            characterSpeciesTextView.text = singleCharacter.species
            characterStatusTextView.text = singleCharacter.status
        }
    }

    override fun getItemCount(): Int = characters.size


    class ListCharactersViewHolder(var binding: CharactersListRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val singleCharacter = v.tag as SingleCharacter
        val singleCharacterId = singleCharacter.id
        actionListener.onCharacterDetailsScreen(singleCharacterId)


    }
}