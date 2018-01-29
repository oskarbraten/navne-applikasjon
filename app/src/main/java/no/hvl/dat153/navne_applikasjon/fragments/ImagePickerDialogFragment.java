package no.hvl.dat153.navne_applikasjon.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import no.hvl.dat153.navne_applikasjon.R;

public class ImagePickerDialogFragment extends DialogFragment {

    public static final int PICK_IMAGE = 1;
    private Uri selectedImage;
    private OnImageSelectedListener imageSelectedListener;

    public ImagePickerDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button chooseBtn = view.findViewById(R.id.imagePicker_chooseBtn);

        chooseBtn.setOnClickListener((View v) -> {
            Intent getContentIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            getContentIntent.setType("image/*");

            startActivityForResult(getContentIntent, PICK_IMAGE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK && requestCode == PICK_IMAGE) {
            selectedImage = data.getData();

            ImageView imagePreview = getView().findViewById(R.id.imagePicker_imagePreview);
            imagePreview.setImageURI(selectedImage);

            // pass selected image to callback.
            if (this.imageSelectedListener != null) {
                this.imageSelectedListener.callback(selectedImage);
            }
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.imagePicker_title);

        builder.setNegativeButton(android.R.string.cancel, (DialogInterface dialog, int whichButton) -> {
            dialog.dismiss();
        });

        View view = onCreateView(getActivity().getLayoutInflater(), null, savedInstanceState);
        onViewCreated(view, savedInstanceState);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.imageSelectedListener = null;
    }


    public Uri getSelectedImage() {
        return selectedImage;
    }

    public interface OnImageSelectedListener {
        void callback(Uri imageURI);
    }

    public void setOnImageSelectedListener(OnImageSelectedListener listener) {
        this.imageSelectedListener = listener;
    }
}
