package com.kucingselfie.kotlindicodingsubmission2.ui.detailleague


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailLeagueBinding
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    private val vm: DetailLeagueViewModel by lazy {
        ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailLeagueBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        vm.getDetailLeague()

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> { progressBar.visibility = View.VISIBLE }
                else -> { progressBar.visibility = View.INVISIBLE }
            }
        })

        vm.detailLeague.observe(this, Observer {
            if (it.isNotEmpty()) {
                displayData(it)
            }
        })

        return binding.root
    }

    private fun displayData(it: List<DetailLeague>) {
        leagueTitle.text = it[0].leagueName
        leagueDesc.text = it[0].description
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_league_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_next_match -> {
                // Navigate to Next Match
                findNavController().navigate(R.id.action_detailLeagueFragment_to_nextMatchFragment)
                return true
            }
            R.id.action_previous_match -> {
                // Navigate to Previous Match
                findNavController().navigate(R.id.action_detailLeagueFragment_to_previousMatchFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
