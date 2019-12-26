package com.kucingselfie.kadesubmission.ui.match.previousmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.databinding.FragmentPreviousMatchBinding
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Result
import com.kucingselfie.kadesubmission.ui.match.MatchFragmentDirections
import com.kucingselfie.kadesubmission.ui.match.nextmatch.MatchAdapter
import com.kucingselfie.kadesubmission.util.invisible
import com.kucingselfie.kadesubmission.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_previous_match.*

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
        idLeague = arguments?.getString("idLeague")!!

        initAdapter()
        vm.getPreviousMatch(idLeague)

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> {
                    progressBar.visible()
                    layoutEmptyData.invisible()
                }
                Result.NO_DATA -> {
                    progressBar.invisible()
                    layoutEmptyData.visible()
                }
                Result.SUCCESS -> {
                    progressBar.invisible()
                    layoutEmptyData.invisible()
                }
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
                    it.eventImage ?: "",
                    it.teamHomeId,
                    it.teamAwayId,
                    false
                )
            findNavController().navigate(action)
        }
        binding.rvPreviousMatch.adapter = adapter
    }

}
