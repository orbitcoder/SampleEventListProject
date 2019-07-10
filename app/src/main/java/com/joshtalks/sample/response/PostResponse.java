package com.joshtalks.sample.response;

import com.joshtalks.sample.model.EventPostModel;

import java.util.ArrayList;

public class PostResponse {
    private String page;
    private boolean isError = false;

    private ArrayList<EventPostModel> posts;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public ArrayList<EventPostModel> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<EventPostModel> posts) {
        this.posts = posts;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }
}