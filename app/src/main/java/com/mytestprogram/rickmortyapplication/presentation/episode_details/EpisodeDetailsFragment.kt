package com.mytestprogram.rickmortyapplication.presentation.episode_details

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class EpisodeDetailsFragment: Fragment() {
    companion object {

        private const val ARG_EPISODE_ID = "ARG_EPISODE_ID"

        fun newInstance(id: Int): EpisodeDetailsFragment {
            val args = Bundle()

            val fragment = EpisodeDetailsFragment()
            fragment.arguments = bundleOf(ARG_EPISODE_ID to id)
            return fragment
        }
    }
}