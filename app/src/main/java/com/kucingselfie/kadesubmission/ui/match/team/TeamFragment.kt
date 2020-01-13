package com.kucingselfie.kadesubmission.ui.match.team

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
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentTeamBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import javax.inject.Inject

class TeamFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentTeamBinding>()

    private lateinit var idLeague: String
    private var adapter by autoCleared<TeamAdapter>()
    private val vm: TeamViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_team, container, false, dataBindingComponent
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
        vm.teams.observe(this, Observer {
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
        val rvAdapter = TeamAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            // navController().navigate(ListLeagueFragmentDirections.actionListLeagueFragmentToDetailLeagueFragment(it.id))
        }
        binding.results = vm.teams
        binding.rvTeam.adapter = rvAdapter
        adapter = rvAdapter
    }
}
