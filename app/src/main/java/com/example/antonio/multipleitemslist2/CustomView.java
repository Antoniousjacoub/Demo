package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by antonio on 2/9/18.
 */

public  class CustomView {


    public static void openDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Confirmed",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static String[] eatFoodyImages = {
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
" inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",
"www.loopwiki.com/wp-content/uploads/2017/11/Untitled-1.jpg",
"inthecheesefactory.com/uploads/source/glidepicasso/quality2.jpg",


    };



}
