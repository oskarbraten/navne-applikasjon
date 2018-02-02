package no.hvl.dat153.navne_applikasjon;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class ShowPersonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);

        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);

        Person person = ((GlobalState) getApplicationContext()).getPeople().get(index);

        ImageView image = findViewById(R.id.showPerson_imageView);
        image.setImageURI(person.getImageURI());

        actionBar.setTitle(person.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
