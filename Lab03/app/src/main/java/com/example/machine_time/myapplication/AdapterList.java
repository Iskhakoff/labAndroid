package com.example.machine_time.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterList extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    public AdapterList(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        Product product = getProduct(position);

        ((ImageView)view.findViewById(R.id.poster)).setImageResource(product.poster);
        ((TextView)view.findViewById(R.id.titleTv)).setText(product.title);
        ((TextView)view.findViewById(R.id.countryTv)).setText(product.country);
        ((TextView)view.findViewById(R.id.ratingTv)).setText(String.valueOf(product.rating));
        ((TextView)view.findViewById(R.id.descriptionTv)).setText(String.valueOf(product.description));

        return view;
    }

    Product getProduct(int position) {
        return ((Product) getItem(position));
    }


}
