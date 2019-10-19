package com.kucingselfie.kotlindicodingsubmission2.ui.nextmatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentNextMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.Match
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment() {

    private lateinit var idLeague: String

    private lateinit var binding: FragmentNextMatchBinding

    private lateinit var adapter: NextMatchAdapter

    private var items: MutableList<Match> = mutableListOf()

    private val vm: NextMatchViewModel by lazy {
        ViewModelProviders.of(this).get(NextMatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNextMatchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        //Get id league
        idLeague = NextMatchFragmentArgs.fromBundle(arguments!!).idLeague
        initAdapter()
        vm.getNextMatch(idLeague)

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> { progressBar.visible() }
                else -> { progressBar.invisible() }
            }
        })

        vm.nextMatch.observe(this, Observer {
            if (it.isNotEmpty()) {
                displayData(it)
            }
        })

        return binding.root
    }

    private fun initAdapter() {
        adapter = NextMatchAdapter(requireContext(), items) {
            val action = NextMatchFragmentDirections.actionNextMatchFragmentToDetailMatchFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvNextMatch.adapter = adapter
    }

    private fun displayData(it: List<Match>) {
        items.clear()
        adapter.refreshData(it)
    }

}
