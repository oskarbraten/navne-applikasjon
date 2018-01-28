package no.hvl.dat153.navne_applikasjon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import no.hvl.dat153.navne_applikasjon.fragments.ImagePickerFragment;

public class SelectProfilePictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile_picture);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setTitle(R.string.selectProfilePicture_title);

        ImagePickerFragment imagePickerFragment = (ImagePickerFragment) getSupportFragmentManager().findFragmentById(R.id.selectProfilePicture_imagePickerFragment);
        imagePickerFragment.setOnImageSelectedListener((Uri selectedImage) -> {

            try {
                String fileName = "profile_picture.png";

                InputStream is = getContentResolver().openInputStream(selectedImage);
                Bitmap image = BitmapFactory.decodeStream(is);

                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                image.compress(Bitmap.CompressFormat.PNG, 100, fos);

                fos.flush();
                fos.close();

                finish();
            } catch (IOException e) {
                Toast.makeText(this, R.string.selectProfilePicture_savingError, Toast.LENGTH_SHORT);
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
