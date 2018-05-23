package com.example.machine_time.lab02;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<User> users;

    public AdapterListView(Context context, ArrayList<User> _users) {
        ctx = context;
        users = _users;
        lInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_listview, parent, false);
        }

        User user = getUser(position);

        ((TextView)view.findViewById(R.id.loginRatingTv)).setText(user.login);
        ((TextView)view.findViewById(R.id.ratingTv)).setText(String.valueOf(user.rating));


        return view;
    }

    User getUser(int position){
        return((User)getItem(position));
    }
}
