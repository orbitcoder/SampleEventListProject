package com.joshtalks.sample.requests.network;

import com.joshtalks.sample.response.PostResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("{extraPath}")
    Call<PostResponse> getAllEventPage(@Path("extraPath") String extraPath);
}