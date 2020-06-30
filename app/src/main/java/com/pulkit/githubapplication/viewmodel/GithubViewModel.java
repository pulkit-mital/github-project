package com.pulkit.githubapplication.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pulkit.githubapplication.model.PullRequest;
import com.pulkit.githubapplication.model.User;
import com.pulkit.githubapplication.respository.GithubRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * The class help us to fetch data using repository and will update UI
 * as it contains live data and UI is observing change in the data
 */
public class GithubViewModel extends ViewModel {

    private GithubRepository githubRepository;
    private Subscription githubSubscription;
    private MutableLiveData<List<PullRequest>> githubPullRequests = new MutableLiveData<>();
    private MutableLiveData<PullRequest> updateRequest = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Integer> isProgressVisible = new MutableLiveData<>();

    @Inject
    public GithubViewModel(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
        getRequests();
    }

    public Observable<PullRequest> getPullRequests() {


        return githubRepository.getPullRequests("google","jax")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<List<PullRequest>, Observable<PullRequest>>() {
                    @Override
                    public Observable<PullRequest> call(List<PullRequest> pullRequests) {
                        githubPullRequests.postValue(pullRequests);
                        return Observable.from(pullRequests)
                                .subscribeOn(Schedulers.io());
                    }
                });
    }

    public void getRequests(){
        isProgressVisible.setValue(View.VISIBLE);
        getPullRequests()
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<PullRequest, Observable<PullRequest>>() {
                    @Override
                    public Observable<PullRequest> call(PullRequest pullRequest) {
                        return getUserInformation(pullRequest);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PullRequest>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PullRequest pullRequest) {
                        updateRequest.postValue(pullRequest);
                        isProgressVisible.setValue(View.GONE);
                    }
                });
    }
    private Observable<PullRequest> getUserInformation(final PullRequest pullRequest){
        return githubRepository.getUserInformation(pullRequest.getUser().getUrl())
                .map(new Func1<User, PullRequest>() {
                    @Override
                    public PullRequest call(User user) {
                        pullRequest.setUser(user);
                        return pullRequest;
                    }
                }).subscribeOn(Schedulers.io());

    }






    public MutableLiveData<List<PullRequest>> getGithubPullRequests() {
        return githubPullRequests;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<Integer> getIsProgressVisible() {
        return isProgressVisible;
    }

    public MutableLiveData<PullRequest> getUpdateRequest() {
        return updateRequest;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (githubSubscription != null && !githubSubscription.isUnsubscribed()) {
            githubSubscription.unsubscribe();
        }
    }
}

