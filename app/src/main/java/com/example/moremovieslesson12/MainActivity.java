package com.example.moremovieslesson12;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    TextView tvTitle, tvGenre, tvYear, tvRating;
    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvTitle = findViewById(R.id.tvTitle);
        tvGenre=findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.bInsert);
        btnShowList = findViewById(R.id.bShowList);
        spinner = findViewById(R.id.spinner);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String yearString = etYear.getText().toString();
                String spinnerSelected = spinner.getSelectedItem().toString();


                boolean number = TextUtils.isDigitsOnly(etYear.getText());

                if (title.trim().length() == 0 || genre.trim().length() == 0
                        || yearString.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter all information to add movies!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    if (number == true) {
                        int getYear = Integer.parseInt(yearString);

                        long inserted_id = dbh.insertMovies(title, genre, getYear, spinnerSelected);
                        if (inserted_id != -1) {
                            Toast.makeText(MainActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                            clear();
                        }
                    }
                }
            }


            private void clear() {
                etTitle.setText("");
                etGenre.setText("");
                etYear.setText("");
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //startActivity
                Intent i = new Intent(MainActivity.this, ShowListActivity.class);
                startActivity(i);
            }
        });





    }


}
