package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);

        mAdapter = new MyCustomAdapter();
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

        Button btn_set_image=findViewById(R.id.btn_set_image);
        btn_set_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
              startActivity(intent);
            }
        });
    }

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

    private class MyCustomAdapter extends BaseAdapter {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_SEPARATOR = 1;
        private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

        private ArrayList mData = new ArrayList();
        private LayoutInflater mInflater;



        private TreeSet mSeparatorsSet = new TreeSet();

        public MyCustomAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void addItem(final String item) {
            mData.add(item);
            //notifyDataSetChanged();
        }

        public void addSeparatorItem(final String item) {
            mData.add(item);
            // save separator position
            mSeparatorsSet.add(mData.size() - 1);
           // notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_MAX_COUNT;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            int type = getItemViewType(position);
            System.out.println("getView " + position + " " + convertView + " type = " + type);
            if (convertView == null) {
                holder = new ViewHolder();
                switch (type) {
                    case TYPE_ITEM:
                        convertView = mInflater.inflate(R.layout.item1, null);
                        holder.textView = (TextView)convertView.findViewById(R.id.text);
                        break;
                    case TYPE_SEPARATOR:
                        convertView = mInflater.inflate(R.layout.item2, null);
                        ////holder.textView = (TextView)convertView.findViewById(R.id.textSeparator);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
//            holder.textView.setText(mData.get(position).toString());
            return convertView;
        }

    }

    public static class ViewHolder {
        public TextView textView;
    }
}

