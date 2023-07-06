package com.example.health_care;

/*
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
*/
/*
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.health_care.R;
import com.example.health_care.Storing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UITest {

    private static final Store MOCK_STORE_DATA = new Store("80", "120", "80", "10:30", "07/06/2023", "Sample comment");

        @Before
        public void launchActivity () {
            ActivityScenario.launch(Storing.class);
        }

        @Test
        public void testSaveButton () {
            // Perform actions
            Espresso.onView(ViewMatchers.withId(R.id.heartRate))
                    .perform(ViewActions.typeText("80")); // Enter heart rate
            Espresso.onView(ViewMatchers.withId(R.id.systolic))
                    .perform(ViewActions.typeText("120")); // Enter systolic value
            Espresso.onView(ViewMatchers.withId(R.id.diastolic))
                    .perform(ViewActions.typeText("80")); // Enter diastolic value
            Espresso.onView(ViewMatchers.withId(R.id.date))
                    .perform(ViewActions.typeText("07/06/2023")); // Enter date
            Espresso.onView(ViewMatchers.withId(R.id.time))
                    .perform(ViewActions.typeText("10:30")); // Enter time
            Espresso.onView(ViewMatchers.withId(R.id.comment))
                    .perform(ViewActions.typeText("Noc Comment")); // Enter comment
            Espresso.closeSoftKeyboard();
            Espresso.onView(ViewMatchers.withId(R.id.save))
                    .perform(ViewActions.click()); // Click the save button

            // You can add assertions here to verify the expected behavior after saving the data
        }


    @Test
    public void testUpdateButton() {
        // Pass the mock store data through Intent
        ActivityScenario.launch(Storing.class).onActivity(activity -> {
            Intent intent = new Intent(activity, Storing.class);
            intent.putExtra("store", MOCK_STORE_DATA);
            activity.setIntent(intent);
        });

        // Perform actions
        Espresso.onView(ViewMatchers.withId(R.id.heartRate))
                .perform(ViewActions.replaceText("90")); // Update heart rate
        Espresso.onView(ViewMatchers.withId(R.id.systolic))
                .perform(ViewActions.replaceText("130")); // Update systolic value
        Espresso.onView(ViewMatchers.withId(R.id.diastolic))
                .perform(ViewActions.replaceText("85")); // Update diastolic value
        Espresso.onView(ViewMatchers.withId(R.id.date))
                .perform(ViewActions.replaceText("07/07/2023")); // Update date
        Espresso.onView(ViewMatchers.withId(R.id.time))
                .perform(ViewActions.replaceText("11:30")); // Update time
        Espresso.onView(ViewMatchers.withId(R.id.comment))
                .perform(ViewActions.replaceText("Updated comment")); // Update comment
        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.save))
                .perform(ViewActions.click()); // Click the save button

        // You can add assertions here to verify the expected behavior after updating the data
    }



  //      }



import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.core.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UITest {

    private static final Store MOCK_STORE_DATA = new Store("80", "120", "80", "10:30", "07/06/2023", "Sample comment");

    @Rule
    public ActivityScenarioRule<RetreiveDataActivity> activityScenarioRule = new ActivityScenarioRule<>(RetreiveDataActivity.class);

    @Before
    public void setup() {
        // Initialize Intents
        Intents.init();
    }

    @After
    public void teardown() {
        // Release Intents
        Intents.release();
    }

    @Test
    public void testSaveButton() {
        // Perform actions in the RetrieveDataActivity
        Espresso.onView(ViewMatchers.withId(R.id.add))
                .perform(ViewActions.click()); // Click the "Add" button

        // Verify that the Storing activity is launched
        intending(hasComponent(Storing.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        // Perform actions in the Storing activity
        Espresso.onView(withId(R.id.heartRate))
                .perform(ViewActions.typeText("80")); // Enter heart rate
        Espresso.onView(withId(R.id.systolic))
                .perform(ViewActions.typeText("120")); // Enter systolic value
        Espresso.onView(withId(R.id.diastolic))
                .perform(ViewActions.typeText("80")); // Enter diastolic value
        Espresso.onView(withId(R.id.date))
                .perform(ViewActions.typeText("07/06/2023")); // Enter date
        Espresso.onView(withId(R.id.time))
                .perform(ViewActions.typeText("10:30")); // Enter time
        Espresso.onView(withId(R.id.comment))
                .perform(ViewActions.typeText("Sample comment")); // Enter comment
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.save))
                .perform(ViewActions.click()); // Click the save button

        // You can add assertions here to verify the expected behavior after saving the data
    }

    // Other test methods for update and delete buttons



    @Test
    public void testUpdateButton() {
        // Pass the mock store data to the activity using intent
        ActivityScenario.launch(RetreiveDataActivity.class).onActivity(activity -> {
            Intent intent = new Intent(activity, Storing.class);
            intent.putExtra("store", MOCK_STORE_DATA);
            activity.setIntent(intent);
        });

        // Delay for a short period to allow the activity transition to occur
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform actions in the RetrieveDataActivity


        // Verify that the Storing activity is launched
        intending(hasComponent(Storing.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        // Perform actions in the Storing activity to update the data
        Espresso.onView(withId(R.id.heartRate))
                .perform(ViewActions.replaceText("90")); // Update heart rate
        Espresso.onView(withId(R.id.systolic))
                .perform(ViewActions.replaceText("130")); // Update systolic value
        Espresso.onView(withId(R.id.diastolic))
                .perform(ViewActions.replaceText("85")); // Update diastolic value
        Espresso.onView(withId(R.id.date))
                .perform(ViewActions.replaceText("07/07/2023")); // Update date
        Espresso.onView(withId(R.id.time))
                .perform(ViewActions.replaceText("11:30")); // Update time
        Espresso.onView(withId(R.id.comment))
                .perform(ViewActions.replaceText("Updated comment")); // Update comment
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.save))
                .perform(ViewActions.click()); // Click the save button

        // You can add assertions here to verify the expected behavior after updating the data
    }



}


*/


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {
    @Rule
    public ActivityScenarioRule<RetreiveDataActivity> activityRule =
            new ActivityScenarioRule<>(RetreiveDataActivity.class);

    @Test
    public void testAll(){
        testAddData();
        testEditData();
        testDeleteData();
    }

    @Test
    public void testAddData(){
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.systolic)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.diastolic)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.heartRate)).perform(ViewActions.typeText("65"));
        Espresso.onView(withId(R.id.date))
                .perform(ViewActions.typeText("07/06/2023")); // Enter date
        Espresso.onView(withId(R.id.time))
                .perform(ViewActions.typeText("10:30"));
        onView(withId(R.id.comment)).perform(ViewActions.typeText("UI test"));

        Espresso.pressBack();
      onView(withId(R.id.save)).perform(click());

    }

    @Test
    public void testEditData(){

        SystemClock.sleep(4000);
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.edit)));
        SystemClock.sleep(1500);

        onView(withId(R.id.systolic)).perform(ViewActions.replaceText("80"));
        onView(withId(R.id.diastolic)).perform(ViewActions.replaceText("110"));
        onView(withId(R.id.heartRate)).perform(ViewActions.replaceText("85"));
        onView(withId(R.id.date)).perform(ViewActions.replaceText("110"));
        onView(withId(R.id.time)).perform(ViewActions.replaceText("85"));

        onView(withId(R.id.comment)).perform(ViewActions.replaceText("UI test"));
        //Espresso.pressBack();
        onView(withId(R.id.save)).perform(click());

        SystemClock.sleep(4000);

        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.buttonDetails)));
        SystemClock.sleep(1000);
        Espresso.pressBack();

    }

    @Test
    public void testDeleteData(){

        SystemClock.sleep(4000);
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(
                0,MyViewAction.clickChildViewWithId(R.id.delete)));
        SystemClock.sleep(4000);

    }

    public static class MyViewAction {

        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return ViewMatchers.isAssignableFrom(RecyclerView.class);
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }

    }
}
