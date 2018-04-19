package com.example.machine_time.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FilmsFragment extends Fragment{

    ArrayList<Product> products = new ArrayList<Product>();
    AdapterList adapterList;


    boolean recap = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Фильмы");

        View rootView = inflater.inflate(R.layout.films_fragment, container, false);

        if(!recap) fillData();
        adapterList = new AdapterList(getContext(), products);
        ListView filmsLv = (ListView)rootView.findViewById(R.id.filmsLv);
        filmsLv.setAdapter(adapterList);

        filmsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView titleFilm = (TextView)view.findViewById(R.id.titleTv);
                TextView countryFilm = (TextView)view.findViewById(R.id.countryTv);
                TextView ratingFilm = (TextView)view.findViewById(R.id.ratingTv);
                TextView descFilm = (TextView)view.findViewById(R.id.descriptionTv);
                ImageView imageFilm = (ImageView)view.findViewById(R.id.poster);
                imageFilm.buildDrawingCache();

                Intent aboutProduct = new Intent(getActivity(), AboutProduct.class);
                aboutProduct.putExtra("titleFilm", titleFilm.getText().toString());
                aboutProduct.putExtra("countryFilm", countryFilm.getText().toString());
                aboutProduct.putExtra("ratingFilm", ratingFilm.getText().toString());
                aboutProduct.putExtra("descFilm", descFilm.getText().toString());
                aboutProduct.putExtra("imageFilm", imageFilm.getDrawingCache());
                startActivity(aboutProduct);

            }
        });

        return rootView;
    }

    void fillData(){
        for (int i = 0; i < 3; i++) {
            products.add(new Product("Фильм" + (i + 1), "Страна" + i, i, "description", R.drawable.film));
        }
        recap = true;
    }
}
