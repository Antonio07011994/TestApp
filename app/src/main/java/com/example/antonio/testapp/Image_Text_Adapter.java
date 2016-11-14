package com.example.antonio.testapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by antonio on 09.11.16.
 */
public class Image_Text_Adapter extends ArrayAdapter<Item> {

    private final Activity context;
    private final List<Item> items;
    private Picasso picasso;

    public Image_Text_Adapter(Activity context, List<Item> items) {
        super(context, R.layout.image_text_item, items);
        this.context = context;
        this.items = items;
        picasso = new Picasso.Builder(context)
                .memoryCache(new LruCache(context))
                .build();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.image_text_item, null, true);
        TextView text = (TextView)rowView.findViewById(R.id.item_text);
        ImageView photo = (ImageView)rowView.findViewById(R.id.item_image);
        final ProgressBar progressBar = (ProgressBar)rowView.findViewById(R.id.loading);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        picasso.with(context)
                .load(items.get(position).getPhoto())
                .fit()
                .error(R.drawable.failed_to_load_image)
                .into(photo, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        text.setText(items.get(position).getFirstName()+" "+items.get(position).getLastName());
        return rowView;
    }
}
