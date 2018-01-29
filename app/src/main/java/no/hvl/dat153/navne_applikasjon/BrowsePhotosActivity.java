package no.hvl.dat153.navne_applikasjon;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.ImageArrayAdapter;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class BrowsePhotosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_photos);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        GlobalState app = (GlobalState) getApplicationContext();
        ArrayList<Person> people = app.getPeople();

        ListView listView = findViewById(R.id.browsePhotos_list);

        ImageArrayAdapter imageListAdapter = new ImageArrayAdapter(this, people);
        listView.setAdapter(imageListAdapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent(this, ShowPersonActivity.class);
            intent.putExtra("index", position);
            startActivity(intent);
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
