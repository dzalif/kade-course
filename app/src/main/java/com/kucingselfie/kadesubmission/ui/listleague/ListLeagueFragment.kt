package com.kucingselfie.kadesubmission.ui.listleague


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentListLeagueBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.visible
import javax.inject.Inject


class ListLeagueFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentListLeagueBinding>()
    private var searchAdapter by autoCleared<SearchAdapter>()
    private var listLeagueAdapter by autoCleared<ListLeagueAdapter>()

    private val vm: ListLeagueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_league, container, false, dataBindingComponent
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val rvAdapter = ListLeagueAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(ListLeagueFragmentDirections.actionListLeagueFragmentToDetailLeagueFragment(it.id))
        }
        vm.leagues.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        binding.rvSearch.gone()
                        binding.rvListLeague.visible()
                        listLeagueAdapter.submitList(it.data)
                    }
                }
            }
        })
        binding.results = vm.leagues
        binding.rvListLeague.adapter = rvAdapter
        listLeagueAdapter = rvAdapter

        val rvSearchAdapter = SearchAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            navController().navigate(
                ListLeagueFragmentDirections.actionListLeagueFragmentToDetailMatchFragment(
                    it.idEvent,
                    it.imageEvent ?: "",
                    "",
                    "",
                    false
                )
            )
        }
        vm.resultSearch.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        binding.rvListLeague.gone()
                        binding.rvSearch.visible()
                        rvSearchAdapter.submitList(it.data)
                    }
                }
            }
        })
        binding.resultSearch = vm.resultSearch
        binding.rvSearch.adapter = rvSearchAdapter
        searchAdapter = rvSearchAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.action_search)
        val actionView = searchView.actionView as SearchView

        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                if (text.isEmpty()) {
                    resetUI()
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                doSearch(query)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun resetUI() {
        binding.rvSearch.gone()
        binding.rvListLeague.visible()
    }

    private fun doSearch(query: String) {
        vm.setQuery(query)
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
