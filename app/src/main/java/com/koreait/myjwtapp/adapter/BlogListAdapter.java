package com.koreait.myjwtapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.koreait.myjwtapp.DetailActivity;
import com.koreait.myjwtapp.R;
import com.koreait.myjwtapp.interfaces.OnBlogListRefresh;
import com.koreait.myjwtapp.repository.models.response.common.Data;
import com.koreait.myjwtapp.utils.OnSingleClickListener;

import java.util.ArrayList;
import java.util.List;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {

    private Context context;
    private List<Data> list = new ArrayList<>();
    private OnBlogListRefresh onBlogListRefresh;

    public void setOnBlogListRefresh(OnBlogListRefresh onBlogListRefresh) {
        this.onBlogListRefresh = onBlogListRefresh;
    }

    public BlogListAdapter(Context context) {
        this.context = context;
    }

    public void setItemData(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_blog_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = list.get(position);
        // https://picsum.photos/200/300?random=1
        Glide.with(holder.imageView.getContext())
                .load("https://picsum.photos/200/300?random=" + position)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerCrop()
                .into(holder.imageView);
        holder.titleTv.setText(data.getTitle());
        holder.userNameTv.setText(data.getUser().getUsername());
        holder.contentTv.setText(data.getContent());

        // new OnSingleClickListener() <- 구글링 방식
        holder.itemView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                if (onBlogListRefresh != null) {
                    onBlogListRefresh.movePage(data);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;
        TextView titleTv;
        TextView userNameTv;
        TextView contentTv;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.blogCardImage);
            titleTv = itemView.findViewById(R.id.blogTitleTv);
            userNameTv = itemView.findViewById(R.id.userNameTv);
            contentTv = itemView.findViewById(R.id.blogContentTv);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
