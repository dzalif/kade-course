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
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.databinding.FragmentListLeagueBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import javax.inject.Inject


class ListLeagueFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentListLeagueBinding>()
//    private var adapter by autoCleared<SearchAdapter>()
    private var listLeagueAdapter by autoCleared<ListLeagueAdapter>()

    private var items: MutableList<League> = mutableListOf()
    private var itemsResult: MutableList<Search> = mutableListOf()

    private val vm: ListLeagueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_league, container, false, dataBindingComponent
        )

        setHasOptionsMenu(true)
        initAdapter()

        //Observe search data
//        vm.search.observe(this, Observer {
//            it?.let {
//                if (it.isNotEmpty()) {
//                    binding.rvSearch.visible()
//                    binding.listLeague.gone()
//                    displayData(it)
//                }
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val rvAdapter = ListLeagueAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) {
            //TODO: navigate
        }
        vm.leagues.observe(this, Observer {
            it?.let {
                listLeagueAdapter.submitList(it.data)
            }
        })
        binding.results = vm.leagues
        binding.rvListLeague.adapter = rvAdapter
        listLeagueAdapter = rvAdapter
    }

    private fun displayData(it: MutableList<Search>) {
        items.clear()
        itemsResult.clear()
        itemsResult.addAll(it)
//        adapter.refreshData(itemsResult)
    }

    private fun initAdapter() {
//        adapter = SearchAdapter(requireContext(), itemsResult) {
//            val action = ListLeagueFragmentDirections.actionListLeagueFragmentToDetailMatchFragment(
//                it.idEvent,
//                it.imageEvent ?: "",
//                "",
//                "",
//                false
//            )
//            findNavController().navigate(action)
//        }
//        binding.rvSearch.adapter = adapter
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
//        adapter.clear()
//        binding.rvSearch.gone()
    }

    private fun doSearch(query: String) {
//        vm.doSearch(query)
    }
}
