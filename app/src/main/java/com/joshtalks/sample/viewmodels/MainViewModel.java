package com.joshtalks.sample.viewmodels;

import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.joshtalks.sample.requests.EventRequest;
import com.joshtalks.sample.response.PostResponse;

public class MainViewModel extends ViewModel {
//    MediatorLiveData<PostResponse> responseLivedata;

    public LiveData<PostResponse> getDataForPage(int page) {
        EventRequest eventRequest = new EventRequest();
        return eventRequest.getEventData(page);
    }

}
