package com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentNextMatchFavoriteBinding
import com.kucingselfie.kotlindicodingsubmission2.db.database
import com.kucingselfie.kotlindicodingsubmission2.model.NextMatchFavorite
import com.kucingselfie.kotlindicodingsubmission2.ui.nextmatch.NextMatchAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentNextMatchFavoriteBinding
    private lateinit var adapter: NextMatchAdapter
    private var favorites: MutableList<NextMatchFavorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentNextMatchFavoriteBinding.inflate(inflater)
        binding.executePendingBindings()

        initAdapter()
        showFavorite()

        return binding.root
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            val result = select(NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE)
            val favorite = result.parseList(classParser<NextMatchFavorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        adapter = NextMatchAdapter(requireContext(), favorites) {
//            val action = NextMatchFavoriteFragmentDirections.actionNextMatchFavoriteFragmentToDetailMatchFragment(it.id!!, it.eventImage ?: "", true)
//            findNavController().navigate(action)
        }
        binding.rvNextMatchFavorite.adapter = adapter
    }

}
