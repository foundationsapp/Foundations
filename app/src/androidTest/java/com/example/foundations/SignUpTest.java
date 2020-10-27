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
public class SignUpTest {
    @Rule
    public ActivityTestRule<SignUp> signUpActivityTestRule
            = new ActivityTestRule<SignUp>(SignUp.class);
    private SignUp signUpActivity = null;



    @Before
    public void setUp() throws Exception {
        signUpActivity = signUpActivityTestRule.getActivity();
    }

    @Test
    public void hasTitle() {
        onView(withId(R.id.signup)).check(matches(withText(R.string.welcome)));
    }

    @Test
    public void hasFirstName() {
        onView(withId(R.id.first_name)).check(matches(withText(R.string.first_name)));
    }

    @Test
    public void hasLastName() {
        onView(withId(R.id.last_name)).check(matches(withText(R.string.last_name)));
    }

    @Test
    public void hasLicenseNumber() {
        onView(withId(R.id.licenseNumber)).check(matches(withText(R.string.license_number)));
    }

    @Test
    public void hasCompanyName() {
        onView(withId(R.id.companyName)).check(matches(withText(R.string.company_name)));
    }

    @Test
    public void hasAddress() {
        onView(withId(R.id.email)).check(matches(withText(R.string.email)));
    }

    @Test
    public void hasPhone() {
        onView(withId(R.id.phone)).check(matches(withText(R.string.phone)));
    }

    @After
    public void tearDown() throws Exception {
        signUpActivity = null;
    }
}
