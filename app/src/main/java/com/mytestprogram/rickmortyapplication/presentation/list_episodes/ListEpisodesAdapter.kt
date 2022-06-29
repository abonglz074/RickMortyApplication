package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mytestprogram.rickmortyapplication.databinding.CharacterDetailsRecyclerviewEpisodesItemBinding
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode


class ListEpisodesAdapter(
    private val actionListener: ListEpisodesActionListener
): RecyclerView.Adapter<ListEpisodesAdapter.ListEpisodesViewHolder>(), View.OnClickListener {

    var episodes: List<SingleEpisode> = emptyList()

        set(newValue){
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEpisodesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterDetailsRecyclerviewEpisodesItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        return ListEpisodesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ListEpisodesViewHolder, position: Int) {
        val singleEpisode = episodes[position]
        holder.itemView.tag = singleEpisode
        with(holder.binding) {
            episodeName.text = singleEpisode.name
            episodeNumber.text = singleEpisode.episode
            episodeAirdate.text = singleEpisode.air_date
        }
    }

    override fun getItemCount(): Int = episodes.size

    override fun onClick(v: View) {
        val singleEpisode = v.tag as SingleEpisode
        val episodeId = singleEpisode.id
        actionListener.showEpisodeDetailsScreen(episodeId)
    }

    class ListEpisodesViewHolder(var binding: CharacterDetailsRecyclerviewEpisodesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}