package com.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.activity.adapter.ActivityAdapter;
import com.example.activity.common.myItemDecoration;
import com.example.activity.entity.Activity;
import com.example.activity.network.ActivityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    private Activity activities;
    private RecyclerView mRecyclerView;
    private TextView name;
    private ImageView avatar;
    private TextView followers;
    private TextView videos;
    private TextView views;
    private ActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        name = findViewById(R.id.title_text);
        avatar = findViewById(R.id.avatar_img);
        followers = findViewById(R.id.follow_count_tv);
        videos = findViewById(R.id.video_count_tv);
        views = findViewById(R.id.play_count_tv);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.39.19:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ActivityService activityService = retrofit.create(ActivityService.class);
        Call<Activity> activity = activityService.getActivity();
        activity.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Call<Activity> call, Response<Activity> response) {
                Activity body = response.body();
                activities=body;
                name.setText(body.getName());
                Glide.with(avatar).load(body.getAvatar()).placeholder(new ColorDrawable(R.id.avatar_img)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(avatar);
                followers.setText(body.getFollows());
                videos.setText(body.getVideos());
                views.setText(body.getViews());
                //创建适配器
                mAdapter = new ActivityAdapter(MainActivity.this, activities.getList());
                //设置适配器
                mRecyclerView.setAdapter(mAdapter);
                //设置LayoutManager布局
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                //设置分割线
                //new myItemDecoration().Builder.verticalInnerSpace(1).horizontalInnerSpace(1);
                //new myItemDecoration().Builder().horizontalInnerSpace().build();
                myItemDecoration myItemDecoration = new myItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL);

                mRecyclerView.addItemDecoration(myItemDecoration);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);


                    }
                });

                //mRecyclerView.canScrollVertically(1);
            }

            @Override
            public void onFailure(Call<Activity> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}