package com.kucingselfie.kotlindicodingsubmission2.ui.detailmatch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kucingselfie.kotlindicodingsubmission2.api.response.DetailMatchResponse

import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.DetailMatch
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.ui.nextmatch.NextMatchFragmentArgs
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.*
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_detail_match.*

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var binding: FragmentDetailMatchBinding

    private lateinit var idEvent: String

    private val vm: DetailMatchViewModel by lazy {
        ViewModelProviders.of(this).get(DetailMatchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMatchBinding.inflate(inflater)

        //Get id event
        idEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).idEvent

        vm.getDetailMatch(idEvent)

        vm.status.observe(this, Observer {
            when(it) {
                Result.LOADING -> {
                    progressBar.visible()
                    group.invisible()
                }
                else -> {
                    progressBar.invisible()
                    group.visible()
                }
            }
        })

        vm.detailMatch.observe(this, Observer {
            binding.model = it
        })

        return binding.root
    }

}
