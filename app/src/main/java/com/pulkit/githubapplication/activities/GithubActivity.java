package com.pulkit.githubapplication.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.pulkit.githubapplication.R;
import com.pulkit.githubapplication.adapters.GithubPullRequestRecyclerAdapter;
import com.pulkit.githubapplication.databinding.ActivityGithubBinding;
import com.pulkit.githubapplication.viewmodel.GithubViewModel;
import com.pulkit.githubapplication.viewmodel.GithubViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class GithubActivity extends AppCompatActivity {

    @Inject
    GithubViewModelFactory githubViewModelFactory;

    @Inject
    GithubPullRequestRecyclerAdapter githubPullRequestRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityGithubBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_github);
        binding.setLifecycleOwner(this);
        binding.layoutToolBar.toolbar.setTitle(getResources().getString(R.string.app_name));
        GithubViewModel githubViewModel = ViewModelProviders.of(this, githubViewModelFactory).get(GithubViewModel.class);
        binding.setGithubViewModel(githubViewModel);
        binding.setGithubPullRequestRecyclerAdapter(githubPullRequestRecyclerAdapter);
    }


}
