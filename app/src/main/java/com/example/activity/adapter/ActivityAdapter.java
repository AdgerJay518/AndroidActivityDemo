package com.example.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.activity.R;
import com.example.activity.entity.Data;
import java.util.List;


public class ActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Data> mActivities;
    private LayoutInflater mInflater;

    // 普通布局
    private final int TYPE_ITEM = 1;
    // 脚布局
    private final int TYPE_FOOTER = 2;

    public ActivityAdapter(Context context,List<Data> activities){
        mInflater = LayoutInflater.from(context);
        this.mActivities=activities;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_ITEM){
            View inflate = mInflater.inflate(R.layout.activity_item, parent, false);
            return new ActivityHolder(inflate,this);
        }
        else if (viewType == TYPE_FOOTER){
            View inflate = mInflater.inflate(R.layout.activity_footer, parent, false);
            return new FootViewHolder(inflate,this);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ActivityHolder){
            Data mCurrent = mActivities.get(position);
            int placeColor = Color.parseColor("#eaeaea");
            ActivityHolder recyclerViewHolder = (ActivityHolder) holder;
            Glide.with(recyclerViewHolder.activityView1).load(mCurrent.getImage()).placeholder(new ColorDrawable(placeColor)).diskCacheStrategy(DiskCacheStrategy.NONE).into(recyclerViewHolder.activityView1);
            recyclerViewHolder.activityView2.setVisibility(View.VISIBLE);
            recyclerViewHolder.activityView2.setText(mCurrent.getMovie());
        }
        else if (holder instanceof FootViewHolder){
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            footViewHolder.text.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mActivities.size()+1;
    }

    class FootViewHolder extends RecyclerView.ViewHolder {

        public final TextView text;
        final  ActivityAdapter mAdapter;
        FootViewHolder(View itemView,ActivityAdapter adapter) {
            super(itemView);
            text = itemView.findViewById(R.id.footer_text);
            this.mAdapter=adapter;
        }
    }


    class ActivityHolder extends RecyclerView.ViewHolder {
        public final ImageView activityView1;
        public final TextView activityView2;
        final  ActivityAdapter mAdapter;
        public ActivityHolder(View itemView,ActivityAdapter adapter) {
            super(itemView);
            activityView1 = itemView.findViewById(R.id.cover);
            activityView2 = itemView.findViewById(R.id.views);
            activityView1.getLayoutParams().height=447;
            this.mAdapter=adapter;
        }
    }

    //只有这个支持StaggeredGridLayoutManager
    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        System.out.println(holder.getLayoutPosition());
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams && getItemViewType(holder.getLayoutPosition())==TYPE_FOOTER) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }
}
