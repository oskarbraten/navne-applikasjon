package no.hvl.dat153.navne_applikasjon;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

public class OpenPersonFromPhotos {

    @Rule
    public ActivityTestRule<BrowsePhotosActivity> activityRule = new ActivityTestRule<>(BrowsePhotosActivity.class);

    @Test
    public void testOk() {

        Context context = InstrumentationRegistry.getTargetContext();
        ArrayList<Person> people = ((GlobalState) context.getApplicationContext()).getPeople();

        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);

            onData(anything()).inAdapterView(withId(R.id.browsePhotos_list)).atPosition(i).perform(scrollTo(), click());

            onView(withText(person.getName())).check(matches(not(doesNotExist())));

            pressBack();
        }
    }

}
