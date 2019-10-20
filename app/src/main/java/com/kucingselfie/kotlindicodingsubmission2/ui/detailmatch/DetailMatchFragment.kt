package com.kucingselfie.kotlindicodingsubmission2.ui.detailmatch


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.api.response.DetailMatchResponse

import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.DetailMatch
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.ui.detailleague.DetailLeagueFragmentDirections
import com.kucingselfie.kotlindicodingsubmission2.ui.nextmatch.NextMatchFragmentArgs
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_detail_match.*

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var binding: FragmentDetailMatchBinding

    private lateinit var idEvent: String

    private val vm: DetailMatchViewModel by lazy {
        ViewModelProviders.of(this).get(DetailMatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMatchBinding.inflate(inflater)

        //Enable options menu
        setHasOptionsMenu(true)

        //Get id event
        idEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).idEvent

        vm.getDetailMatch(idEvent)

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> {
                    progressBar.visible()
                    group.invisible()
                }
                else -> {
                    group.visible()
                    progressBar.invisible()
                }
            }
        })

        vm.detailMatch.observe(this, Observer {
            binding.model = it
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_search_match -> {
                // Navigate to Search Match
                findNavController().navigate(R.id.action_detailMatchFragment_to_searchFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
