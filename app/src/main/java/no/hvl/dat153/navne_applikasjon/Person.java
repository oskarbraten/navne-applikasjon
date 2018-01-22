package no.hvl.dat153.navne_applikasjon;

import android.net.Uri;

public class Person {
    private String name;
    private Uri imageURI;

    public Person(String name, Uri imageURI) {
        this.name = name;
        this.imageURI = imageURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageURI() {
        return imageURI;
    }

    public void setImageURI(Uri imageURI) {
        this.imageURI = imageURI;
    }
}
