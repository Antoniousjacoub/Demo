package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.antonio.multipleitemslist2.ShowImage.PhotoFullPopupWindow;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import static com.example.antonio.multipleitemslist2.CustomView.eatFoodyImages;
import static com.example.antonio.multipleitemslist2.Settings.isConnectingToInternet;

public class PicassoExampleActivity extends AppCompatActivity {
    private String URL_Header="https://";
    private Button btn_check_internet;
    private GridView usage_example_gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picsso_example);
        usage_example_gridview = findViewById(R.id.usage_example_gridview);
        usage_example_gridview.setAdapter(new ImageListAdapter(PicassoExampleActivity.this, eatFoodyImages));

        btn_check_internet = findViewById(R.id.btn_check_internet);
        btn_check_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnectingToInternet(PicassoExampleActivity.this)) {
                    // Internet Connection is not present
                    Toast.makeText(PicassoExampleActivity.this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
                    // stop executing code by return
                    return;
                }else {
                    Toast.makeText(PicassoExampleActivity.this,"Connected...",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public class ImageListAdapter extends ArrayAdapter {
        private Context context;
        private LayoutInflater inflater;

        private String[] imageUrls;

        public ImageListAdapter(Context context, String[] imageUrls) {
            super(context, R.layout.grid_item, imageUrls);

            this.context = context;
            this.imageUrls = imageUrls;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.grid_item, parent, false);
            }
           ImageView image_gridView=convertView.findViewById(R.id.image_gridView);
            image_gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new PhotoFullPopupWindow(context, R.layout.popup_photo_full, view,  null,URL_Header+imageUrls[position]);
                }
            });

            Picasso.with(context)
                    .load(URL_Header+imageUrls[position])
                    .fit() // will explain later
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into((ImageView) convertView);

            return convertView;
        }
    }




}
