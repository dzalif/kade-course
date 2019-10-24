package com.kucingselfie.kotlindicodingsubmission2.ui.listleague


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentListLeagueBinding
import com.kucingselfie.kotlindicodingsubmission2.model.League
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.model.Search
import com.kucingselfie.kotlindicodingsubmission2.ui.searchevent.SearchAdapter
import com.kucingselfie.kotlindicodingsubmission2.ui.searchevent.SearchViewModel
import com.kucingselfie.kotlindicodingsubmission2.util.gone
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*

/**
 * A simple [Fragment] subclass.
 */
class ListLeagueFragment : Fragment() {

    private lateinit var adapter: SearchAdapter

    private var items: MutableList<League> = mutableListOf()

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
        initSearchAdapter()

        binding.listLeague.adapter =
            ListLeagueAdapter(items) {
                findNavController().navigate(R.id.action_listLeagueFragment_to_detailLeagueFragment)
            }

        vm.status.observe(this, Observer {
            when (it) {
                Result.LOADING -> {
                    progressBar.visible()
                }
                else -> {
                    progressBar.invisible()
                    binding.rvSearch.visible()
                }
            }
        })

        vm.search.observe(this, Observer {
            if (it.isNotEmpty()) {
                displayData(it)
            }
        })

        return binding.root
    }

    private fun displayData(it: MutableList<Search>) {
        items.clear()
        itemsResult.clear()
        adapter.refreshData(it)
    }

    private fun initSearchAdapter() {
        adapter = SearchAdapter(requireContext(), itemsResult) {
            val action = ListLeagueFragmentDirections.actionListLeagueFragmentToDetailMatchFragment(
                it.idEvent,
                it.imageEvent ?: ""
            )
            findNavController().navigate(action)
        }
        binding.rvSearch.adapter = adapter
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
        val name = resources.getStringArray(R.array.league)
        items.clear()
        for (i in name.indices) {
            items.add(League(name[i]))
        }
    }
}
