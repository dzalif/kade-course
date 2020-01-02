package com.kucingselfie.kadesubmission.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueViewModel
import com.kucingselfie.kadesubmission.ui.searchevent.SearchViewModel
import com.kucingselfie.kadesubmission.util.FootballViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListLeagueViewModel::class)
    abstract fun bindListLeagueViewModel(viewModel: ListLeagueViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FootballViewModelFactory): ViewModelProvider.Factory
}