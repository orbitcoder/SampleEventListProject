package com.joshtalks.sample.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.joshtalks.sample.R;
import com.joshtalks.sample.databinding.CustomRowBinding;
import com.joshtalks.sample.model.EventPostModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<EventPostModel> dataList;
    private Context context;

    public CustomAdapter(Context context, List<EventPostModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void addData(ArrayList<EventPostModel> posts) {
        if (dataList == null)
            dataList = new ArrayList<>();
        dataList.addAll(posts);
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        CustomRowBinding mbinding;

        private ImageView coverImage;

        CustomViewHolder(CustomRowBinding itemView) {
            super(itemView.getRoot());
            mbinding = itemView;
        }

        public void bind(EventPostModel data) {
            mbinding.setData(data);
            mbinding.executePendingBindings();
            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(data.getThumbnail_image())
                    .placeholder((R.drawable.ic_launcher_background))
                    .error(R.drawable.ic_launcher_background)
                    .into(mbinding.imageView);
        }

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomRowBinding customRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_row, parent, false);

        return new CustomViewHolder(customRowBinding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        EventPostModel eventPost = dataList.get(position);
        holder.bind(eventPost);

      /*  Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);*/

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public void updateData(List<EventPostModel> data) {
        if (dataList == null)
            dataList = new ArrayList<>();

        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }
}