package com.pulkit.githubapplication.model;

import com.google.gson.annotations.SerializedName;

public class PullRequest {

    private int id;
    private String title;
    private String body;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("closed_at")
    private String closedAt;
    private String state;
    private User user;

    public PullRequest() {
    }

    public PullRequest(int id, String title, String body, String createdAt, String closedAt, String state) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.closedAt = closedAt;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
