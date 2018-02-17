package com.example.antonio.multipleitemslist2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antonio.multipleitemslist2.ShowImage.PhotoFullPopupWindow;

import static com.example.antonio.multipleitemslist2.Settings.getPreference;
import static com.example.antonio.multipleitemslist2.Settings.setPreference;

public class ProfileActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    private ImageView cover_photo;
    private Bitmap bitmap;
    private ImageView edt_image;
    private EditText edt_content;
    private TextView tv_get_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void initViews(){

        cover_photo = findViewById(R.id.cover_photo);
        String path = getPreference(this, "imagePath");


     if (path != null || path.length() != 0 || !path.equalsIgnoreCase("")){
         cover_photo.setImageBitmap(getScaledBitmap(path, 80, 800));
        }

        cover_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = ((BitmapDrawable)cover_photo.getDrawable()).getBitmap();
                new PhotoFullPopupWindow(ProfileActivity.this, R.layout.popup_photo_full, v, bitmap,null);


            }


    });


        Button btn_change_image=findViewById(R.id.btn_change_image);
        btn_change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             loadImagefromGallery();
            }
        });




        ///////////////////Edit

        edt_image = findViewById(R.id.img_edit);
        edt_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableDisableEditText();
                edt_content.setText(edt_content.getText().toString());
            }
        });
        edt_content = findViewById(R.id.edt_content);
        edt_content.setEnabled(false);


    }
    //method for enable or disable edittext
    private void enableDisableEditText() {
        if (edt_content.isEnabled()) {
            edt_content.setEnabled(false);
            edt_image.setImageResource(R.drawable.ic_border_color_black_24dp);
            Toast.makeText(this,edt_content.getText().toString(),Toast.LENGTH_LONG).show();

        } else {
            edt_content.setEnabled(true);
            edt_image.setImageResource(R.drawable.ic_image_black_24dp);

        }
    }
    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_FIRST_USER && resultCode == RESULT_OK
                    && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                setPreference(this, picturePath, "imagePath");
                cover_photo.setImageBitmap(getScaledBitmap(picturePath, 800, 800));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = null;
        try {
            sizeOptions = new BitmapFactory.Options();
            sizeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, sizeOptions);

            int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

            sizeOptions.inJustDecodeBounds = false;
            sizeOptions.inSampleSize = inSampleSize;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }
    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        int inSampleSize = 0;
        try {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                // Calculate ratios of height and width to requested height and
                // width
                final int heightRatio = Math.round((float) height
                        / (float) reqHeight);
                final int widthRatio = Math.round((float) width
                        / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will
                // guarantee
                // a final image with both dimensions larger than or equal to
                // the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio
                        : widthRatio;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inSampleSize;
    }


}
