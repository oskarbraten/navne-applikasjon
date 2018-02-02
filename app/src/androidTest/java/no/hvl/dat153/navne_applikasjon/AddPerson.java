package no.hvl.dat153.navne_applikasjon;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;

public class AddPerson {

    @Rule
    public ActivityTestRule<AddPersonActivity> activityRule = new ActivityTestRule<>(AddPersonActivity.class);
    public ActivityTestRule<BrowseNamesActivity> activityRule2 = new ActivityTestRule<>(BrowseNamesActivity.class);

    private static final String name = "Gandalf";

    @Test
    public void testOk() {

        Context context = InstrumentationRegistry.getTargetContext();

        final int numberOfPeopleBefore = ((GlobalState) context.getApplicationContext()).getPeople().size();

        onView(withId(R.id.addPerson_nameEditText)).perform(typeText(name));

        Resources resources = context.getResources();
        Uri u = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.drawable.dog2) + '/' +
                resources.getResourceTypeName(R.drawable.dog2) + '/' +
                resources.getResourceEntryName(R.drawable.dog2));

        Intent resultData = new Intent();
        resultData.setData(u);

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_OPEN_DOCUMENT));

        Intents.init();

        intending(expectedIntent).respondWith(result);

        // click choose image btn.
        onView(withId(R.id.addPerson_chooseImageBtn)).perform(click());

        intended(expectedIntent);
        Intents.release();

        // close keyboard.
        closeSoftKeyboard();

        onView(withId(R.id.addPerson_addButton)).perform(scrollTo(), click());

        final int numberOfPeopleAfter = ((GlobalState) context.getApplicationContext()).getPeople().size();

        assertEquals(numberOfPeopleBefore + 1, numberOfPeopleAfter);

        activityRule2.launchActivity(new Intent());

        // check that name is in list.
        onView(withId(R.id.browseNames_list)).check(matches(hasDescendant(withText(name))));
    }
}
