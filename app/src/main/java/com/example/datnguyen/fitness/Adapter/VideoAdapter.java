package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datnguyen.fitness.Model.Video;
import com.example.datnguyen.fitness.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Video> videos;

    public VideoAdapter(Context context, int layout, ArrayList<Video> videos) {
        this.context = context;
        this.layout = layout;
        this.videos = videos;
    }
    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imgThumb;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
            viewHolder.imgThumb = convertView.findViewById(R.id.imgThumb);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Video video = videos.get(position);
        viewHolder.txtTitle.setText(video.getTitle());
        Picasso.with(context).load(video.getThumbnail()).into(viewHolder.imgThumb);
        return convertView;
    }
}
