package com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentLastMatchFavoriteBinding
import com.kucingselfie.kotlindicodingsubmission2.db.database
import com.kucingselfie.kotlindicodingsubmission2.model.LastMatchFavorite
import com.kucingselfie.kotlindicodingsubmission2.ui.previousmatch.LastMatchAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class LastMatchFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentLastMatchFavoriteBinding
    private lateinit var adapter: LastMatchAdapter
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
    }

    private fun initAdapter() {
        adapter = LastMatchAdapter(requireContext(), favorites) {
//            val action = PreviousMatchFragmentDirections.actionPreviousMatchFragmentToDetailMatchFragment(it.id!!, it.eventImage!!, false)
//            findNavController().navigate(action)
        }
        binding.rvLastMatchFavorite.adapter = adapter
    }


}
