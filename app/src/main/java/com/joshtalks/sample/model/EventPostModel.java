package com.joshtalks.sample.model;

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class EventPostModel {

    @SerializedName("id")
    String id;

    @SerializedName("thumbnail_image")
    String thumbnail_image;

    @SerializedName("event_name")
    String event_name;

    @SerializedName("event_date")
    long event_date;

    @SerializedName("views")
    long views;

    @SerializedName("likes")
    long likes;

    @SerializedName("shares")
    long shares;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public long getEvent_date() {
        return event_date;
    }

    public void setEvent_date(long event_date) {
        this.event_date = event_date;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getShares() {
        return shares;
    }

    public void setShares(long shares) {
        this.shares = shares;
    }

    public String getFormatedDate(){
        try {
            return DateFormat.format("MM/dd/yyyy", new Date(event_date)).toString();
        }catch (Exception e){

        }
        return "";
    }
}
