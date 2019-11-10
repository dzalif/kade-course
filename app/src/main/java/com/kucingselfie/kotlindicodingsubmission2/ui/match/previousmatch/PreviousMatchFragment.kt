package com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.common.ID_LEAGUE
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentPreviousMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.Match
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.ui.match.MatchFragmentDirections
import com.kucingselfie.kotlindicodingsubmission2.ui.match.nextmatch.MatchAdapter
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */

class PreviousMatchFragment : Fragment() {

    private lateinit var idLeague: String

    private lateinit var binding: FragmentPreviousMatchBinding

    private lateinit var adapter: MatchAdapter

    private var items: MutableList<Match> = mutableListOf()

    private val vm: PreviousMatchViewModel by lazy {
        ViewModelProviders.of(this).get(PreviousMatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentPreviousMatchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        //Get id league
        idLeague = ID_LEAGUE
        initAdapter()
        vm.getPreviousMatch(idLeague)

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

    private fun displayData(it: List<Match>) {
        items.clear()
        adapter.refreshData(it)
    }

    private fun initAdapter() {
        adapter = MatchAdapter(
            requireContext(),
            items
        ) {
            val action =
                MatchFragmentDirections.actionMatchFragmentToDetailMatchFragment(
                    it.id,
                    it.eventImage!!,
                    false
                )
            findNavController().navigate(action)
        }
        binding.rvPreviousMatch.adapter = adapter
    }

}
