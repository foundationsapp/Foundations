package com.example.foundations;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.intent.matcher.IntentMatchers;
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

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intending;
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

    @Test
    public void failTests() {
        onView(withText("SIGN UP")).perform(scrollTo(), click());
    }
//
//    @Test
//    public void takePhoto() {
//        onView(withId(R.id.signupcamera)).perform(click());
//
//        intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(
//                new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
//
//        IntentCallback intentCallback = new IntentCallback() {
//            @Override
//            public void onIntentSent(Intent intent) {
//                if (intent.getAction().equals("android.media.action.IMAGE_CAPTURE")) {
//                    try {
//                        Uri imageUri = intent.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
//                        Context context = getTargetContext();
//                        Bitmap icon = BitmapFactory.decodeResource(
//                                context.getResources(),
//                                R.mipmap.ic_launcher);
//                        OutputStream out = getTargetContext().getContentResolver().openOutputStream(imageUri);
//                        icon.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                        out.flush();
//                        out.close();
//                    } catch (IOException e) {
//
//                    }
//                }
//            }
//        };
//        IntentMonitorRegistry.getInstance().addIntentCallback(intentCallback);
//
//        //Perform action here
//
//    }

    @After
    public void tearDown() throws Exception {
        signUpActivity = null;
    }
}
