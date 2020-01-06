package com.kucingselfie.kadesubmission.ui.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.android.material.snackbar.Snackbar
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentDataBindingComponent
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.databinding.FragmentDetailMatchBinding
import com.kucingselfie.kadesubmission.db.database
import com.kucingselfie.kadesubmission.di.Injectable
import com.kucingselfie.kadesubmission.model.*
import com.kucingselfie.kadesubmission.util.autoCleared
import com.kucingselfie.kadesubmission.util.invisible
import com.kucingselfie.kadesubmission.util.toGMT7
import com.kucingselfie.kadesubmission.util.visible
import kotlinx.android.synthetic.main.fragment_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailMatchFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentDetailMatchBinding>()

    private lateinit var idEvent: String
    private var imageEvent: String? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var isNextMatch: Boolean = false
    private var nextMatch: NextMatch? = null
    private var lastMatch: LastMatch? = null
    private lateinit var teamHomeId: String
    private lateinit var teamAwayId: String

    private val vm: DetailMatchViewModel by viewModels { viewModelFactory }

    companion object {
        const val GK = "(GK)"
        const val FW = "(FW)"
        const val MD = "(MD)"
        const val DF = "(DF)"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail_match, container, false, dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        getIntentData()
        favoriteState()
        observeData()
    }

    private fun observeData() {
        //Enable cross fade transition on glide
        val transition = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        vm.detailMatch.observe(this, Observer {
            it?.let {
                when(it) {
                    is Result.Loading -> {
                        group.invisible()
                        setEnabledMenuFavorite(false)
                    }
                    is Result.Success -> {
                        group.visible()
                        setEnabledMenuFavorite(true)
                        val data = it.data?.get(0)
                        setDetailMatch(data)
                        //Bind model data
                        binding.model = data
                        //Concat date and match time
                        val formattedMatchTime = data?.let { match -> formatDate(match) }
                        //Get team badge
                        if (isNextMatch) {
                            nextMatch = NextMatch(idEvent, data?.eventName, imageEvent, formattedMatchTime)
                        } else {
                            lastMatch = LastMatch(idEvent, data?.eventName, imageEvent, formattedMatchTime)
                        }
                    }
                    is Result.Error -> {
                        showSnackbar(R.string.unknown_error)
                    }
                }
            }
            binding.results = vm.detailMatch
        })

        vm.detailHomeTeam.observe(this, Observer {
            it?.let {
                val homeTeamBadge = it.data?.get(0)?.strTeamBadge
                homeTeamBadge?.let {
                    displayTeamLogo(homeTeamBadge, binding.homeTeamBadge, transition)
                }
            }
        })

        vm.detailAwayTeam.observe(this, Observer {
            it?.let {
                val awayTeamBadge = it.data?.get(0)?.strTeamBadge
                awayTeamBadge?.let {
                    displayTeamLogo(awayTeamBadge, binding.awayTeamBadge, transition)
                }
            }
        })
    }

    private fun setDetailMatch(data: DetailMatch?) : DetailMatch {
        return DetailMatch(
            data?.dateEvent,
            data?.idEvent,
            data?.eventName,
            data?.intAwayScore,
            data?.intHomeScore,
            data?.strAwayTeam,
            data?.strHomeTeam,
            data?.strTime?.toGMT7() ?: "",
            data?.strHomeFormation ?: "",
            data?.strAwayFormation ?: "",
            data?.strHomeGK + GK,
            data?.strHomeFW + FW,
            data?.strHomeMD + MD,
            data?.strHomeDF + DF,
            data?.strAwayGK + GK,
            data?.strAwayFW + FW,
            data?.strAwayMD + MD,
            data?.strAwayDF + DF
        )
    }

    private fun getIntentData() {
        arguments?.let {
            idEvent = DetailMatchFragmentArgs.fromBundle(it).idEvent
            imageEvent = DetailMatchFragmentArgs.fromBundle(it).imageEvent
            isNextMatch = DetailMatchFragmentArgs.fromBundle(it).isNextMatch
            teamHomeId = DetailMatchFragmentArgs.fromBundle(it).idHomeTeam
            teamAwayId = DetailMatchFragmentArgs.fromBundle(it).idAwayTeam
        }
        setViewModelData(idEvent, teamHomeId, teamAwayId)
    }

    private fun setViewModelData(idEvent: String, teamHomeId: String, teamAwayId: String) {
        vm.setIdEvent(idEvent)
        vm.setTeamHomeId(teamHomeId)
        vm.setTeamAwayId(teamAwayId)
    }

    private fun displayTeamLogo(teamUrl: String, teamImage: ImageView, transition: DrawableCrossFadeFactory) {
        Glide.with(requireContext()).load(teamUrl)
            .transition(withCrossFade(transition))
            .into(teamImage)
    }

    private fun formatDate(it: DetailMatch): String? {
        //Do concat if match time is not null, otherwise just save only a date
        return if (it.strTime.isNullOrEmpty()) it.dateEvent
        else "${it.dateEvent} ${it.strTime.toGMT7()}"
    }

    private fun setEnabledMenuFavorite(menuFavorite: Boolean) {
        menuItem?.getItem(0)?.isEnabled = menuFavorite
    }

    private fun showSnackbar(message: Int) {
        Snackbar.make(
            constraintLayout,
            getString(message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun favoriteState() {
        if (isNextMatch) {
            context?.database?.use {
                val result = select(NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE)
                    .whereArgs("(MATCH_ID) = {id}", "id" to idEvent)
                val favorite = result.parseList(classParser<NextMatchFavorite>())
                if (favorite.isNotEmpty()) isFavorite = true
            }
        } else {
            context?.database?.use {
                val result = select(LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE)
                    .whereArgs("(MATCH_ID) = {id}", "id" to idEvent)
                val favorite = result.parseList(classParser<LastMatchFavorite>())
                if (favorite.isNotEmpty()) isFavorite = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match_menu, menu)
        menuItem = menu
        setFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setFavorite() {
        changeStateMenuIcon()
    }

    private fun changeStateMenuIcon() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_active)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_inactive)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) {
                    if (isNextMatch) removeFromNextMatchFavorite()
                    else removeFromLastMatchFavorite()
                } else {
                    if (isNextMatch) addToNextMatchFavorite()
                    else addToLastMatchFavorite()
                }
                isFavorite = !isFavorite
                setFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeFromLastMatchFavorite() {
        try {
            context?.database?.use {
                delete(
                    LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE,
                    "(MATCH_ID = {id})",
                    "id" to idEvent
                )
            }
            showSnackbar(R.string.removed_from_previous_match_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromNextMatchFavorite() {
        try {
            context?.database?.use {
                delete(
                    NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE,
                    "(MATCH_ID = {id})",
                    "id" to idEvent
                )
            }
            showSnackbar(R.string.removed_from_next_match_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToLastMatchFavorite() {
        try {
            context?.database?.use {
                insert(
                    LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE,
                    LastMatchFavorite.MATCH_ID to lastMatch?.id,
                    LastMatchFavorite.MATCH_NAME to lastMatch?.event,
                    LastMatchFavorite.MATCH_PICTURE to lastMatch?.eventImage,
                    LastMatchFavorite.MATCH_TIME to lastMatch?.dateEvent,
                    LastMatchFavorite.HOME_TEAM_ID to teamHomeId,
                    LastMatchFavorite.AWAY_TEAM_ID to teamAwayId
                )
            }
            showSnackbar(R.string.added_to_previous_match_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToNextMatchFavorite() {
        try {
            context?.database?.use {
                insert(
                    NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE,
                    NextMatchFavorite.MATCH_ID to nextMatch?.id,
                    NextMatchFavorite.MATCH_NAME to nextMatch?.event,
                    NextMatchFavorite.MATCH_PICTURE to nextMatch?.eventImage,
                    NextMatchFavorite.MATCH_TIME to nextMatch?.dateEvent,
                    NextMatchFavorite.HOME_TEAM_ID to teamHomeId,
                    NextMatchFavorite.AWAY_TEAM_ID to teamAwayId
                )
            }
            showSnackbar(R.string.added_to_next_match_favorite)
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

}
