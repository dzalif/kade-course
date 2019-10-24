package com.kucingselfie.kotlindicodingsubmission2.ui.detailmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentDetailMatchBinding
import com.kucingselfie.kotlindicodingsubmission2.model.Result
import com.kucingselfie.kotlindicodingsubmission2.util.invisible
import com.kucingselfie.kotlindicodingsubmission2.util.visible
import kotlinx.android.synthetic.main.fragment_detail_league.progressBar
import kotlinx.android.synthetic.main.fragment_detail_match.*

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : Fragment() {

    private lateinit var binding: FragmentDetailMatchBinding

    private lateinit var idEvent: String
    private var imageEvent: String? = null

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
        imageEvent = DetailMatchFragmentArgs.fromBundle(arguments!!).imageEvent

        displayImageEvent(imageEvent)

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
        })

        return binding.root
    }

    private fun displayImageEvent(imageEvent: String?) {
        imageEvent?.let {
            Glide.with(requireContext()).load(imageEvent)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(binding.imageEvent)
        }
    }

}
