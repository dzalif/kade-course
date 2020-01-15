package com.kucingselfie.kadesubmission.ui.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentDetailTeamBinding
import com.kucingselfie.kadesubmission.db.database
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.model.TeamFavorite
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.autoCleared
import com.kucingselfie.kadesubmission.util.gone
import com.kucingselfie.kadesubmission.util.visible
import kotlinx.android.synthetic.main.fragment_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailTeamFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentDetailTeamBinding>()

    private lateinit var idTeam: String
    private val vm: DetailTeamViewModel by viewModels { viewModelFactory }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail_team, container, false, dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        //Get id league
        idTeam = arguments?.getString("idTeam")!!
        vm.setIdTeam(idTeam)
        favoriteState()
        observeData()
    }

    private fun favoriteState() {
        context?.database?.use {
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
                .whereArgs("(TEAM_ID) = {id}", "id" to idTeam)
            val favorite = result.parseList(classParser<TeamFavorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun observeData() {
        binding.results = vm.team
        vm.team.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {
                        group.gone()
                        setEnabledMenuFavorite(false)
                    }
                    is Result.Success -> {
                        group.visible()
                        setEnabledMenuFavorite(true)
                        val data = it.data?.get(0)
                        vm.setData(data)
                        binding.model = data
                    }
                }
            }
        })
    }

    private fun setEnabledMenuFavorite(menuFavorite: Boolean) {
        menuItem?.getItem(0)?.isEnabled = menuFavorite
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match_menu, menu)
        menuItem = menu
        setFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                   addToFavorite()
                }
                isFavorite = !isFavorite
                setFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        val data = vm.getData()
        try {
            context?.database?.use {
                insert(
                    TeamFavorite.TABLE_TEAM_FAVORITE,
                    TeamFavorite.TEAM_ID to data?.teamId,
                    TeamFavorite.TEAM_NAME to data?.team,
                    TeamFavorite.TEAM_BADGE to data?.strTeamBadge
                )
            }
            showSnackbar(R.string.added_to_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            context?.database?.use {
                delete(
                    TeamFavorite.TABLE_TEAM_FAVORITE,
                    "(TEAM_ID = {id})",
                    "id" to idTeam
                )
            }
            showSnackbar(R.string.removed_from_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSnackbar(message: Int) {
        Snackbar.make(
            binding.constraintLayout,
            getString(message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_active)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_inactive)
    }
}
