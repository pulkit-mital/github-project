package com.pulkit.githubapplication.dependencyinjection.module;

import com.pulkit.githubapplication.activities.GithubActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = ViewModelModule.class)
    abstract GithubActivity contributesNewsActivity();
}
