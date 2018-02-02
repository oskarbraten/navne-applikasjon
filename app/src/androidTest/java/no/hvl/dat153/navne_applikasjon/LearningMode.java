package no.hvl.dat153.navne_applikasjon;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class LearningMode {

    @Rule
    public ActivityTestRule<LearningModeActivity> activityRule = new ActivityTestRule<>(LearningModeActivity.class);
    public ActivityTestRule<MainActivity> activityRule2 = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testOk() {

        Context context = InstrumentationRegistry.getTargetContext();

        final int numberOfGuesses = 3;


        /**
         * CORRECT GUESSES + REPEAT CHECK:
         */

        Person previousPerson = null;

        for (int i = 0; i < numberOfGuesses; i++) {
            Person currentPerson = activityRule.getActivity().getCurrentPerson();

            onView(withId(R.id.learningMode_textInput)).perform(typeText(currentPerson.getName()));

            closeSoftKeyboard();

            onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());


            // Check that the displayed score corresponds to the number of correct guesses.
            // get currently displayed score with hacky substring extraction =)
            TextView scoreView = activityRule.getActivity().findViewById(R.id.learningMode_score);
            String scoreViewText = scoreView.getText().toString();
            int scoreTextResourceLength = context.getResources().getString(R.string.learningMode_score).length();
            int currentlyDisplayedScore = Integer.parseInt(scoreViewText.substring(scoreTextResourceLength).replace(" ", ""));

            assertEquals(currentlyDisplayedScore, i + 1);


            // Check that the person to guess is not repeated.
            assertTrue(currentPerson != previousPerson);
            previousPerson = currentPerson;
        }


        /**
         * INCORRECT GUESSES:
         */

        String gibberish;

        ArrayList<Person> people = ((GlobalState) context.getApplicationContext()).getPeople();

        /**
         * Overcomplicated check to ensure String gibberish is not a persons name.
         */
        while (true) {
            gibberish = UUID.randomUUID().toString().substring(0, 8);

            for (Person p : people) {
                if (p.getName().equals(gibberish)) {
                    continue; // pick new name and check again.
                }
            }

            break; // gibberish does not equal name of any person.
        }

        for (int i = 0; i < numberOfGuesses; i++) {
            onView(withId(R.id.learningMode_textInput)).perform(typeText(gibberish));

            closeSoftKeyboard();

            onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());

            // Check that the displayed score corresponds to the number of failed guesses.
            // get currently displayed score with hacky substring extraction =)
            TextView scoreView = activityRule.getActivity().findViewById(R.id.learningMode_score);
            String scoreViewText = scoreView.getText().toString();
            int scoreTextResourceLength = context.getResources().getString(R.string.learningMode_score).length();
            int currentlyDisplayedScore = Integer.parseInt(scoreViewText.substring(scoreTextResourceLength).replace(" ", ""));

            assertEquals(currentlyDisplayedScore, numberOfGuesses - (i + 1));
        }


        /**
         * HIGHSCORE CHECK:
         */

        activityRule2.launchActivity(new Intent());

        // Check that main menu shows the correct highScore.
        TextView highscoreView = activityRule2.getActivity().findViewById(R.id.main_highScoreLabel);
        String highscoreViewText = highscoreView.getText().toString();
        int highscoreTextResourceLength = context.getResources().getString(R.string.main_highScoreLabelText).length();
        int currentlyDisplayedHighscore = Integer.parseInt(highscoreViewText.substring(highscoreTextResourceLength).replace(" ", ""));

        assertEquals(currentlyDisplayedHighscore, numberOfGuesses);
    }
}
