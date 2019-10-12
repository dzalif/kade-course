package com.kucingselfie.kotlindicodingsubmission2.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentListLeagueBinding
import com.kucingselfie.kotlindicodingsubmission2.model.League
import kotlinx.android.synthetic.main.fragment_list_league.*

/**
 * A simple [Fragment] subclass.
 */
class ListLeagueFragment : Fragment() {

    private var items: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListLeagueBinding.inflate(inflater)
        binding.lifecycleOwner = this

        initData()

        binding.listLeague.adapter = ListLeagueAdapter(items) {

        }

        return binding.root
    }

    private fun initData() {val name = resources.getStringArray(R.array.league)
        items.clear()
        for (i in name.indices) {
            items.add(League(name[i]))
        }
    }
}
