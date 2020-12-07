package com.example.foundations;

import android.Manifest;
import android.view.Gravity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.DrawerMatchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA);

    public static RecyclerViewMatcherTestUtils withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcherTestUtils(recyclerViewId);
    }

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
        onView(withId(R.id.edit_first_name)).perform(scrollTo(), typeText("Tommy"));
        onView(withId(R.id.edit_last_name)).perform(scrollTo(), typeText("Deckman"));
        onView(withId(R.id.edit_license_number)).perform(scrollTo(), typeText("123123"));
        onView(withId(R.id.edit_company_name)).perform(scrollTo(), typeText("Inspect The Deck"));
        onView(withId(R.id.edit_email)).perform(scrollTo(), typeText("kevin@deck.com"));
        onView(withId(R.id.edit_phone)).perform(scrollTo(), typeText("2062023434"));
        Espresso.closeSoftKeyboard();
        Thread.sleep(500);
        onView(withId(R.id.get_started_button)).perform(scrollTo(), click());
        Thread.sleep(500);
        onView(withId(R.id.users)).check(matches(withText("USERS")));
        onView(withId(R.id.profileItem)).check(matches(withText("Tommy Deckman")));
        onView(withId(R.id.profileItem)).perform(click());
        onView(withId(R.id.get_started_button)).check(matches(withText("SELECT USER")));
        onView(withId(R.id.get_started_button)).perform(scrollTo(), click());
        onView(withId(R.id.dash_frag_recent)).check(matches(withText("Recent Inspections")));
        onView(withId(R.id.dash_frag_select_inspection)).check(matches(withText("SELECT INSPECTION")));
        onView(withId(R.id.dash_frag_new_inspection)).check(matches(withText("NEW INSPECTION")));
        onView(withId(R.id.dash_frag_new_inspection)).perform(click());
        onView(withId(R.id.basic_info_start)).check(matches(withText("Basic Information")));
        onView(withId(R.id.buyer_info_title)).check(matches(withText("Buyer Information")));
        onView(withId(R.id.title_buyer_first_name)).check(matches(withText("First Name")));
        onView(withId(R.id.ni_buyer_first_name)).check(matches(withHint("Michael")));
        onView(withId(R.id.ni_buyer_first_name)).perform(typeText("Mike"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.buyer_last_name)).check(matches(withText("Last Name")));
        onView(withId(R.id.ni_buyer_last_name)).check(matches(withHint("Jobs")));
        onView(withId(R.id.ni_buyer_last_name)).perform(scrollTo(), typeText("Buyers"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_seller_first_name)).perform(scrollTo());
        onView(withId(R.id.seller_info_title)).check(matches(withText("Seller Information")));
        onView(withId(R.id.title_seller_first_name)).check(matches(withText("First Name")));
        onView(withId(R.id.ni_seller_first_name)).check(matches(withHint("Tom")));
        onView(withId(R.id.ni_seller_first_name)).perform(typeText("Steven"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_seller_last_name)).perform(scrollTo());
        onView(withId(R.id.title_seller_last_name)).check(matches(withText("Last Name")));
        onView(withId(R.id.ni_seller_last_name)).check(matches(withHint("Smith")));
        onView(withId(R.id.ni_seller_last_name)).perform(typeText("Fields"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_address)).perform(scrollTo());
        onView(withId(R.id.title_site_info)).check(matches(withText("Site Information")));
        onView(withId(R.id.bi_si_title)).check(matches(withText("Address")));
        onView(withId(R.id.ni_address)).check(matches(withHint("1256 Marline CT")));
        onView(withId(R.id.ni_address)).perform(typeText("123 buymyhouse rd"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_city)).perform(scrollTo());
        onView(withId(R.id.bi_si_city)).check(matches(withText("City")));
        onView(withId(R.id.ni_city)).check(matches(withHint("Seattle")));
        onView(withId(R.id.ni_city)).perform(typeText("Bellevue"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_state)).perform(scrollTo());
        onView(withId(R.id.bi_si_state)).check(matches(withText("State")));
        onView(withId(R.id.ni_state)).check(matches(withHint("WA")));
        onView(withId(R.id.ni_state)).perform(typeText("WA"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_zip)).perform(scrollTo());
        onView(withId(R.id.bi_si_zip)).check(matches(withText("Zip Code")));
        onView(withId(R.id.ni_zip)).check(matches(withHint("98133")));
        onView(withId(R.id.ni_zip)).perform(typeText("98989"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ni_create_report)).perform(scrollTo());
        onView(withId(R.id.property_photo_title)).check(matches(withText("Property Photo")));
        onView(withId(R.id.add_property_photo)).check(matches(isDisplayed()));
        onView(withId(R.id.ni_create_report)).check(matches(withText("SUBMIT AND CONTINUE")));
        onView(withId(R.id.ni_create_report)).perform(click());
        onView(withId(R.id.sd_title)).check(matches(withText("Site Details")));

        onView(withId(R.id.sd_bedrooms)).check(matches(withText("Bedrooms")));
        onView(withId(R.id.sd_bedrooms_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_bedrooms_amt)).perform(typeText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sd_bathrooms)).check(matches(withText("Bathrooms")));
        onView(withId(R.id.sd_bathrooms_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_bathrooms_amt)).perform(scrollTo(), typeText("2"));

        onView(withId(R.id.sd_stories)).check(matches(withText("Stories")));
        onView(withId(R.id.sd_stories_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_stories_amt)).perform(scrollTo(), typeText("2"));

        onView(withId(R.id.sd_square_feet)).check(matches(withText("Square Feet")));
        onView(withId(R.id.sd_square_feet_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_square_feet_amt)).perform(scrollTo(), typeText("1200"));

        onView(withId(R.id.sd_inspection_fee)).check(matches(withText("Inspection Fee")));
        onView(withId(R.id.sd_inspection_fee_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_inspection_fee_amt)).perform(scrollTo(), typeText("199"));

        onView(withId(R.id.sd_year_built)).check(matches(withText("Year Built")));
        onView(withId(R.id.sd_year_built_amt)).check(matches(withHint("0")));
        onView(withId(R.id.sd_year_built_amt)).perform(scrollTo(), typeText("1999"));

        onView(withId(R.id.sd_furnished)).check(matches(withText("Furnished")));
        onView(withId(R.id.sd_furnished_amt)).check(matches(withHint("No")));
        onView(withId(R.id.sd_furnished_amt)).perform(scrollTo(), typeText("No"));

        onView(withId(R.id.sd_present)).check(matches(withText("Present at Inspection")));
        onView(withId(R.id.sd_present_amt)).check(matches(withHint("Buyer")));
        onView(withId(R.id.sd_present_amt)).perform(scrollTo(), typeText("Me"));

        onView(withId(R.id.sd_orientation_amt)).perform(scrollTo());
        onView(withId(R.id.sd_orientation)).check(matches(withText("Orientation")));
        onView(withId(R.id.sd_orientation_amt)).check(matches(withHint("North")));
        onView(withId(R.id.sd_orientation_amt)).perform(scrollTo(), typeText("South"));
        Espresso.closeSoftKeyboard();
        Thread.sleep(500);
        onView(withId(R.id.sd_submit)).check(matches(withText("SUBMIT AND CONTINUE")));
        onView(withId(R.id.sd_submit)).perform(scrollTo(), click());
        Thread.sleep(500);
        onView(withId(R.id.main_inspection_title)).check(matches(withText("Current Inspection")));
        onView(withText("Appliances")).check(matches(isDisplayed()));
        onView(withText("Dishwasher")).check(matches(isDisplayed()));
        onView(withText("Oven")).check(matches(isDisplayed()));
        onView(withText("Microwave")).check(matches(isDisplayed()));
        onView(withText("Range Hood")).check(matches(isDisplayed()));
        onView(withText("Range/Stove")).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.mi_edit_item)).check(matches(withText("Edit Item")));
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.mi_add_item)).check(matches(withText("Add Item")));
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.mi_add_item)).perform(click());
        onView(withId(R.id.list_item_title)).check(matches(withText("List Item")));
        onView(withId(R.id.list_item_subcat)).check(matches(withText("Select Subcategory for Item")));
        onView(withId(R.id.list_item_notes)).check(matches(withHint("Enter Notes")));
        onView(withId(R.id.list_item_notes)).perform(typeText("OMG AWESOME"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.list_item_notes)).check(matches(isDisplayed()));
        onView(withText("CANCEL")).check(matches(isDisplayed()));
        onView(withText("SUBMIT ITEM")).check(matches(isDisplayed()));
        onView(withText("SUBMIT ITEM")).perform(click());
        Thread.sleep(500);
        onView(withText("OMG AWESOME")).check(matches(isDisplayed()));
        onView(withText("OMG AWESOME")).perform(click());
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.mi_edit_item)).perform(click());
        onView(withId(R.id.list_item_notes)).perform(clearText());
        onView(withId(R.id.list_item_notes)).perform(typeText("OMG AWESOME AGAIN"));
        Espresso.closeSoftKeyboard();
        onView(withText("Submit Item")).perform(click());
        onView(withText("OMG AWESOME AGAIN")).perform(click());
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.mi_edit_item)).perform(click());
        Thread.sleep(500);
        onView(withText("CANCEL")).perform(click());
        onView(withText("OMG AWESOME AGAIN")).perform(click());
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.delete_item)).perform(click());
        onView(withText("CANCEL")).perform(click());
        onView(withText("OMG AWESOME AGAIN")).perform(click());
        onView(withRecyclerView(R.id.mi_category_recyclerview)
                .atPositionOnView(0, R.id.delete_item)).perform(click());
        onView(withText("YES, DELETE")).perform(click());
        onView(withText("ADD CATEGORY")).check(matches(isDisplayed()));
        onView(withText("ADD SUBCATEGORY")).check(matches(isDisplayed()));
        onView(withId(R.id.mi_done_button)).check(matches(isDisplayed()));
        onView(withText("ADD SUBCATEGORY")).perform(click());
        onView(withId(R.id.asc_dialog_title)).check(matches(withText("Add Subcategory")));
        onView(withId(R.id.asc_dialog_category)).check(matches(withText("Select Category")));
        onView(withId(R.id.asc_dialog_title_edit)).check(matches(withHint("Enter subcategory name")));
        onView(withId(R.id.asc_dialog_title_edit)).perform(typeText("WOW WOW WOW"));
        onView(withText("ADD SUBCATEGORY")).perform(click());
        onView(withText("ADD CATEGORY")).perform(click());
        onView(withId(R.id.ac_dialog_title)).check(matches(withText("Add Category")));
        onView(withId(R.id.ac_dialog_title_edit)).check(matches(withHint("Enter category name")));
        onView(withId(R.id.ac_dialog_title_edit)).perform(typeText("NO NO NO"));
        Espresso.closeSoftKeyboard();;
        onView(withText("ADD CATEGORY")).perform(click());
        onView(withId(R.id.mi_done_button)).perform(click());
        onView(withText("Ready To Generate PDF?")).check(matches(isDisplayed()));
        onView(withContentDescription("pdfimage")).check(matches(isDisplayed()));
        onView(withText("Basic Information")).check(matches(isDisplayed()));
        onView(withId(R.id.pdf_buyer_information)).check(matches(withText("Buyer Information")));
        onView(withText("Buyer First Name")).check(matches(isDisplayed()));
        onView(withText("Buyer Last Name")).check(matches(isDisplayed()));
        onView(withId(R.id.pdf_buyer_first_name)).check(matches(withText("Mike")));
        onView(withId(R.id.pdf_buyer_last_name)).check(matches(withText("Buyers")));
        onView(withId(R.id.pdf_seller_information)).check(matches(withText("Seller Information")));
        onView(withText("Seller First Name")).check(matches(isDisplayed()));
        onView(withText("Seller Last Name")).check(matches(isDisplayed()));
        onView(withId(R.id.pdf_seller_first_name)).check(matches(withText("Steven")));
        onView(withId(R.id.pdf_seller_last_name)).check(matches(withText("Fields")));



    }
//
//    @Test
//    public void navigate() throws InterruptedException {
//        onView(withId(R.id.profileItem)).check(matches(withText("Tommy Deckman")));
//        onView(withId(R.id.profileItem)).perform(click());
//        Thread.sleep(1000);
//        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.START)))
//                .perform(DrawerActions.open());
//    }

    @Test
    public void hasNewUserOption() {
        onView(withId(R.id.newUser)).check(matches(withText(R.string.new_user)));
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}
