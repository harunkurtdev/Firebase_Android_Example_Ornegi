package com.serifgungor.firebasedatabaseornegi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> users;
    LayoutInflater layoutInflater;


    public GridAdapter(Context context,ArrayList<User> users){
        this.context = context;
        this.users = users;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View v = layoutInflater.inflate(R.layout.custom_gridview_row,null);

        TextView tvAd,tvSoyad,tvEmail;
        tvAd = v.findViewById(R.id.tvAd);
        tvSoyad = v.findViewById(R.id.tvSoyad);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvAd.setText(users.get(position).getAd());
        tvSoyad.setText(users.get(position).getSoyad());
        tvEmail.setText(users.get(position).getEmail());

        return v;
    }
}
