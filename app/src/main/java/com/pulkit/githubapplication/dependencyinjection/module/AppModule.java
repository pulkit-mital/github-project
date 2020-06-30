package com.pulkit.githubapplication.dependencyinjection.module;

import android.content.Context;

import com.pulkit.githubapplication.GithubApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideApplicationContext(GithubApplication githubApplication) {
        return githubApplication.getApplicationContext();
    }
}
