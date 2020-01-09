package com.kucingselfie.kadesubmission.ui.listleague

import android.view.KeyEvent
import androidx.databinding.DataBindingComponent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.binding.FragmentBindingAdapters
import com.kucingselfie.kadesubmission.common.Result
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.testing.SingleFragmentActivity
import com.kucingselfie.kadesubmission.util.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class ListLeagueFragmentTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        ActivityTestRule(SingleFragmentActivity::class.java)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    private lateinit var viewModel: ListLeagueViewModel
    private val result = MutableLiveData<Result<List<Search>>>()
    private val resultLeague = MutableLiveData<Result<List<League>>>()
    private lateinit var mockBindingAdapter: FragmentBindingAdapters
    private val fragment = ListLeagueFragment()

    @Before
    fun setUp() {
        viewModel = mock(ListLeagueViewModel::class.java)
        `when`(viewModel.resultSearch).thenReturn(result)
        `when`(viewModel.leagues).thenReturn(resultLeague)
        mockBindingAdapter = mock(FragmentBindingAdapters::class.java)
        fragment.appExecutors = countingAppExecutors.appExecutors
        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        fragment.dataBindingComponent = object : DataBindingComponent {
            override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
                return mockBindingAdapter
            }
        }
        activityRule.activity.setFragment(fragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rvSearch)
    }

    @Test
    fun search() {
        //Perform type to search input
        onView(withId(R.id.searchInput)).perform(
            typeText("arsenal"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        //Check if loading result is set and progress bar is displayed
        result.postValue(Result.Loading(null))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        //Check if result is success
        val match = mutableListOf<Search>()
        match.add(
            Search(
                "0",
                "foo",
                "bar",
                "buzz",
                "",
                "arsenal",
                "chelsea",
                "",
                "",
                ""
            )
        )
        result.postValue(Result.Success(match))
        onView(listMatcher().atPosition(0))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("arsenal"))))

        //Check recyclerview league is hiding
        onView(withId(R.id.rvListLeague))
            .check(matches(not(isDisplayed())))

        //Check recyclerview search is showing
        onView(withId(R.id.rvSearch))
            .check(matches(isDisplayed()))
    }
}