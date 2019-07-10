package com.joshtalks.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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

        mBinding.retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage = 1;
                loadNextPage();
            }
        });

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
        if (adapter.getItemCount() > 0) {
            showListView();
            adapter.addData(postResponse.getPosts());
        } else if (postResponse.isError()) {
            showErrorView();
        } else if (postResponse.getPosts().size() == 0) {
            showEmptyView();
        } else {
            showListView();
            adapter.updateData(postResponse.getPosts());

        }
    }

    private void showEmptyView() {
        mBinding.recyclerList.setVisibility(View.GONE);
        mBinding.empty.setVisibility(View.VISIBLE);
        mBinding.errorView.setVisibility(View.GONE);
    }

    private void showListView() {
        if (mBinding.recyclerList.getVisibility() != View.VISIBLE) {
            mBinding.recyclerList.setVisibility(View.VISIBLE);
            mBinding.empty.setVisibility(View.GONE);
            mBinding.errorView.setVisibility(View.GONE);
        }
    }

    private void showErrorView() {
        mBinding.recyclerList.setVisibility(View.GONE);
        mBinding.empty.setVisibility(View.GONE);
        mBinding.errorView.setVisibility(View.VISIBLE);
    }
}
