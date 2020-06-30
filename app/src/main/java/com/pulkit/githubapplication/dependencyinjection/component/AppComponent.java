package com.pulkit.githubapplication.dependencyinjection.component;

import com.pulkit.githubapplication.GithubApplication;
import com.pulkit.githubapplication.dependencyinjection.module.ActivityBuilderModule;
import com.pulkit.githubapplication.dependencyinjection.module.AppModule;
import com.pulkit.githubapplication.dependencyinjection.module.NetworkServiceModule;
import com.pulkit.githubapplication.dependencyinjection.module.RecyclerAdapterModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class, NetworkServiceModule.class, AndroidSupportInjectionModule.class, ActivityBuilderModule.class, RecyclerAdapterModule.class})
public interface AppComponent extends AndroidInjector<GithubApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<GithubApplication> {
    }
}
