package com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentLastMatchFavoriteBinding
import com.kucingselfie.kotlindicodingsubmission2.db.database
import com.kucingselfie.kotlindicodingsubmission2.model.LastMatchFavorite
import com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch.PreviousMatchAdapter
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentLastMatchFavoriteBinding
    private lateinit var adapter: PreviousMatchAdapter
    private var favorites: MutableList<LastMatchFavorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLastMatchFavoriteBinding.inflate(inflater)
        binding.executePendingBindings()

        initAdapter()
        showFavorite()

        return binding.root
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE)
            val favorite = result.parseList(classParser<LastMatchFavorite>())
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
        adapter =
            PreviousMatchAdapter(
                requireContext(),
                favorites
            ) {
                val action =
                    FavoriteMatchFragmentDirections.actionFavoriteMatchFragmentToDetailMatchFragment(
                        it.matchId!!,
                        it.matchPicture ?: "",
                        "",
                        "",
                        true
                    )
                findNavController().navigate(action)
            }
        binding.rvLastMatchFavorite.adapter = adapter
    }


}
