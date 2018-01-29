package no.hvl.dat153.navne_applikasjon;

import android.app.Activity;
import android.os.Bundle;

import no.hvl.dat153.navne_applikasjon.fragments.SettingsFragment;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}