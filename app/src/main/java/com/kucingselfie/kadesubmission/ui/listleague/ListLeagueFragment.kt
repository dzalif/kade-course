package com.kucingselfie.kadesubmission.ui.listleague


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.FragmentListLeagueBinding
import com.kucingselfie.kadesubmission.model.DetailLeague
import com.kucingselfie.kadesubmission.model.Result
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.ui.searchevent.SearchAdapter
import com.kucingselfie.kadesubmission.ui.searchevent.SearchViewModel
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.invisible
import com.kucingselfie.kadesubmission.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar


class ListLeagueFragment : Fragment() {

    private lateinit var adapter: SearchAdapter
    private lateinit var listLeagueAdapter: ListLeagueAdapter

    private var items: MutableList<DetailLeague> = mutableListOf()

    private var itemsResult: MutableList<Search> = mutableListOf()

    lateinit var binding: FragmentListLeagueBinding

    private val vm: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListLeagueBinding.inflate(inflater)
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        initData()
        initAdapter()
        observeData()

        vm.status.observe(this, Observer {
            when (it) {
                Result.LOADING -> {
                    progressBar.visible()
                }
                else -> {
                    progressBar.invisible()
                }
            }
        })

        //Observe search data
        vm.search.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    binding.rvSearch.visible()
                    binding.listLeague.gone()
                    displayData(it)
                }
            }
        })

        return binding.root
    }

    private fun observeData() {
        //Observe list league data
        vm.listLeague.observe(this, Observer {
            it?.let {
                binding.rvSearch.gone()
                binding.listLeague.visible()
                displayListLeague(it)
            }
        })
    }

    private fun displayListLeague(it: List<DetailLeague>) {
        items.clear()
        itemsResult.clear()
        items.addAll(it)
        listLeagueAdapter.refreshData(items)
    }

    private fun displayData(it: MutableList<Search>) {
        items.clear()
        itemsResult.clear()
        itemsResult.addAll(it)
        adapter.refreshData(itemsResult)
    }

    private fun initAdapter() {
        adapter = SearchAdapter(requireContext(), itemsResult) {
            val action = ListLeagueFragmentDirections.actionListLeagueFragmentToDetailMatchFragment(
                it.idEvent,
                it.imageEvent ?: "",
                "",
                "",
                false
            )
            findNavController().navigate(action)
        }
        binding.rvSearch.adapter = adapter

        listLeagueAdapter = ListLeagueAdapter(requireContext(), items) {
            val action =
                ListLeagueFragmentDirections.actionListLeagueFragmentToDetailLeagueFragment(it.id)
            findNavController().navigate(action)
        }
        binding.listLeague.adapter = listLeagueAdapter
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
        adapter.clear()
        initData()
        binding.rvSearch.gone()
    }

    private fun doSearch(query: String) {
        vm.doSearch(query)
    }

    private fun initData() {
        vm.getListLeague()
    }
}
