package com.example.antonio.multipleitemslist2;

/**
 * Created by antonio on 2/17/18.
 */

public class Ulits {
//    MyFragmentClass test = (MyFragmentClass) getSupportFragmentManager().findFragmentByTag("testID");
//if (test != null && test.isVisible()) {
//        //DO STUFF
//    }
//else {
//        //Whatever
//    }

    //icon drawer
    //mDrawerToggle.syncState();
    //for videoplayer
    //https://github.com/afollestad/easy-video-player

//    private void implementScrollListener() {
//        listView.setOnScrollListener(new OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
//                // If scroll state is touch scroll then set userScrolled
//                // true
//                if (scrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    userScrolled = true;
//
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                // Now check if userScrolled is true and also check if
//                // the item is end then update list view and set
//                // userScrolled to false
//                if (userScrolled
//                        && firstVisibleItem + visibleItemCount == totalItemCount) {
//
//                    userScrolled = false;
//                    updateListView();
//                }
//            }
//        });
//    }
//
// Implement scroll listener
//private void implementScrollListener() {
//    listRecyclerView
//            .addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView,
//                                                 int newState) {
//
//                    super.onScrollStateChanged(recyclerView, newState);
//
//                    // If scroll state is touch scroll then set userScrolled
//                    // true
//                    if (newState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                        userScrolled = true;
//
//                    }
//
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx,
//                                       int dy) {
//
//                    super.onScrolled(recyclerView, dx, dy);
//                    // Here get the child count, item count and visibleitems
//                    // from layout manager
//
//                    visibleItemCount = mLayoutManager.getChildCount();
//                    totalItemCount = mLayoutManager.getItemCount();
//                    pastVisiblesItems = mLayoutManager
//                            .findFirstVisibleItemPosition();
//
//                    // Now check if userScrolled is true and also check if
//                    // the item is end then update recycler view and set
//                    // userScrolled to false
//                    if (userScrolled
//                            && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
//                        userScrolled = false;
//
//                        updateRecyclerView();
//                    }
//
//                }
//
//            });
//
//}
//






//
//    private boolean loadMore = true;
//    private int pastVisiblesItems, visibleItemCount, totalItemCount;
//     listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(AbsListView absListView, int i) {
//        }
//
//        @Override
//        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//            visibleItemCount = listView.getChildCount();
//            totalItemCount = listView.getCheckedItemCount();
//            pastVisiblesItems = listView.getFirstVisiblePosition();
//            if (loadMore) {
//                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
//                    soundListSearch.add(null);
//                    new LoadDataTask().execute();
//                }
//            }
//        }
//    });
//    And finally loadMore=false
//
//
//

}
