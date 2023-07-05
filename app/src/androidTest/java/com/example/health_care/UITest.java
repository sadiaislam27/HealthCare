package com.example.health_care;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class UITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAppName() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
    }

    @Test
    public void addData() {

        SystemClock.sleep(3000);
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.heartRate)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.date)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.time)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.comment)).perform(ViewActions.typeText("Checking UI Test"));
        Espresso.pressBack(); //hide keyboard
        onView(withId(R.id.save)).perform(click());

    }

}
