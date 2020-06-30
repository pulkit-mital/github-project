package com.pulkit.githubapplication.dependencyinjection.module;

import com.pulkit.githubapplication.adapters.GithubPullRequestRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerAdapterModule {

    @Provides
    @Singleton
    public GithubPullRequestRecyclerAdapter provideNewsArticleRecyclerAdapter() {
        return new GithubPullRequestRecyclerAdapter();
    }

}
