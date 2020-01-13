package com.kucingselfie.kadesubmission.ui.listleague


import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
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
import com.kucingselfie.kadesubmission.databinding.FragmentListLeagueBinding
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.testing.OpenForTesting
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.visible
import javax.inject.Inject


@OpenForTesting
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

        initSearchInput()

        vm.leagues.observe(this, Observer {
            it?.let {
                when(it) {
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

    private fun initSearchInput() {
        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            if (text.toString().isEmpty()) {
                resetUI()
            }
        }
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

    private fun resetUI() {
        binding.rvSearch.gone()
        binding.rvListLeague.visible()
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

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}
