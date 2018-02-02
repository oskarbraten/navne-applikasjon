package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ChangeUsername {

    public static final String username = "Captain Picard";

    @Rule
    public ActivityTestRule<SettingsActivity> activityRule = new ActivityTestRule<>(SettingsActivity.class);
    public ActivityTestRule<MainActivity> activityRule2 = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testOk() {
        // click displayName preference.
        onView(withText(R.string.pref_display_name_title)).perform(click());

        onView(withText(R.string.pref_display_name_title)).check(matches(isDisplayed()));
        onView(withHint(R.string.pref_display_name_default)).perform(clearText()).perform(typeText(username));

        onView(withText(android.R.string.ok)).check(matches(isClickable())).perform(click());

        // start main activity.
        activityRule2.launchActivity(new Intent());

        onView(withId(R.id.main_displayName)).check(matches(withText(username)));
    }
}
