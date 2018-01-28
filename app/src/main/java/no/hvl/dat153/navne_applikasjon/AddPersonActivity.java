package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import no.hvl.dat153.navne_applikasjon.fragments.ImagePickerFragment;
import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class AddPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.addPerson_title);

        Button addButton = findViewById(R.id.addPerson_addButton);

        EditText nameEditText = findViewById(R.id.addPerson_nameEditText);

        addButton.setOnClickListener((View v) -> {

            String name = nameEditText.getText().toString();

            ImagePickerFragment imagePickerFragment = (ImagePickerFragment) getSupportFragmentManager().findFragmentById(R.id.addPerson_imagePickerFragment);

            Uri selectedPhoto = imagePickerFragment.getSelectedImage();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
