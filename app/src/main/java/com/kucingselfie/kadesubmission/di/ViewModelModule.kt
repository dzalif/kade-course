package com.kucingselfie.kadesubmission.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kucingselfie.kadesubmission.ui.detailleague.DetailLeagueViewModel
import com.kucingselfie.kadesubmission.ui.detailmatch.DetailMatchViewModel
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueViewModel
import com.kucingselfie.kadesubmission.ui.match.chooseleague.ChooseLeagueViewModel
import com.kucingselfie.kadesubmission.ui.match.nextmatch.NextMatchViewModel
import com.kucingselfie.kadesubmission.ui.match.previousmatch.PreviousMatchViewModel
import com.kucingselfie.kadesubmission.ui.match.standings.StandingsViewModel
import com.kucingselfie.kadesubmission.ui.match.team.TeamViewModel
import com.kucingselfie.kadesubmission.util.FootballViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListLeagueViewModel::class)
    abstract fun bindListLeagueViewModel(viewModel: ListLeagueViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailLeagueViewModel::class)
    abstract fun bindDetailLeagueViewModel(viewModel: DetailLeagueViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseLeagueViewModel::class)
    abstract fun bindChooseLeagueViewModel(viewModel: ChooseLeagueViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NextMatchViewModel::class)
    abstract fun bindNextMatchViewModel(viewModel: NextMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PreviousMatchViewModel::class)
    abstract fun bindPreviousMatchViewModel(viewModel: PreviousMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMatchViewModel::class)
    abstract fun bindDetailMatchViewModel(viewModel: DetailMatchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StandingsViewModel::class)
    abstract fun bindStandingsViewModel(viewModel: StandingsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    abstract fun bindTeamViewModel(viewModel: TeamViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FootballViewModelFactory): ViewModelProvider.Factory
}