package com.kucingselfie.kadesubmission.ui.favorite.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kadesubmission.databinding.FragmentTeamFavoriteBinding
import com.kucingselfie.kadesubmission.db.database
import com.kucingselfie.kadesubmission.model.TeamFavorite
import com.kucingselfie.kadesubmission.ui.favorite.FavoriteFragmentDirections
import com.kucingselfie.kadesubmission.util.invisible
import com.kucingselfie.kadesubmission.util.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TeamFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentTeamFavoriteBinding
    private lateinit var adapter: TeamFavoriteAdapter
    private var favorites: MutableList<TeamFavorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamFavoriteBinding.inflate(inflater)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<TeamFavorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }

        if (favorites.isEmpty()) {
            binding.layoutEmptyData.visible()
        } else {
            binding.layoutEmptyData.invisible()
        }
    }

    private fun initAdapter() {
        adapter = TeamFavoriteAdapter(
            requireContext(),
            favorites
        ) {
            val action =
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailTeamFavoriteFragment(
                    it.teamId!!
                )
            findNavController().navigate(action)
        }
        binding.rvTeam.adapter = adapter
    }
}
