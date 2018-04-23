package com.example.machine_time.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutProduct extends AppCompatActivity {

    ImageView posterAbout;
    TextView titleTvAbout, countryTvAbout, ratingTvAbout, descriptionTvAbout;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);



        posterAbout = (ImageView)findViewById(R.id.posterAbout);
        titleTvAbout = (TextView)findViewById(R.id.titleTvAbout);
        countryTvAbout = (TextView)findViewById(R.id.countryTvAbout);
        ratingTvAbout = (TextView)findViewById(R.id.ratingTvAbout);
        descriptionTvAbout = (TextView)findViewById(R.id.descriptionTvAbout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Intent getForAbout = getIntent();

        Bundle extras = getForAbout.getExtras();
        Bitmap bmp = (Bitmap)extras.getParcelable("imageFilm");


        titleTvAbout.setText(getForAbout.getStringExtra("titleFilm"));
        countryTvAbout.setText(getForAbout.getStringExtra("countryFilm"));
        ratingTvAbout.setText(getForAbout.getStringExtra("ratingFilm"));
        descriptionTvAbout.setText(getForAbout.getStringExtra("descFilm"));
        posterAbout.setImageBitmap(bmp);

        setTitle(getForAbout.getStringExtra("titleFilm"));


    }
}
