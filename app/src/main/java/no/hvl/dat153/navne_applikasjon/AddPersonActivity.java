package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddPersonActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    private Uri selectedPhoto = null;
    private ImageView imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addButton = findViewById(R.id.addPerson_addButton);
        Button browseButton = findViewById(R.id.addPerson_browseButton);

        imagePreview = findViewById(R.id.addPerson_imagePreview);

        EditText nameEditText = findViewById(R.id.addPerson_nameEditText);

        browseButton.setOnClickListener((View v) -> {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, getResources().getString(R.string.addPerson_selectPhoto));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, PICK_IMAGE);
        });

        addButton.setOnClickListener((View v) -> {

            String name = nameEditText.getText().toString();

            if (selectedPhoto != null && name.length() > 0) {

                Person person = new Person(name, selectedPhoto);
                final GlobalState app = (GlobalState) getApplicationContext();
                app.getPeople().add(person);
                NavUtils.navigateUpFromSameTask(this);
            } else {
                Toast.makeText(AddPersonActivity.this, R.string.addPerson_errorMsg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            selectedPhoto = data.getData();
            imagePreview.setImageURI(selectedPhoto);
        }
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
