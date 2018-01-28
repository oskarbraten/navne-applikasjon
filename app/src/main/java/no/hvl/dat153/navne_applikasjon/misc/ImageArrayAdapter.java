package no.hvl.dat153.navne_applikasjon.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import no.hvl.dat153.navne_applikasjon.R;

public class ImageArrayAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Person> people;

    public ImageArrayAdapter(Context c, ArrayList<Person> people) {
        mContext = c;
        this.people = people;
    }

    public int getCount() {
        return people.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            imageView = (ImageView) inflater.inflate(R.layout.photo, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(people.get(position).getImageURI());
        return imageView;
    }
}
