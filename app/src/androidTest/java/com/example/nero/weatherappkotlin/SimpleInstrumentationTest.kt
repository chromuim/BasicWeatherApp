package com.example.nero.weatherappkotlin

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.nero.weatherappkotlin.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

//@RunWith(AndroidJUnit4::class)
class SimpleInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun itemClick_navigateToDetail() {
        onView(withId(R.id.forecastList)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.weatherDescription))
                .check(matches(isAssignableFrom(TextView::class.java)))
    }
}