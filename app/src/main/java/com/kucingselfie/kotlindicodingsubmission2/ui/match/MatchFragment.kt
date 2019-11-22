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
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.ui.match.nextmatch.NextMatchFragment
import com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch.PreviousMatchFragment

/**
 * A simple [Fragment] subclass.
 */

class MatchFragment : Fragment() {

    private lateinit var binding: FragmentMatchBinding
    private lateinit var viewPager : ViewPager
    private lateinit var viewPagerAdapter : ViewPagerFragment
    private lateinit var tabLayout : TabLayout
    private lateinit var idLeague: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchBinding.inflate(inflater)
        binding.executePendingBindings()
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        //Get id event
        idLeague = MatchFragmentArgs.fromBundle(arguments!!).idLeague

        val nextMatchFragment = NextMatchFragment()
        val previousMatchFragment = PreviousMatchFragment()

        //Send idLeague from Match Fragment to Next Match Fragment and Previous Match Fragment
        val bundle = Bundle()
        bundle.putString("idLeague", idLeague)
        nextMatchFragment.arguments = bundle
        previousMatchFragment.arguments = bundle

        viewPagerAdapter =
            ViewPagerFragment(childFragmentManager, requireContext(), nextMatchFragment, previousMatchFragment)
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager,true)
        return binding.root
    }

    class ViewPagerFragment(fm: FragmentManager, context: Context, nextMatchFragment: NextMatchFragment, previousMatchFragment: PreviousMatchFragment): FragmentPagerAdapter(fm) {
        private val tabNextMatch = context.resources.getString(R.string.next_match)
        private val tabLastMatch = context.resources.getString(R.string.previous_match)

        private val tabTitles = arrayOf(tabNextMatch, tabLastMatch)

        private val pages = listOf(
            nextMatchFragment,
            previousMatchFragment
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
