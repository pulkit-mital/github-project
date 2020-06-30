package com.pulkit.githubapplication.utils;


import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pulkit.githubapplication.adapters.GithubPullRequestRecyclerAdapter;
import com.pulkit.githubapplication.model.PullRequest;

import java.text.ParseException;
import java.util.List;

public class BindingUtils {


    @BindingAdapter({"items","adapter", "updateItem"})
    public static void loadRecyclerView(RecyclerView recyclerView, List<PullRequest> pullRequests, GithubPullRequestRecyclerAdapter adapter, PullRequest pullRequest){
        recyclerView.setAdapter(adapter);
        if(pullRequest != null && pullRequest.getUser() != null && NetworkUtil.isEmptyOrNull(pullRequest.getUser().getName())) {
            adapter.updateRequest(pullRequest);
        }else {
            adapter.setNewsArticles(pullRequests);
        }
    }

    @BindingAdapter({"imageUrl","text"})
    public static void loadAuthor(final TextView textView, String imageUrl, String text){
        textView.setText(text);
        Glide.with(textView.getContext()).load(imageUrl).circleCrop().into(new CustomTarget<Drawable>(60, 60) {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                textView.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);

            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                textView.setCompoundDrawablesWithIntrinsicBounds(placeholder, null, null, null);
            }
        });
    }

    @BindingAdapter({"date"})
    public static void loadDate(TextView textView, String date){
        try {
            textView.setText(DateTimeUtils.getDateFormatted(date));
        }catch (ParseException ex){
            textView.setText(date);
        }
    }
}
