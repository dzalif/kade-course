package com.kucingselfie.kadesubmission.ui.match.nextmatch


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
import com.kucingselfie.kadesubmission.databinding.FragmentNextMatchBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.ui.match.MatchFragmentDirections
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import javax.inject.Inject


class NextMatchFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentNextMatchBinding>()

    private lateinit var idLeague: String

    private var adapter by autoCleared<MatchAdapter>()

    private val vm: NextMatchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_next_match, container, false, dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        //Get id league
        idLeague = arguments?.getString("idLeague")!!
        vm.setIdLeague(idLeague)
        val rvAdapter = MatchAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(
                MatchFragmentDirections.actionMatchFragmentToDetailMatchFragment(
                    it.id,
                    it.eventImage ?: "",
                    it.teamHomeId,
                    it.teamAwayId,
                    true
                )
            )
        }

        vm.nextMatch.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Success -> {
                        adapter.submitList(it.data)
                    }
                }
            }
        })

        binding.results = vm.nextMatch
        binding.rvNextMatch.adapter = rvAdapter
        adapter = rvAdapter
    }

    fun navController() = findNavController()

}
