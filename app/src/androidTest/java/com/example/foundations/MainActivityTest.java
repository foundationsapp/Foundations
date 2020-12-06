package com.example.foundations;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
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
    public void hasLogo() {
        onView(withId(R.id.main_logo)).check(matches(withId(R.id.main_logo)));
    }

    @Test
    public void hasSubtitle() {
        onView(withId(R.id.subtitle)).check(matches(withText(R.string.home_inspection_made_easy)));
    }

    @Test
    public void signUp() throws InterruptedException {
        onView(withId(R.id.newUser)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.edit_first_name)).perform(ViewActions.scrollTo(), typeText("Tommy"));
        onView(withId(R.id.edit_last_name)).perform(ViewActions.scrollTo(), typeText("Deckman"));
        onView(withId(R.id.edit_license_number)).perform(ViewActions.scrollTo(), typeText("123123"));
        onView(withId(R.id.edit_company_name)).perform(ViewActions.scrollTo(), typeText("Inspect The Deck"));
        onView(withId(R.id.edit_email)).perform(ViewActions.scrollTo(), typeText("kevin@deck.com"));
        onView(withId(R.id.edit_phone)).perform(ViewActions.scrollTo(), typeText("2062023434"));
        onView(withId(R.id.get_started_button)).perform(ViewActions.scrollTo(), click());
        Thread.sleep(500);
        onView(withId(R.id.users)).check(matches(withText("USERS")));
        onView(withId(R.id.profileItem)).check(matches(withText("Tommy Deckman")));
        onView(withId(R.id.profileItem)).perform(click());
        onView(withId(R.id.get_started_button)).check(matches(withText("SELECT USER")));
        onView(withId(R.id.get_started_button)).perform(click());
        onView(withId(R.id.dash_frag_recent)).check(matches(withText("Recent Inspections")));
        onView(withId(R.id.dash_frag_select_inspection)).check(matches(withText("SELECT INSPECTION")));
        onView(withId(R.id.dash_frag_new_inspection)).check(matches(withText("NEW INSPECTION")));

    }

    @Test
    public void hasNewUserOption() {
        onView(withId(R.id.newUser)).check(matches(withText(R.string.new_user)));
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}
