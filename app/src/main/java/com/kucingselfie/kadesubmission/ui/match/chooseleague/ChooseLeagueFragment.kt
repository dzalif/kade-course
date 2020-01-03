package com.kucingselfie.kadesubmission.ui.match.chooseleague


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
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentChooseLeagueBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueAdapter
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ChooseLeagueFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentChooseLeagueBinding>()
    private var listLeagueAdapter by autoCleared<ListLeagueAdapter>()

    private val vm: ChooseLeagueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_choose_league, container, false, dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val rvAdapter = ListLeagueAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(ChooseLeagueFragmentDirections.actionChooseLeagueFragmentToMatchFragment(it.id))
        }
        vm.leagues.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        listLeagueAdapter.submitList(it.data)
                    }
                }
            }
        })
        binding.results = vm.leagues
        binding.rvListLeague.adapter = rvAdapter
        listLeagueAdapter = rvAdapter
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()

}
