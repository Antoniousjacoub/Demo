package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private MyCustomAdapter mAdapter;
    private ListView listView;
    private TextView btnGetMoreResults;
    private int preLast;
    private Button btn_youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);

        mAdapter = new MyCustomAdapter(this);
        for (int i = 1; i < 20; i++) {
            mAdapter.addItem("item " + i);
            if (i % 4 == 0) {
                mAdapter.addSeparatorItem("separator " + i);
            }
        }
        listView.setAdapter(mAdapter);
        initViews();
        listView.setOnScrollListener(this);

        View footerLayout =  getLayoutInflater().inflate(R.layout.footerview,null);
        btnGetMoreResults = footerLayout.findViewById(R.id.btnGetMoreResults);
        listView.addFooterView(footerLayout);
        btnGetMoreResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CommentsActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initViews(){
Button btn_test_framlaout = findViewById(R.id.btn_test_framlaout);
        btn_test_framlaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent = new Intent(MainActivity.this,FramlayoutActivity.class);
startActivity(intent);
            }
        });
        Button btn_set_image=findViewById(R.id.btn_set_image);
        btn_set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
              startActivity(intent);
            }
        });

        btn_youtube = findViewById(R.id.btn_youtube);
        btn_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
              YoutubeFragment  fragment =new YoutubeFragment();
                ft.add(android.R.id.content,fragment,"YoutubeFragment");
                ft.commit();

            }
        });

    }
//    private void loadFragment(Fragment fragment) {
//// create a FragmentManager
//        android.app.FragmentManager fm = getFragmentManager();
//// create a FragmentTransaction to begin the transaction and replace the Fragment
//        android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
//// replace the FrameLayout with new Fragment
//        fragmentTransac+tion.replace(android.R.id.content, fragment);
//        fragmentTransaction.commit(); // save the changes
//    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        switch(absListView.getId())
        {
            case R.id.list_item:

                // Make your calculation stuff here. You have all your
                // needed info from the parameters of this function.

                // Sample calculation to determine if the last
                // item is fully visible.
                final int lastItem = firstVisibleItem + visibleItemCount;

                if(lastItem == totalItemCount)
                {
                    Toast.makeText(MainActivity.this,"reached bottom",Toast.LENGTH_LONG).show();
                    for (int i = 1; i < 20; i++) {
                        mAdapter.addItem("item " + i);
                        if (i % 4 == 0) {
                            mAdapter.addSeparatorItem("separator " + i);
                        }
                    }
                    listView.setAdapter(mAdapter);

                   // mAdapter.notifyDataSetChanged();
                    Log.d("itmes", String.valueOf(totalItemCount));
//                    if(preLast!=lastItem)
//                    {
//                        //to avoid multiple calls for last item
//                        Log.d("Last", "Last");
//                        preLast = lastItem;
//                      //  Toast.makeText(MainActivity.this,"reached bottom",Toast.LENGTH_LONG).show();
//
//                    }
                }
        }
    }

    @Override
    public void onBackPressed() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.smoothScrollToPosition(0);
            }
        });
        super.onBackPressed();
    }


}

