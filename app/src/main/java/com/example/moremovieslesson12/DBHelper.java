package com.example.moremovieslesson12;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIES = "movie";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "RATING";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIES  +  "("
                +COLUMN_TITLE +" TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_GENRE + " TEXT," + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"SQL statement: " + createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_MOVIES + " ADD COLUMN  module_name TEXT ");
        //onCreate(db); // Delete as table already created

    }

    public long insertMovies(String title, String genre, int year, String rating){
        //table name? columns involved
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIES, null, values);
        db.close();

        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> movieslist = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id, title, genre, year, rating);
                movieslist.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movieslist;
    }



    public ArrayList<Movies> getAllMoviesByRating(String rating) {
        ArrayList<Movies> movieslist = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_RATING + "= ?";
        String[] args = {String.valueOf(rating)};

        Cursor cursor;
        cursor = db.query(TABLE_MOVIES, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                String ratings = cursor.getString(4);

                Movies newMovie = new Movies(id, title, singers, year, ratings);
                movieslist.add(newMovie);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return movieslist;
    }

    public ArrayList<String> getRating() {
        ArrayList<String> movies = new ArrayList<String>();
        movies.add("NONE");
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                COLUMN_RATING, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String rating=cursor.getString(0);

                movies.add(rating);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }


    public int updateMovie(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, data.getId());
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIES, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIES, condition, args);
        db.close();
        return result;
    }

}
