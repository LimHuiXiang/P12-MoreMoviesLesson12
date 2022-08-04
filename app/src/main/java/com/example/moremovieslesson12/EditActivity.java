package com.example.moremovieslesson12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import id.ionbit.ionalert.IonAlert;

public class EditActivity extends AppCompatActivity {



    Button btnUpdate, btnDelete,btnCancel;
    EditText etTitle, etGenre,etYear,etID;
    Spinner spinner;
    String rating;
    DBHelper db=new DBHelper(EditActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        btnCancel=findViewById(R.id.bCancel);
        btnUpdate =findViewById(R.id.bUpdate);
        btnDelete =findViewById(R.id.bDelete);
        etID=findViewById(R.id.editTextMovieID2);
        etGenre=findViewById(R.id.editTextGenre2);
        spinner=findViewById(R.id.spinner2);
        etTitle=findViewById(R.id.editTextMovieTitle2);
        etYear=findViewById(R.id.editTextYear2);


        Intent i = getIntent();
        Movies movie = (Movies) i.getSerializableExtra("Selected");


        etID.setText(movie.getId()+"");
        etID.setEnabled(false);
        etTitle.setText(movie.getTitle());
        etGenre.setText(movie.getGenre());
        etYear.setText(movie.getYear()+"");


        int count=spinner.getCount();
        for(int a=0;a<count;a++)
        {
            String rating=spinner.getSelectedItem().toString();
            if(rating.equalsIgnoreCase(movie.getRating()))
            {
                spinner.setSelection(a);
            }
        }





        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movie.updateMovieDetails(Integer.parseInt(etID.getText().toString()),etTitle.getText().toString(),etGenre.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()),spinner.getSelectedItem().toString());
                db.updateMovie(movie);
                db.close();
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new IonAlert(EditActivity.this, IonAlert.WARNING_TYPE)
                        .setTitleText("Are you sure you want to delete the movie record??")
                        .setContentText("You won`t be able to see it anymore!")
                        .setConfirmText("Yes,delete the movie!")
                        .setConfirmClickListener(new IonAlert.ClickListener() {
                            @Override
                            public void onClick(IonAlert sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("The movie has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(IonAlert.SUCCESS_TYPE);
                                DBHelper db = new DBHelper(EditActivity.this);
                                db.deleteMovie(movie.getId());
                                finish();
                            }
                        })
                        .show();


            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new IonAlert(EditActivity.this, IonAlert.WARNING_TYPE)
                        .setTitleText("Are you sure you want to discard the changes?")
                        .setContentText("You wont be able to go back!")
                        .setConfirmText("Yes,discard the changes!")
                        .setConfirmClickListener(new IonAlert.ClickListener() {
                            @Override
                            public void onClick(IonAlert sDialog) {
                                sDialog
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your changes has been cancelled!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(IonAlert.SUCCESS_TYPE);

                                finish();
                                sDialog.dismiss();
                            }


                        })
                        .show();


            }
        });

    }
}