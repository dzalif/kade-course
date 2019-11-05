package com.kucingselfie.kotlindicodingsubmission2.ui.detailmatch


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.db.database
import com.kucingselfie.kotlindicodingsubmission2.model.*
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var binding: FragmentDetailMatchBinding

    private lateinit var idEvent: String
    private var imageEvent: String? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var isNextMatch: Boolean = false
    private var nextMatch: NextMatch? = null
    private var lastMatch: LastMatch? = null

    private val vm: DetailMatchViewModel by lazy {
        ViewModelProviders.of(this).get(DetailMatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMatchBinding.inflate(inflater)

        setHasOptionsMenu(true)

        //Get id event
        idEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).idEvent
        imageEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).imageEvent
        isNextMatch = DetailMatchFragmentArgs.fromBundle(arguments!!).isNextMatch

        displayImageEvent(imageEvent)

        favoriteState()

        vm.getDetailMatch(idEvent)

        vm.status.observe(this, Observer {
            when (it) {
                Result.LOADING -> {
                    progressBar.visible()
                    group.invisible()
                }
                else -> {
                    group.visible()
                    progressBar.invisible()
                }
            }
        })

        vm.detailMatch.observe(this, Observer {
            binding.model = it
            if (isNextMatch) {
                nextMatch = NextMatch(idEvent, it.eventName, imageEvent)
            } else {
                lastMatch = LastMatch(idEvent, it.eventName, imageEvent)
            }
        })

        return binding.root
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

    private fun displayImageEvent(imageEvent: String?) {
        imageEvent?.let {
            Glide.with(requireContext()).load(imageEvent)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(binding.imageEvent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match_menu, menu)
        menuItem = menu
        setFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_active)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_inactive)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) {
                    if (isNextMatch) removeFromNextMatchFavorite()
                    else removeFromLastMatchFavorite()
                } else {
                    if(isNextMatch) addToNextMatchFavorite()
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
                delete(LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE, "(MATCH_ID = {id})", "id" to idEvent)
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromNextMatchFavorite() {
        try {
            context?.database?.use {
                delete(NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE, "(MATCH_ID = {id})", "id" to idEvent)
            }
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
                    LastMatchFavorite.MATCH_PICTURE to lastMatch?.eventImage
                )
            }
            Toast.makeText(requireContext(), "Added to last match favorite", Toast.LENGTH_SHORT).show()
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
                    NextMatchFavorite.MATCH_PICTURE to nextMatch?.eventImage
                    )
            }
            Toast.makeText(requireContext(), "Added to next match favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

}
