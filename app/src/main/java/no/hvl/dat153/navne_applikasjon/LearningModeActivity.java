package no.hvl.dat153.navne_applikasjon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class LearningModeActivity extends Activity {

    private ArrayList<Person> people;
    private Person currentPerson;

    private ImageSwitcher imageSwitcher;
    private EditText textInput;
    private TextView scoreText;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

        GlobalState app = (GlobalState) getApplicationContext();
        people = app.getPeople();

        this.imageSwitcher = findViewById(R.id.learningMode_photo);
        this.imageSwitcher.setFactory(() -> {
            // Create a new ImageView and set it's properties
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            return imageView;
        });

        // set animations.
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        this.imageSwitcher.setInAnimation(in);
        this.imageSwitcher.setOutAnimation(out);

        this.textInput = findViewById(R.id.learningMode_textInput);
        this.scoreText = findViewById(R.id.learningMode_score);

        Button checkButton = findViewById(R.id.learningMode_checkButton);
        checkButton.setOnClickListener((View v) -> {
            check();
            reset();
        });

        // call once to initialize.
        app.setHighScore(0);
        reset();
    }

    private void check() {
        String guess = textInput.getText().toString();

        if (currentPerson.getName().toLowerCase().equals(guess.toLowerCase())) {
            this.score++;
            Toast.makeText(LearningModeActivity.this, R.string.learningMode_guessedCorrect, Toast.LENGTH_SHORT).show();

            // potential increase in highScore. save it.
            this.save(this.score);

        } else {
            this.score--;
            Toast.makeText(LearningModeActivity.this, R.string.learningMode_guessedIncorrect, Toast.LENGTH_SHORT).show();
        }

        scoreText.setText(getResources().getString(R.string.learningMode_score) + " " + this.score);
    }

    private void reset() {
        // select random person, and make sure it is not already selected.
        while (true) {
            int randomNumber = ThreadLocalRandom.current().nextInt(0, people.size());
            Person randomPerson = people.get(randomNumber);

            if (currentPerson == null || currentPerson.getName() != randomPerson.getName()) {
                currentPerson = randomPerson;
                break;
            }
        }

        this.imageSwitcher.setImageURI(currentPerson.getImageURI());
        textInput.setText("");
    }

    private void save(int newScore) {
        GlobalState app = (GlobalState) getApplicationContext();

        int prevScore = app.getHighScore();

        if (prevScore < newScore) {
            app.setHighScore(newScore); // new high score
        }
    }
}
