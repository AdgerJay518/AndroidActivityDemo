package com.example.activity.entity;


import java.util.List;

public class Activity {

    private String name;
    private String avatar;
    private String follows;
    private String videos;
    private String views;
    private List<Data> list;

    public Activity() {
    }

    public Activity(String name, String avatar, String follows, String videos, String views, List<Data> list) {
        this.name = name;
        this.avatar = avatar;
        this.follows = follows;
        this.videos = videos;
        this.views = views;
        this.list = list;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollows() {
        return follows;
    }

    public void setFollows(String follows) {
        this.follows = follows;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }
}
