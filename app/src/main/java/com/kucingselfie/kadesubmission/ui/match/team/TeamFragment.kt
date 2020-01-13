package com.kucingselfie.kadesubmission.ui.match.team

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.visible
import kotlinx.android.synthetic.main.fragment_team.*
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
        initSearchInput()
        initAdapter()
        observeData()
    }

    private fun initSearchInput() {
        binding.searchInput.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        binding.searchInput.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(v: View) {
        val query = binding.searchInput.text.toString()
        // Dismiss keyboard
        dismissKeyboard(v.windowToken)
        vm.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun observeData() {
        vm.teams.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Success -> {
                        progressBar.gone()
                        adapter.submitList(it.data)
                    }
                }
            }
        })

        vm.resultSearch.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Success -> {
                        progressBar.visible()
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
        binding.resultSearch = vm.resultSearch
        binding.rvTeam.adapter = rvAdapter
        adapter = rvAdapter
    }
}
