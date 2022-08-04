package com.example.moremovieslesson12;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Movies> {

    Context parent_context;
    int layout_id;
    ArrayList<Movies> alMovies;

    public CustomAdapter(Context context, int resource,  ArrayList<Movies> objects){
        super(context, resource, objects);

        this.parent_context = context;
        layout_id = resource;
        alMovies = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object - Get
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row - Read
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding - Find and Bind
        TextView tvID = rowView.findViewById(R.id.textViewID);
        TextView tvTitle = rowView.findViewById(R.id.textViewMovieTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);

        // Obtain the Android Version information based on the position - Find
        Movies currentMovies = alMovies.get(position);

        // Set values to the TextView to display the corresponding information - Populate
        tvID.setText(currentMovies.getId()+"");
        tvTitle.setText(currentMovies.getTitle());
        tvGenre.setText(currentMovies.getGenre());
        tvYear.setText(currentMovies.getYear()+"");
        if (currentMovies.getRating().equalsIgnoreCase("PG13")){
            ivRating.setImageResource(R.drawable.pg13);

        }
        else if (currentMovies.getRating().equalsIgnoreCase("NC16")){
            ivRating.setImageResource(R.drawable.nc16);

        }
        if (currentMovies.getRating().equalsIgnoreCase("M18")){
            ivRating.setImageResource(R.drawable.m18);

        }
        if (currentMovies.getRating().equalsIgnoreCase("R21")){
            ivRating.setImageResource(R.drawable.r21);

        }





        return rowView;
    }



}
