package com.kucingselfie.kadesubmission.ui.match.chooseleague


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.databinding.FragmentChooseLeagueBinding
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.util.visible

/**
 * A simple [Fragment] subclass.
 */
class ChooseLeagueFragment : Fragment() {

//    private lateinit var listLeagueAdapter: ListLeagueAdapter

    private var items: MutableList<League> = mutableListOf()

    lateinit var binding: FragmentChooseLeagueBinding

    private val vm: ChooseLeagueViewModel by lazy {
        ViewModelProviders.of(this).get(ChooseLeagueViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChooseLeagueBinding.inflate(inflater)
        binding.lifecycleOwner = this

        initData()
        observedata()

        return binding.root
    }

    private fun observedata() {
        //Observe status
//        vm.status.observe(this, Observer {
//            when (it) {
////                Result.LOADING -> {
////                    progressBar.visible()
////                }
////                else -> {
////                    progressBar.invisible()
////                }
//            }
//        })

        //Observe list league data
        vm.listLeague.observe(this, Observer {
            it?.let {
                binding.listLeague.visible()
                displayListLeague(it)
            }
        })
    }

    private fun displayListLeague(it: List<League>) {
        items.clear()
        items.addAll(it)
//        listLeagueAdapter.refreshData(items)
    }

    private fun initData() {
        vm.getListLeague()
    }

}
