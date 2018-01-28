package no.hvl.dat153.navne_applikasjon.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import no.hvl.dat153.navne_applikasjon.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Bundle bundle = getActivity().getIntent().getExtras();

        if (bundle != null && bundle.containsKey("initialize")) {
            EditTextPreference namePreference = (EditTextPreference) findPreference("display_name");
            Preference profilePicturePreference = findPreference("profile_picture");

            PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("preferenceScreen");

            // programmatically click on item.
            preferenceScreen.onItemClick(null, null, namePreference.getOrder(), 0);

            namePreference.setOnPreferenceChangeListener((Preference preference, Object newValue) -> {
                namePreference.setOnPreferenceChangeListener(null); // remove listener.

                // programmatically click on profile picture preference.
                preferenceScreen.onItemClick(null, null, profilePicturePreference.getOrder(), 0);

                return true; // update value of preference.
            });
        }

        Preference profilePicturePreference = findPreference("profile_picture");
        profilePicturePreference.setOnPreferenceClickListener((Preference preference) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int id) -> {
                dialog.cancel();
            });

            builder.setTitle("Select or capture a profile picture");

            LinearLayout layout = new LinearLayout(getActivity());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setPadding(16, 16,16, 16);
            layout.setWeightSum(2);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;

            Button selectBtn = new Button(getActivity());
            selectBtn.setLayoutParams(params);
            selectBtn.setText(R.string.imagePicker_select);

            Button captureBtn = new Button(getActivity());
            captureBtn.setLayoutParams(params);
            captureBtn.setText(R.string.imagePicker_capture);

            layout.addView(selectBtn);
            layout.addView(captureBtn);

            builder.setView(layout);

            AlertDialog dialog = builder.create();

            dialog.show();

            selectBtn.setOnClickListener((View v) -> {

            });

            captureBtn.setOnClickListener((View v) -> {

            });

            return true;
        });
    }
}
