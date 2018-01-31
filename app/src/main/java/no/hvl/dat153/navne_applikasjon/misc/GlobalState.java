package no.hvl.dat153.navne_applikasjon.misc;

import android.app.Application;
import android.content.res.TypedArray;
import android.net.Uri;

import java.util.ArrayList;

import no.hvl.dat153.navne_applikasjon.MainActivity;
import no.hvl.dat153.navne_applikasjon.R;

public class GlobalState extends Application {

    private ArrayList<Person> people;

    public ArrayList<Person> getPeople() {
        return people;
    }

    private int highScore = 0;

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<Person>();

        String[] defaultNames = getResources().getStringArray(R.array.names);
        TypedArray defaultPhotos = getResources().obtainTypedArray(R.array.photos);

        for (int i = 0; i < defaultNames.length; i++) {
            String name = defaultNames[i];
            Uri imageURI = Uri.parse("android.resource://" + MainActivity.class.getPackage().getName() + "/" + defaultPhotos.getResourceId(i, 0));

            Person person = new Person(name, imageURI);
            people.add(person);
        }
    }
}
