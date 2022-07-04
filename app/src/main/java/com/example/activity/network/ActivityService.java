package com.example.activity.network;


import com.example.activity.entity.Activity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActivityService {
    @GET("activity.json")
    Call<Activity> getActivity();
}
