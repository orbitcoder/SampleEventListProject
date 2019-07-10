package com.joshtalks.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.joshtalks.sample.databinding.ActivityMainBinding;
import com.joshtalks.sample.response.PostResponse;
import com.joshtalks.sample.viewmodels.MainViewModel;
import com.joshtalks.sample.views.CustomAdapter;

import helpers.PaginationScrollListener;

public class MainActivity extends AppCompatActivity implements Observer<PostResponse> {

    private MainViewModel mViewModel;
    private ActivityMainBinding mBinding;
    private boolean isLoading = false;
    private int currentPage = 1;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        adapter = new CustomAdapter(this, null);
        mBinding.recyclerList.setAdapter(adapter);

        mViewModel.getDataForPage(currentPage).observe(this, this);

        initRV();

    }

    private void initRV() {
        mBinding.recyclerList.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) mBinding.recyclerList.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                //Increment page index to load the next one
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return 3;
            }

            @Override
            public boolean isLastPage() {
                return currentPage >= 3;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    public void loadNextPage() {
        mViewModel.getDataForPage(currentPage).observe(this, this);
    }


    @Override
    public void onChanged(PostResponse postResponse) {
        isLoading = false;
    if (adapter.getItemCount() > 0)
        adapter.addData(postResponse.getPosts());
    else
        adapter.updateData(postResponse.getPosts());

    }
}
