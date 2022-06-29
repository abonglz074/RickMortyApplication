package com.mytestprogram.rickmortyapplication.presentation.location_details

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class LocationDetailsFragment: Fragment() {
    companion object {

        private const val ARG_LOCATION_ID = "ARG_LOCATION_ID"

        fun newInstance(id: Int): LocationDetailsFragment {
            val args = Bundle()

            val fragment = LocationDetailsFragment()
            fragment.arguments = bundleOf(ARG_LOCATION_ID to id)
            return fragment
        }
    }

}

