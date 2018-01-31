package no.hvl.dat153.navne_applikasjon;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class AddPersonActivity extends Activity {

    public static final int PICK_IMAGE = 1;

    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.addPerson_title);


        Button chooseImageBtn = findViewById(R.id.addPerson_chooseImageBtn);
        chooseImageBtn.setOnClickListener((View v) -> {
            Intent getContentIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            getContentIntent.setType("image/*");

            // TODO: Implement camera support. Add runtime camera permissions.

            //Intent getFromCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Intent chooserIntent = Intent.createChooser(getContentIntent, "Select or capture image");
            //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {getFromCameraIntent});

            startActivityForResult(getContentIntent, PICK_IMAGE);
        });

        EditText nameEditText = findViewById(R.id.addPerson_nameEditText);

        Button addButton = findViewById(R.id.addPerson_addButton);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            selectedImage = data.getData();

            ImageView imagePreview = findViewById(R.id.addPerson_imagePreview);
            imagePreview.setImageURI(selectedImage);
        }
    }
}
