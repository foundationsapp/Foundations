package com.example.foundations;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.intent.IntentCallback;
import androidx.test.runner.intent.IntentMonitorRegistry;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.OutputStream;

import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {

    private static final String TAG = "SignUpTest";
    @Rule
    public IntentsTestRule<SignUp> signUpActivityTestRule
            = new IntentsTestRule<SignUp>(SignUp.class);
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

    @Test
    public void failTests() {
        onView(withText("SIGN UP")).perform(scrollTo(), click());
    }

    @Test
    public void takePhoto() {
        Bitmap logo = BitmapFactory.decodeResource(InstrumentationRegistry.getTargetContext().getResources(), R.drawable.logo);
        Log.d(TAG, "takePhoto: " + logo);
        Intent result = new Intent();
        result.putExtra("data", logo);
        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, result);

        intending(toPackage("com.android.camera2")).respondWith(activityResult);

        onView(withId(R.id.signupcamera)).perform(click());

        intended(toPackage("com.android.camera2"));

    }

    @After
    public void tearDown() throws Exception {
        signUpActivity = null;
    }
}
