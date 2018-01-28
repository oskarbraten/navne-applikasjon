package no.hvl.dat153.navne_applikasjon;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import no.hvl.dat153.navne_applikasjon.misc.GlobalState;
import no.hvl.dat153.navne_applikasjon.misc.Person;

public class BrowseNamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_names);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GlobalState app = (GlobalState) getApplicationContext();
        ArrayList<Person> people = app.getPeople();

        ListView listView = findViewById(R.id.browseNames_list);

        ListAdapter listAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, people);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent(this, ShowPersonActivity.class);
            intent.putExtra("index", position);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.browseNames_addBtnText).setIcon(R.drawable.ic_add).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case 0:
                startActivity(new Intent(this, AddPersonActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
