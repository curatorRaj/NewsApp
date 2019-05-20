package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


    import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class CoustumAdapter extends ArrayAdapter<News> {
    private ArrayList<News> news;
    private Context context;

    public CoustumAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.context=context;
        news = (ArrayList<News>) objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);
        News currentob = news.get(position);
        TextView text = convertView.findViewById(R.id.card_text);
       // TextView date =convertView.findViewById(R.id.card_date);
        ImageView image = convertView.findViewById(R.id.card_image);

        text.setText(currentob.getTitle());

        Glide
                .with(context)
                .load(currentob.getImage())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image);
        image.setVisibility(View.VISIBLE);
        return convertView;
    }
}


