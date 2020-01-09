package com.kucingselfie.kadesubmission.util

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry


fun withMenuIdOrText(@IdRes id: Int, @StringRes menuText: Int): org.hamcrest.Matcher<View> {
    val matcher = withId(id)
    try {
        onView(matcher).check(matches(isDisplayed()))
        return matcher
    } catch (NoMatchingViewException: Exception) {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        return withText(menuText)
    }
}