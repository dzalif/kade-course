package com.kucingselfie.kotlindicodingsubmission2.ui.favoritematch


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentFavoriteMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMatchBinding
    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteMatchBinding.inflate(inflater)
        binding.executePendingBindings()
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        viewPagerAdapter = ViewPagerFragment(childFragmentManager, requireContext())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)

        return binding.root
    }

    class ViewPagerFragment(fm: FragmentManager, context: Context): FragmentPagerAdapter(fm) {
        private val tabNextMatch = context.resources.getString(R.string.next_match)
        private val tabLastMatch = context.resources.getString(R.string.previous_match)

        private val tabTitles = arrayOf(tabNextMatch, tabLastMatch)

        private val pages = listOf(
            NextMatchFavoriteFragment(),
            PreviousMatchFavoriteFragment()
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
