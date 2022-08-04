package com.example.moremovieslesson12;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity  {
    Button btnFilterByRating;
    Spinner spinner;
    ArrayList<Movies> alMovies;
    ArrayList<String> alRating;
    ListView lv;
    boolean toggleRating = false;
    CustomAdapter caMovies;
    ArrayAdapter<String>aaRating;
    DBHelper dbh = new DBHelper(ShowListActivity.this);



    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        //Linking of UI variables
        spinner = findViewById(R.id.spinnerRating2);
        lv = findViewById(R.id.ListViewMovies);
        btnFilterByRating = findViewById(R.id.BShowAll);
        alRating = new ArrayList<String>();
        aaRating = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alRating);
        load();
        spinner.setAdapter(aaRating);
        spinner.setSelected(false);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies selected = alMovies.get(position);
                System.out.println("SELECT" + selected.getTitle());
                Intent i = new Intent(ShowListActivity.this,
                        EditActivity.class);
                i.putExtra("Selected", selected);
                startActivity(i);
            }
        });


        btnFilterByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choseRating = alRating.get(spinner.getSelectedItemPosition());
                if (choseRating.equalsIgnoreCase("NONE")) {
                    load();
                } else {
                    alMovies.clear();
                    alMovies.addAll(dbh.getAllMoviesByRating(choseRating));
                    caMovies.notifyDataSetChanged();
                    Toast.makeText(ShowListActivity.this, spinner.getSelectedItem().toString() + " is FILTERED", Toast.LENGTH_LONG).show();
                }


            }
        });
    }


        private void load()
        {
            alMovies = dbh.getAllMovies();
            caMovies = new CustomAdapter(this,
                    R.layout.row, alMovies);
            lv.setAdapter(caMovies);
            loadSpinner();
        }
        private void loadSpinner ()
        {
            alRating.clear();
            alRating.addAll(dbh.getRating());
//            Toast.makeText(ShowListActivity.this, alRating.get(0) + "",
//                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onResume() {
            super.onResume();
            load();
        }
    }
