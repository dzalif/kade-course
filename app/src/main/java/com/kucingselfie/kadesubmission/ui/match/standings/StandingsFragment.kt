package com.kucingselfie.kadesubmission.ui.match.standings

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
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentStandingsBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueAdapter
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueFragmentDirections
import com.kucingselfie.kadesubmission.ui.match.nextmatch.MatchAdapter
import com.kucingselfie.kadesubmission.ui.match.nextmatch.NextMatchViewModel
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import javax.inject.Inject

class StandingsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentStandingsBinding>()

    private lateinit var idLeague: String
    private var adapter by autoCleared<StandingsAdapter>()
    private val vm: StandingsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_standings, container, false, dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        //Get id league
        idLeague = arguments?.getString("idLeague")!!
        vm.setIdLeague(idLeague)
        initAdapter()
        observeData()
    }

    private fun observeData() {
        vm.standings.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Success -> {
                        adapter.submitList(it.data)
                    }
                }
            }
        })
    }

    private fun initAdapter() {
        val rvAdapter = StandingsAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            // navController().navigate(ListLeagueFragmentDirections.actionListLeagueFragmentToDetailLeagueFragment(it.id))
        }
        binding.results = vm.standings
        binding.rvStanding.adapter = rvAdapter
        adapter = rvAdapter
    }

    fun navController() = findNavController()
}
