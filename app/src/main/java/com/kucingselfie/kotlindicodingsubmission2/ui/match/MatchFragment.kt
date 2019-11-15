package com.kucingselfie.kotlindicodingsubmission2.ui.match


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentFavoriteMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch.FavoriteMatchFragment
import com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch.NextMatchFavoriteFragment
import com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch.PreviousMatchFavoriteFragment
import com.kucingselfie.kotlindicodingsubmission2.ui.match.nextmatch.NextMatchFragment
import com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch.PreviousMatchFragment
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchBinding.inflate(inflater)
        binding.executePendingBindings()
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPagerAdapter =
            ViewPagerFragment(childFragmentManager, requireContext())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)
        return binding.root
    }

    class ViewPagerFragment(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {
        private val tabNextMatch = context.resources.getString(R.string.next_match)
        private val tabLastMatch = context.resources.getString(R.string.previous_match)

        private val tabTitles = arrayOf(tabNextMatch, tabLastMatch)

        private val pages = listOf(
            NextMatchFragment(),
            PreviousMatchFragment()
        )
        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {

            return pages.size
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return tabTitles[position]
        }
    }

}
