package com.kucingselfie.kadesubmission.ui.detailteam

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingselfie.kadesubmission.R

class DetailTeamFragment : Fragment() {

    companion object {
        fun newInstance() = DetailTeamFragment()
    }

    private lateinit var viewModel: DetailTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailTeamViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
