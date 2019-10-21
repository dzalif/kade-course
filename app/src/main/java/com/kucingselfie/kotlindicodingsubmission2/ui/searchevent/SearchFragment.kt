package com.kucingselfie.kotlindicodingsubmission2.ui.searchevent


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentSearchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.model.Search
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*


class SearchFragment : Fragment() {

    private lateinit var adapter: SearchAdapter

    private var items: MutableList<Search> = mutableListOf()

    lateinit var binding: FragmentSearchBinding

    private val vm: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)

        setHasOptionsMenu(true)

        initAdapter()

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> { progressBar.visible() }
                else -> { progressBar.invisible() }
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
        adapter.refreshData(it)
    }

    private fun initAdapter() {
        adapter = SearchAdapter(items)
        binding.rvSearch.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = menu.findItem(R.id.action_search)
        val actionView = searchView.actionView as SearchView

        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                if (text.isEmpty()) {
                    adapter.clear()
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

    private fun doSearch(query: String) {
        vm.doSearch(query)
    }

}
