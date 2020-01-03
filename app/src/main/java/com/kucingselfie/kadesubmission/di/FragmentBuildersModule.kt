package com.kucingselfie.kadesubmission.di

import com.kucingselfie.kadesubmission.ui.detailleague.DetailLeagueFragment
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeListLeagueFragment(): ListLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailLeagueFragment(): DetailLeagueFragment
}