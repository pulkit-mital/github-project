package com.pulkit.githubapplication.dependencyinjection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pulkit.githubapplication.scope.ViewModelKey;
import com.pulkit.githubapplication.viewmodel.GithubViewModel;
import com.pulkit.githubapplication.viewmodel.GithubViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel.class)
    abstract ViewModel bindNewsViewModel(GithubViewModel githubViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory githubViewModelFactory);
}
