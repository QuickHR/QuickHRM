package com.example.exceloid_android.exceloid_mobileapp;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Exceloid-Android on 20-09-2016.
 */
public class Splash_Screen extends AppCompatActivity {

    ImageView imageView;
    Button button;
    TextView tv;
    byte[] inputData;
   // UserFunctions userFunctions;
    int width = 100;
    int height = 100;
    MyDataBase myDataBase;
    Bitmap b;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    private static final int SELECT_PICTURE = 200;
    private static final String TAG = "Splash_Screen";
   // TouchImageView image;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

       // imageView=(ImageView)findViewById(R.id.imageView1);
     //   image=(TouchImageView)findViewById(R.id.touchimageView1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android_Gallery();
            }
        });

    }
    public void android_Gallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
              //  Uri selectedImageUri = data.getData();
                previewGalleryImage(data);
            }
        }
      }

    public void previewGalleryImage(Intent data){

        Uri selectedImageUri = data.getData();
        if (null != selectedImageUri) {
            try {
                InputStream iStream1 = getContentResolver().openInputStream(selectedImageUri);
                inputData = UserFunctions.getBytes(iStream1);
                long lengthbmp = inputData.length/1024;
                if(lengthbmp<4000){
                    loadImage_FromGallery();
                }else{
                    Toast.makeText(getApplicationContext(),"please check your image size is more than 4mb",Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(Splash_Screen.this, "image is not available", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadImage_FromGallery(){
        Bitmap map = UserFunctions.getImage(inputData);
        if(map.getWidth()>2100&&map.getHeight()>2100) {
            Toast.makeText(Splash_Screen.this, "2000 more than", Toast.LENGTH_SHORT).show();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
            imageView.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
        }else if(map.getWidth()>1000&&map.getHeight()>1000){
            Toast.makeText(Splash_Screen.this, "1000 more than", Toast.LENGTH_SHORT).show();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
            imageView.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
        }else{
            Toast.makeText(Splash_Screen.this, "1000 below", Toast.LENGTH_SHORT).show();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
          //  image.setImageBitmap(bitmapResized);
            imageView.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
        }

        forLengthvalue();

        /*TouchImageView img = new TouchImageView(this);
        img.setImageBitmap(bmp);
        img.setMaxZoom(4f);
        setContentView(img);*/
    }

    public void forLengthvalue(){
        long secondvalue;
        if(imageView.getBackground()!=null){
            Bitmap bitmap1 = ((BitmapDrawable)imageView.getBackground()).getBitmap();
            ByteArrayOutputStream bos1=new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 0, bos1);
            byte[] inputSecondImage=bos1.toByteArray();
            secondvalue = inputSecondImage.length/1024;
        }else{
            byte[] inputSecondImage=null;
            secondvalue=0;
        }
        Toast.makeText(Splash_Screen.this, ""+secondvalue, Toast.LENGTH_SHORT).show();
    }

    }
//13 11