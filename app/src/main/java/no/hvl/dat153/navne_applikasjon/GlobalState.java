package no.hvl.dat153.navne_applikasjon;

import android.app.Application;
import android.content.res.TypedArray;

import java.util.ArrayList;

public class GlobalState extends Application {

    private ArrayList<Person> people;

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String[] defaultNames = getResources().getStringArray(R.array.names);
        TypedArray defaultPhotos = getResources().obtainTypedArray(R.array.photos);



    }

}
