package com.example.antonio.multipleitemslist2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class FramlayoutActivity extends YouTubeBaseActivity {
    public static final String API_KEY = "AIzaSyBx7v0YOb1404fDO7EbfMx4l87raxezDWFw";

    //https://www.youtube.com/watch?v=<VIDEO_ID>
    public static final String VIDEO_ID = "-m3V8w_7vhk";
    private ImageView imageView;
    private FrameLayout frame_layout;
    private LinearLayout container_icons;
    private ImageView imageView1;
    private FragmentManager fm;
    private YouTubePlayerFragment playerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framlayout);
        initViews();
        initPlayer();
    }


    private void initViews(){
        frame_layout = findViewById(R.id.frame_layout);
        container_icons = findViewById(R.id.container_icons);
        imageView = findViewById(R.id.detete);
        imageView1 = findViewById(R.id.edit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FramlayoutActivity.this,"deleted clicked",Toast.LENGTH_LONG).show();
                FragmentTransaction ft = fm.beginTransaction();
                DeletePlayerFragment deletePlayerFragment = new  DeletePlayerFragment();
                ft.replace(R.id.frame_layout, deletePlayerFragment, "");
                ft.commit();

            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FramlayoutActivity.this,"edit clicked",Toast.LENGTH_LONG).show();

            }
        });

        //container_icons.bringChildToFront(frame_layout);



    }


private void initPlayer(){


    //Initializing and adding YouTubePlayerFragment
    fm = getFragmentManager();
    String tag = YouTubePlayerFragment.class.getSimpleName();


    YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
    if (playerFragment == null) {
        FragmentTransaction ft = fm.beginTransaction();
        playerFragment = YouTubePlayerFragment.newInstance();
        ft.replace(R.id.frame_layout, playerFragment, tag);
        ft.commit();
    }

    playerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.cueVideo(VIDEO_ID);

        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            Toast.makeText(getApplicationContext(), "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
        }
    });
}

}
