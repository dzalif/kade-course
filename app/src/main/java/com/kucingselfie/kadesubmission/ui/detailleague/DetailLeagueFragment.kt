package com.kucingselfie.kadesubmission.ui.detailleague


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentDetailLeagueBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.util.autoCleared
import kotlinx.android.synthetic.main.fragment_detail_league.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DetailLeagueFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentDetailLeagueBinding>()

    private val vm: DetailLeagueViewModel by viewModels { viewModelFactory }

    private lateinit var idLeague: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail_league, container, false, dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getArgFromBundle()
        binding.lifecycleOwner = viewLifecycleOwner
        vm.detailLeague.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Success -> {
                        val league = it.data?.get(0)
                        leagueTitle.text = league?.leagueName
                        leagueDesc.text = league?.description
                        idLeague = league!!.id
                        val image = league.badge
                        Glide.with(requireContext()).load(image).placeholder(R.drawable.trophy).into(imageView)
                    }
                    else -> {}
                }
            }
        })
        binding.results = vm.detailLeague
    }

    private fun getArgFromBundle() {
        //Get id league
        arguments?.let {
            idLeague = DetailLeagueFragmentArgs.fromBundle(it).idLeague
        }
        vm.setIdLeague(idLeague)
    }
}
