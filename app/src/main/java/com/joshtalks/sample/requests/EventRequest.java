package com.joshtalks.sample.requests;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.joshtalks.sample.requests.network.GetDataService;
import com.joshtalks.sample.requests.network.RetrofitClientInstance;
import com.joshtalks.sample.response.PostResponse;

import okhttp3.Cache;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRequest {

    public static LiveData<PostResponse> getEventData(int pageNumber) {
        final MutableLiveData<PostResponse> liveData = new MutableLiveData<>();
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<PostResponse> call = service.getAllEventPage(getExtraPathForPage(pageNumber));
        call.enqueue(new Callback<PostResponse>() {

            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                PostResponse response = new PostResponse();
                response.setError(true);
                liveData.setValue(response);
            }

        });

        return liveData;
    }

    private static String getExtraPathForPage(int page) {
        switch (page) {
            case 1:
                return "59b3f0b0100000e30b236b7e";
            case 2:
                return "59ac28a9100000ce0bf9c236";
            case 3:
                return "59ac293b100000d60bf9c239";
        }
        return "59b3f0b0100000e30b236b7e";
    }


}
