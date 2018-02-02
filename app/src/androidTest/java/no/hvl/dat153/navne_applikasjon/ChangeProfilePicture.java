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

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;

public class ChangeProfilePicture {

    private static final String filename = "profile_picture.png";

    @Rule
    public ActivityTestRule<SettingsActivity> activityRule = new ActivityTestRule<>(SettingsActivity.class, true, false);

    @Test
    public void testOk() {

        Context context = InstrumentationRegistry.getTargetContext();

        // delete old profile picture.
        context.deleteFile(filename);

        // launch settings.
        activityRule.launchActivity(new Intent());

        Resources resources = context.getResources();
        Uri u = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.drawable.dog1) + '/' +
                resources.getResourceTypeName(R.drawable.dog1) + '/' +
                resources.getResourceEntryName(R.drawable.dog1));

        Intent resultData = new Intent();
        resultData.setData(u);

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_OPEN_DOCUMENT));

        Intents.init();

        intending(expectedIntent).respondWith(result);

        // click displayName preference.
        onView(withText(R.string.pref_profile_picture_title)).perform(click());

        intended(expectedIntent);
        Intents.release();

        onView(withText(R.string.confirm)).check(matches(isClickable())).perform(click());

        // check if new profile picture was saved.
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + filename);
        assertTrue(file.exists());
    }
}
