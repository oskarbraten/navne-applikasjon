package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button browseNamesButton = findViewById(R.id.main_browseNamesBtn);
        browseNamesButton.setOnClickListener((View) -> {
            startActivity(new Intent(MainActivity.this, BrowseNamesActivity.class));
        });

        Button browsePhotosButton = findViewById(R.id.main_browsePhotosBtn);
        browsePhotosButton.setOnClickListener((View) -> {
            startActivity(new Intent(MainActivity.this, BrowsePhotosActivity.class));
        });

        Button learningModeButton = findViewById(R.id.main_learningMode_btn);
        learningModeButton.setOnClickListener((View) -> {
            startActivity(new Intent(MainActivity.this, LearningModeActivity.class));
        });
    }

    public void onResume() {
        super.onResume();

        GlobalState app = (GlobalState) getApplicationContext();

        TextView highScoreLabel = findViewById(R.id.main_highScoreLabel);
        highScoreLabel.setText(getResources().getString(R.string.main_highScoreLabelText) + " " + Integer.toString(app.getHighScore()));
    }
}
