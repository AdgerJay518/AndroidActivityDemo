package com.example.activity.entity;

public class Data {

    private String movie;
    private String image;

    public Data() {
    }

    public Data(String movie, String image) {
        this.movie = movie;
        this.image = image;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
