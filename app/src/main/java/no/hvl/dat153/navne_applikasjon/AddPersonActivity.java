package no.hvl.dat153.navne_applikasjon;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import no.hvl.dat153.navne_applikasjon.fragments.ImagePickerDialogFragment;
import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class AddPersonActivity extends Activity {

    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.addPerson_title);

        Button addButton = findViewById(R.id.addPerson_addButton);

        EditText nameEditText = findViewById(R.id.addPerson_nameEditText);

        ImagePickerDialogFragment imagePickerDialogFragment = (ImagePickerDialogFragment) getFragmentManager().findFragmentById(R.id.addPerson_imagePickerFragment);

        imagePickerDialogFragment.setOnImageSelectedListener((Uri selectedImage) -> {
            this.selectedImage = selectedImage;
        });

        addButton.setOnClickListener((View v) -> {

            String name = nameEditText.getText().toString();

            if (this.selectedImage != null && name.length() > 0) {
                Person person = new Person(name, this.selectedImage);
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
