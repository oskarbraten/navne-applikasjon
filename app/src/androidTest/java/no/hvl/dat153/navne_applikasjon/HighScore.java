package no.hvl.dat153.navne_applikasjon;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import no.hvl.dat153.navne_applikasjon.misc.Person;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;


public class HighScore {

    @Rule
    public ActivityTestRule<LearningModeActivity> activityRule = new ActivityTestRule<>(LearningModeActivity.class);
    public ActivityTestRule<MainActivity> activityRule2 = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testHighScore1() {
        /**
         * HIGH SCORE CHECK 1:
         * Tests that a new session with lower score does not overwrite the current high score.
         */

        Context context = InstrumentationRegistry.getTargetContext();

        int maxScore = 3;

        for (int i = 0; i < maxScore; i++) {
            Person currentPerson = activityRule.getActivity().getCurrentPerson();

            onView(withId(R.id.learningMode_textInput)).perform(typeText(currentPerson.getName()));

            closeSoftKeyboard();

            onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());
        }

        activityRule.launchActivity(new Intent());

        Person currentPerson = activityRule.getActivity().getCurrentPerson();

        onView(withId(R.id.learningMode_textInput)).perform(typeText(currentPerson.getName()));

        closeSoftKeyboard();

        onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());

        activityRule2.launchActivity(new Intent());

        // Check that main menu shows the correct highScore.
        TextView highScoreView = activityRule2.getActivity().findViewById(R.id.main_highScoreLabel);
        String highScoreViewText = highScoreView.getText().toString();
        int highScoreTextResourceLength = context.getResources().getString(R.string.main_highScoreLabelText).length();
        int currentlyDisplayedHighScore = Integer.parseInt(highScoreViewText.substring(highScoreTextResourceLength).replace(" ", ""));

        // assert that highScore is still equal to maxScore even if we achieved 1 point in another session.
        assertEquals(currentlyDisplayedHighScore, maxScore);
    }

    @Test
    public void testHighScore2() {
        /**
         * HIGH SCORE CHECK 2:
         * Tests that a new high score overwrites the previous one.
         */

        Context context = InstrumentationRegistry.getTargetContext();

        int oldHighScore = 2;
        for (int i = 0; i < oldHighScore; i++) {
            Person currentPerson = activityRule.getActivity().getCurrentPerson();

            onView(withId(R.id.learningMode_textInput)).perform(typeText(currentPerson.getName()));

            closeSoftKeyboard();

            onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());
        }

        activityRule.launchActivity(new Intent());

        int newHighScore = 3;

        for (int i = 0; i < newHighScore; i++) {
            Person currentPerson = activityRule.getActivity().getCurrentPerson();

            onView(withId(R.id.learningMode_textInput)).perform(typeText(currentPerson.getName()));

            closeSoftKeyboard();

            onView(withId(R.id.learningMode_checkButton)).perform(scrollTo(), click());
        }

        activityRule2.launchActivity(new Intent());

        // Check that main menu shows the correct high score.
        TextView highScoreView = activityRule2.getActivity().findViewById(R.id.main_highScoreLabel);
        String highScoreViewText = highScoreView.getText().toString();
        int highScoreTextResourceLength = context.getResources().getString(R.string.main_highScoreLabelText).length();
        int currentlyDisplayedHighScore = Integer.parseInt(highScoreViewText.substring(highScoreTextResourceLength).replace(" ", ""));

        assertEquals(currentlyDisplayedHighScore, newHighScore);
    }
}
