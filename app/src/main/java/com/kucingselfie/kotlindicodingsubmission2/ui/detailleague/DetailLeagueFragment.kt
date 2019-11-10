package com.kucingselfie.kotlindicodingsubmission2.ui.detailleague


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailLeagueBinding
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment() {

    private val vm: DetailLeagueViewModel by lazy {
        ViewModelProviders.of(this).get(DetailLeagueViewModel::class.java)
    }

    private lateinit var idLeague: String

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
                Result.LOADING -> { progressBar.visible() }
                else -> { progressBar.invisible() }
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
        idLeague = it[0].id
    }
}
