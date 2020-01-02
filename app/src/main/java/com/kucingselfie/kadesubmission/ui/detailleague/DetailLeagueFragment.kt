package com.kucingselfie.kadesubmission.ui.detailleague


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.FragmentDetailLeagueBinding
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.util.invisible
import com.kucingselfie.kadesubmission.util.visible
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

        getArgFromBundle()

        vm.getDetailLeague(idLeague)

//        vm.status.observe(this, Observer {
//            when(it) {
////                Result.LOADING -> { progressBar.visible() }
////                else -> { progressBar.invisible() }
//            }
//        })

        vm.league.observe(this, Observer {
            if (it.isNotEmpty()) {
                displayData(it)
            }
        })

        return binding.root
    }

    private fun getArgFromBundle() {
        //Get id league
        idLeague = DetailLeagueFragmentArgs.fromBundle(arguments!!).idLeague
    }

    private fun displayData(it: List<League>) {
        leagueTitle.text = it[0].leagueName
        leagueDesc.text = it[0].description
        idLeague = it[0].id
        val image = it[0].badge
        Glide.with(requireContext()).load(image).placeholder(R.drawable.trophy).into(imageView)
    }
}
