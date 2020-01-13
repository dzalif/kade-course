package com.kucingselfie.kadesubmission.di

import com.kucingselfie.kadesubmission.ui.detailleague.DetailLeagueFragment
import com.kucingselfie.kadesubmission.ui.detailmatch.DetailMatchFragment
import com.kucingselfie.kadesubmission.ui.listleague.ListLeagueFragment
import com.kucingselfie.kadesubmission.ui.match.MatchFragment
import com.kucingselfie.kadesubmission.ui.match.chooseleague.ChooseLeagueFragment
import com.kucingselfie.kadesubmission.ui.match.nextmatch.NextMatchFragment
import com.kucingselfie.kadesubmission.ui.match.previousmatch.PreviousMatchFragment
import com.kucingselfie.kadesubmission.ui.match.standings.StandingsFragment
import com.kucingselfie.kadesubmission.ui.match.team.TeamFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeListLeagueFragment(): ListLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailLeagueFragment(): DetailLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributeChooseLeagueFragment(): ChooseLeagueFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchFragment(): MatchFragment

    @ContributesAndroidInjector
    abstract fun contributeNextMatchFragment(): NextMatchFragment

    @ContributesAndroidInjector
    abstract fun contributePreviousMatchFragment(): PreviousMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailMatchFragment(): DetailMatchFragment

    @ContributesAndroidInjector
    abstract fun contributeStandingsFragment(): StandingsFragment

    @ContributesAndroidInjector
    abstract fun contributeTeamFragment(): TeamFragment
}