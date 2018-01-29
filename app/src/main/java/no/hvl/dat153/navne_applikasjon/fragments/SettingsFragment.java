package no.hvl.dat153.navne_applikasjon.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

            ImagePickerDialogFragment imagePickerDialogFragment = new ImagePickerDialogFragment();

            imagePickerDialogFragment.setOnImageSelectedListener((Uri selectedImage) -> {
                try {
                    String fileName = "profile_picture.png";

                    InputStream is = getActivity().getContentResolver().openInputStream(selectedImage);
                    Bitmap image = BitmapFactory.decodeStream(is);

                    FileOutputStream fos = getActivity().openFileOutput(fileName, getActivity().MODE_PRIVATE);
                    image.compress(Bitmap.CompressFormat.PNG, 100, fos);

                    fos.flush();
                    fos.close();

                    imagePickerDialogFragment.dismiss();

                } catch (IOException e) {
                    imagePickerDialogFragment.dismiss();
                    Toast.makeText(getActivity(), R.string.pref_profile_picture_savingError, Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
            });

            imagePickerDialogFragment.show(getFragmentManager(), "DialogFragment");

            return true;
        });
    }
}
