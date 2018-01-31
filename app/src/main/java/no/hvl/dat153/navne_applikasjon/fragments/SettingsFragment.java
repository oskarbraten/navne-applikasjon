package no.hvl.dat153.navne_applikasjon.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import no.hvl.dat153.navne_applikasjon.R;

public class SettingsFragment extends PreferenceFragment {

    public static final String profilePictureFileName = "profile_picture.png";
    public static final int PICK_IMAGE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Bundle bundle = getActivity().getIntent().getExtras();

        if (bundle != null && bundle.containsKey("initialize")) {
            EditTextPreference namePreference = (EditTextPreference) findPreference("display_name");

            PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference("preferenceScreen");

            // programmatically click on item.
            preferenceScreen.onItemClick(null, null, namePreference.getOrder(), 0);

            namePreference.setOnPreferenceChangeListener((Preference preference, Object newValue) -> {
                namePreference.setOnPreferenceChangeListener(null); // remove listener.

                // programmatically click on profile picture preference.
                // preferenceScreen.onItemClick(null, null, profilePicturePreference.getOrder(), 0);

                return true; // update value of preference.
            });
        }

        Preference profilePicturePreference = findPreference("profile_picture");
        profilePicturePreference.setOnPreferenceClickListener((Preference preference) -> {

            Intent getContentIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            getContentIntent.setType("image/*");

            // TODO: Implement camera support. Add runtime camera permissions.

            //Intent getFromCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Intent chooserIntent = Intent.createChooser(getContentIntent, "Select or capture image");
            //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {getFromCameraIntent});

            startActivityForResult(getContentIntent, PICK_IMAGE);

            return true;
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        final int RESULT_OK = getActivity().RESULT_OK;

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            Uri selectedImage = data.getData();

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle(R.string.pref_profile_picture_alert_title);

            builder.setPositiveButton(R.string.confirm, (DialogInterface dialog, int id) -> {
                new Thread(() -> {
                    try {
                        InputStream is = getActivity().getContentResolver().openInputStream(selectedImage);
                        Bitmap image = BitmapFactory.decodeStream(is);

                        FileOutputStream fos = getActivity().openFileOutput(profilePictureFileName, getActivity().MODE_PRIVATE);
                        image.compress(Bitmap.CompressFormat.PNG, 100, fos);

                        fos.flush();
                        fos.close();

                    } catch (IOException e) {
                        Toast.makeText(getActivity(), R.string.pref_profile_picture_savingError, Toast.LENGTH_SHORT);
                        e.printStackTrace();
                    }

                    dialog.dismiss();
                }).start();
            });

            builder.setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int id) -> {
                Intent getContentIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                getContentIntent.setType("image/*");
                startActivityForResult(getContentIntent, PICK_IMAGE);

                dialog.dismiss();
            });

            Dialog dialog = builder.create();
            dialog.show();

        }
    }
}
