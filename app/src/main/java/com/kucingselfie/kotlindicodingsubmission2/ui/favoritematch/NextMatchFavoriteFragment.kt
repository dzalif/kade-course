package com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kucingselfie.kotlindicodingsubmission2.R

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match_favorite, container, false)
    }


}
