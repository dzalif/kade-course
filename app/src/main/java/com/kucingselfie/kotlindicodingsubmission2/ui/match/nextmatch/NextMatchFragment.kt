package com.kucingselfie.kotlindicodingsubmission2.ui.match.nextmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentNextMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.Match
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.ui.match.MatchFragmentDirections
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_next_match.*


class NextMatchFragment : Fragment() {

    private lateinit var idLeague: String

    private lateinit var binding: FragmentNextMatchBinding

    private lateinit var adapter: MatchAdapter

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
        idLeague = arguments?.getString("idLeague")!!

        initAdapter()
        vm.getNextMatch(idLeague)

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> {
                    progressBar.visible()
                    layoutEmptyData.invisible()
                }
                Result.SUCCESS -> {
                    progressBar.invisible()
                    layoutEmptyData.invisible()
                }
                Result.NO_DATA -> {
                    progressBar.invisible()
                    layoutEmptyData.visible()
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

    private fun initAdapter() {
        adapter = MatchAdapter(
            requireContext(),
            items
        ) {
            val action =
                MatchFragmentDirections.actionMatchFragmentToDetailMatchFragment(
                    it.id,
                    it.eventImage ?: "",
                    true
                )
            findNavController().navigate(action)
        }
        binding.rvNextMatch.adapter = adapter
    }

    private fun displayData(it: List<Match>) {
        items.clear()
        adapter.refreshData(it)
    }

}
