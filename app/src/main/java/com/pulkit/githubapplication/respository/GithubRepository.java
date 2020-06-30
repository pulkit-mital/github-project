package com.pulkit.githubapplication.respository;

import com.pulkit.githubapplication.BuildConfig;
import com.pulkit.githubapplication.GithubApplication;
import com.pulkit.githubapplication.model.PullRequest;
import com.pulkit.githubapplication.model.User;
import com.pulkit.githubapplication.service.GithubService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

import static com.pulkit.githubapplication.utils.NetworkUtil.isNetworkConnected;

/**
 * The class will provide access to web service to fetch data from network
 * as well as room database to get data when network connection is not there
 */
@Singleton
public class GithubRepository {

    private GithubService githubService;
    private GithubApplication githubApplication;

    @Inject
    public GithubRepository(GithubService githubService, GithubApplication githubApplication) {
        this.githubService = githubService;
        this.githubApplication = githubApplication;
    }


    public Observable<List<PullRequest>> getPullRequests(final String owner, final String repo ) {
        return Observable.create(new Observable.OnSubscribe<List<PullRequest>>() {
            @Override
            public void call(Subscriber<? super List<PullRequest>> subscriber) {
                if (isNetworkConnected(githubApplication.getApplicationContext())) {

                    Call<List<PullRequest>> call = githubService.getGithubPullRequests("token "+ BuildConfig.GITHUB_TOKEN, owner, repo, "closed");
                    try {

                        Response<List<PullRequest>> response = call.execute();
                        if (response.isSuccessful()) {
                            subscriber.onNext(response.body());
                        } else {
                            subscriber.onError(new Exception(response.errorBody().string()));
                        }

                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                } else {
                    subscriber.onError(new Exception("No Internet Connection"));
                }
            }
        });
    }

    public Observable<User> getUserInformation(final String url) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if (isNetworkConnected(githubApplication.getApplicationContext())) {

                    Call<User> call = githubService.getUsers("token "+ BuildConfig.GITHUB_TOKEN, url);
                    try {
                        Response<User> response = call.execute();
                        if (response.isSuccessful()) {
                            subscriber.onNext(response.body());
                        } else {
                            subscriber.onError(new Exception(response.errorBody().string()));
                        }

                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                } else {
                    subscriber.onError(new Exception("No Internet Connection"));
                }
            }
        });
    }

}
