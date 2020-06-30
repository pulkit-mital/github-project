package com.pulkit.githubapplication.service;

import com.pulkit.githubapplication.model.PullRequest;
import com.pulkit.githubapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GithubService {

    @GET("/repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> getGithubPullRequests(@Header ("Authorization") String authToken, @Path("owner") String owner, @Path("repo") String repo, @Query("state") String state);

    @GET()
    Call<User> getUsers(@Header ("Authorization") String authToken, @Url String url);
}
