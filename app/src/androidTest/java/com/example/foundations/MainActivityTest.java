package com.example.foundations;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;



    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void hasTitle() {
        onView(withId(R.id.title)).check(matches(withText(R.string.foundations)));
    }

    @Test
    public void hasSubtitle() {
        onView(withId(R.id.subtitle)).check(matches(withText(R.string.home_inspection_made_easy)));
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}
