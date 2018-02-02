package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;

public class MainActivity extends Activity {

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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.contains("display_name")) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("initialize", true);
            startActivity(intent);
        }
    }

    public void onResume() {
        super.onResume();

        GlobalState app = (GlobalState) getApplicationContext();

        TextView highScoreLabel = findViewById(R.id.main_highScoreLabel);
        highScoreLabel.setText(getString(R.string.main_highScoreLabelText) + " " + app.getHighScore());

        TextView displayNameTextView = findViewById(R.id.main_displayName);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String displayName = sharedPreferences.getString("display_name", getString(R.string.pref_display_name_default));

        if (!displayName.equals("")) {
            displayNameTextView.setText(displayName);
        }

        // load profile picture.
        String filePath = getFilesDir().getAbsolutePath() + "/profile_picture.png";
        ImageView profilePictureImageView = findViewById(R.id.main_userPhoto);

        new Thread(() -> {
            Bitmap image = BitmapFactory.decodeFile(filePath);
            if (image != null) {
                profilePictureImageView.post(() -> {
                    profilePictureImageView.setImageBitmap(image);
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
