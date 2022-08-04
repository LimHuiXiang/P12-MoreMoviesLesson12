package com.example.moremovieslesson12;

import java.io.Serializable;

public class Movies implements Serializable {
    private int id;
    private String title;
    private String genre;
    private int year;
    private String rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movies(int id, String title, String genre, int year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public void updateMovieDetails(int id,String title,String genre, int year, String rating)
    {
        this.id=id;
        this.title = title;
        this.genre =genre;
        this.rating=rating;
        this.year=year;
    }



    public String toString() { return "ID: " + id + ",\nTitle: " + title +",\nGenre: "+ genre +",\nYear: "+ year+
            " ,\nRating: "+rating;  }

}

