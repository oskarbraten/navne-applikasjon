package no.hvl.dat153.navne_applikasjon;

import org.junit.*;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class InstallBehaviour {

    private static final String username = "Captain Kirk";

    @Rule
    public ClearedPreferencesActivityTestRule<MainActivity> activityRule = new ClearedPreferencesActivityTestRule<>(MainActivity.class);

    @Test
    public void testOk() {

        onView(withText(R.string.pref_display_name_title)).check(matches(isDisplayed()));

        onView(withHint(R.string.pref_display_name_default)).perform(typeText(username));

        onView(withText(android.R.string.ok)).check(matches(isClickable())).perform(click());

        pressBack();

        onView(withId(R.id.main_displayName)).check(matches(withText(username)));
    }
}
