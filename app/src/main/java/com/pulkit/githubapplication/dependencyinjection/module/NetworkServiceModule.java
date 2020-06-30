package com.pulkit.githubapplication.dependencyinjection.module;

import com.pulkit.githubapplication.service.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class NetworkServiceModule {

    @Provides
    @Singleton
    GithubService provideNewsService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

}
