package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LinearLayout myprofile,help,requests,status_reports,dictionary,settings;
    ImageView server_image;
   // public static final String URL ="http://theopentutorials.com/totwp331/wp-content/uploads/totlogo.png";
   // public static final String URL ="http://api.androidhive.info/images/sample.jpg";
   // public static final String URL="http://www.androidbegin.com/wp-content/uploads/2013/07/HD-Logo.gif";
    public static final String URL = "http://inducesmile.com/wp-content/uploads/2015/03/mobile.jpg";
    private static final int SELECT_PICTURE = 200;
    UserFunctions userFunctions;
    MyDataBase myDataBase;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    byte[] image_Bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myprofile=(LinearLayout) findViewById(R.id.myprofile_layout);
        dictionary=(LinearLayout) findViewById(R.id.dictionary_layout);
        requests=(LinearLayout) findViewById(R.id.request_layout);
        status_reports=(LinearLayout) findViewById(R.id.status_reports_layout);
        help=(LinearLayout) findViewById(R.id.help_layout);
        settings=(LinearLayout) findViewById(R.id.settings_layout);
        server_image=(ImageView)findViewById(R.id.sever_image_view);
        userFunctions=new UserFunctions();
        myDataBase=new MyDataBase(this);
        loadImage_FromDB();

        server_image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                final CharSequence[] items_one = {"Choose from Gallery", "Take Photo"};
                final CharSequence[] items_two = {"Choose from Gallery", "Take Photo","Remove Photo","See Full Image"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Option!");

                myDataBase.open();
                int cursor = myDataBase.getRowCount();
                if (cursor > 0) {
                    builder.setItems(items_two, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (items_two[item].equals("Choose from Gallery")) {
                                android_Gallery();
                            } else if (items_two[item].equals("Take Photo")) {
                                if (!isDeviceSupportCamera()) {
                                    Toast.makeText(getApplicationContext(),"Sorry! Your device doesn't support camera",Toast.LENGTH_SHORT).show();
                                }else{
                                    android_Camera();
                                    }
                            } else if (items_two[item].equals("Remove Photo")) {
                                remove_Photo();
                            }  else if (items_two[item].equals("See Full Image")) {
                                if(image_Bytes.length!=0&&image_Bytes!=null) {
                                    Intent intent = new Intent(MainActivity.this, Sample_Activity.class);
                                    intent.putExtra("picture", image_Bytes);
                                    startActivity(intent);
                                }else{
                                }
                            }    }
                    });
                }else {
                    builder.setItems(items_one, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (items_one[item].equals("Choose from Gallery")) {
                                android_Gallery();
                            } else if (items_one[item].equals("Take Photo")) {
                                if (!isDeviceSupportCamera()) {
                                    Toast.makeText(getApplicationContext(),"Sorry! Your device doesn't support camera",Toast.LENGTH_SHORT).show();
                                }else{
                                    android_Camera();
                                    }          }             }
                    });
                }
                builder.show();
            }});

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent in=new Intent(MainActivity.this,Navigation_Activity.class);
                b.putString("name","zero");
                in.putExtras(b);
                startActivity(in);
            }
        });

        dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent in=new Intent(MainActivity.this,Navigation_Activity.class);
                b.putString("name","one");
                in.putExtras(b);
                startActivity(in);
            }
        });

        status_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent in=new Intent(MainActivity.this,Navigation_Activity.class);
                b.putString("name","two");
                in.putExtras(b);
                startActivity(in);
            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent in=new Intent(MainActivity.this,Navigation_Activity.class);
                b.putString("name","three");
                in.putExtras(b);
                startActivity(in);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                Intent in=new Intent(MainActivity.this,Navigation_Activity.class);
                b.putString("name","four");
                in.putExtras(b);
                startActivity(in);
            }
        });

		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent se=new Intent(MainActivity.this,Settings_Activity.class);
				startActivity(se);
			}
		});
    }
    public void loadImage_FromDB(){
        try {
            server_image.setImageDrawable(null);
            myDataBase.open();
            int cursor = myDataBase.getRowCount();
            if (cursor > 0) {
                try{
                    myDataBase.open();
                    image_Bytes = myDataBase.retreiveImageFromDB();
                    myDataBase.close();
                    Bitmap map=UserFunctions.getImage(image_Bytes);
                   // map=Bitmap.createScaledBitmap(map, width,height, true);
                  //  Toast.makeText(MainActivity.this, ""+map, Toast.LENGTH_SHORT).show();
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map,(int) (map.getWidth() * 0.5), (int) (map.getHeight() * 0.5), false);
                    server_image.setImageBitmap(bitmapResized);
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                //  imageView.setImageDrawable(null);
                //   imageView.setBackgroundDrawable( getResources().getDrawable(R.drawable.ic_account_circle_24dp) );
                //  imageView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_account_child_24dp));
                server_image.setImageResource(R.drawable.ic_account_circle_24dp);
                //  imageView.setBackgroundResource(R.drawable.ic_account_child_24dp);
              //  Toast.makeText(MainActivity.this, "no image", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }myDataBase.close();
    }
    public void remove_Photo(){
        try {
            myDataBase.open();
            int cursor = myDataBase.getRowCount();
            if (cursor > 0) {
                try{
                    myDataBase.open();
                    myDataBase.delete_byID(1);
                    myDataBase.close();
                    loadImage_FromDB();
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "no image", Toast.LENGTH_SHORT).show();
            }}
        catch (Exception e){
            e.printStackTrace();
        }   myDataBase.close();}

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }}

    public void android_Gallery(){

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    public void android_Camera(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                //  Uri selectedImageUri = data.getData();
                previewGalleryImage(data);
            }
            else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE){
                previewCapturedImage();
            }
        } else {
            // failed to capture image
            Toast.makeText(getApplicationContext(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
    } }

    public void previewGalleryImage(Intent data){

        Uri selectedImageUri = data.getData();

        if (null != selectedImageUri) {
            myDataBase.open();
            int c = myDataBase.getRowCount();
            if (c > 0) {
                try {
                    myDataBase.open();
                    InputStream iStream1 = getContentResolver().openInputStream(selectedImageUri);
                    byte[] inputData1 = UserFunctions.getBytes(iStream1);
                    long lengthbmp = inputData1.length/1024;
                    if(lengthbmp<2000){
                        myDataBase.updateImage(1, inputData1);
                    }else{
                        Toast.makeText(getApplicationContext(),"please check your image size is more than 2mb",Toast.LENGTH_SHORT).show();
                    }
                    myDataBase.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    //  myDataBase.close();
                }
            } else {
                try {
                    myDataBase.open();
                    InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
                    byte[] inputData = UserFunctions.getBytes(iStream);
                    long lengthbmp = inputData.length/1024;
                    if(lengthbmp<2000){
                        myDataBase.insertImage(inputData);
                    }else{
                        Toast.makeText(getApplicationContext(),"please check your image size is more than 2mb",Toast.LENGTH_SHORT).show();
                    }
                    myDataBase.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    // myDataBase.close();
                }
            }myDataBase.close();
            loadImage_FromDB();
        }else{
            Toast.makeText(MainActivity.this, "image is not available", Toast.LENGTH_SHORT).show();
        }
    }
    private void previewCapturedImage() {
        try {
            //  BitmapFactory.Options options = new BitmapFactory.Options();
            //   options.inSampleSize = 8;
            if (null != fileUri) {
                myDataBase.open();
                int c1=myDataBase.getRowCount();
                if(c1>0) {
                    try {
                        myDataBase.open();
                        InputStream iStream = getContentResolver().openInputStream(fileUri);
                        byte[] inputData = UserFunctions.getBytes(iStream);
                        long lengthbmp = inputData.length/1024;
                        if(lengthbmp<2000){
                            myDataBase.updateImage(1,inputData);
                        }else{
                            Toast.makeText(getApplicationContext(),"please check your image size is more than 2mb",Toast.LENGTH_SHORT).show();
                        }
                        myDataBase.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        //  myDataBase.close();
                    }
                }else{
                    try {
                        myDataBase.open();
                        InputStream iStream = getContentResolver().openInputStream(fileUri);
                        byte[] inputData = UserFunctions.getBytes(iStream);
                        long lengthbmp = inputData.length/1024;
                        if(lengthbmp<2000){
                            myDataBase.insertImage(inputData);
                        }else{
                            Toast.makeText(getApplicationContext(),"please check your image size is more than 2mb",Toast.LENGTH_SHORT).show();
                        }
                        myDataBase.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                        //  myDataBase.close();
                    }
                }myDataBase.close();
                loadImage_FromDB();
            } } catch (NullPointerException e) {
            e.printStackTrace();
        }    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "+ IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case R.id.ic_logout:
                AlertDialog.Builder adb1 = new AlertDialog.Builder(MainActivity.this);
                adb1.setTitle("SignOut?");
                adb1.setMessage("Are you sure you want to SignOut?");
                adb1.setNegativeButton("Cancel", null);
                adb1.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new MyAsync_Signout().execute();
                    }
                });
                adb1.show();
        }
        return super.onOptionsItemSelected(item);
    }

    class MyAsync_Signout extends AsyncTask<Void, Integer, Void> {

        boolean running;
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Do something...
                Thread.sleep(2000);
                userFunctions.logoutUser(getApplicationContext());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
            progressDialog = ProgressDialog.show(MainActivity.this, null, "Please Wait!");
            progressDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent login=new Intent(MainActivity.this,Login_Activity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            finish();
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        MainActivity.this.finish();
        moveTaskToBack(true);
        super.onBackPressed();
    }
}


/*
 public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    myDataBase.open();
                    int c=myDataBase.getRowCount();
                    if(c>0) {
                        try {
                            myDataBase.open();
                            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
                            byte[] inputData = UserFunctions.getBytes(iStream);
                            myDataBase.updateImage(1,inputData);
                            myDataBase.close();
                        } catch (IOException ioe) {
                          //  Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
                            myDataBase.close();
                        }
                }else{
                        try {
                            myDataBase.open();
                            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
                            byte[] inputData = UserFunctions.getBytes(iStream);
                            myDataBase.insertImage(inputData);
                            myDataBase.close();
                        } catch (IOException ioe) {
                            //  Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
                            myDataBase.close();
                        }
                    }myDataBase.close();

                   loadImage_FromDB();



                  //  tempBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                  //  tempBitmap=Bitmap.createScaledBitmap(tempBitmap, width,height, true);
                  //  server_image.setImageBitmap(tempBitmap);
            }}} }
   b = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img = bos.toByteArray();
           GetXMLTask task = new GetXMLTask();
        // Execute the task
         task.execute(new String[] { URL });
private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {
        map = null;
        for (String url : urls) {
            //  map = downloadImage(url);
        }
        return map;
    }

    // Sets the Bitmap returned by doInBackground
    @Override
    protected void onPostExecute(Bitmap result) {
        // Drawable d=new BitmapDrawable(getResources(),result);
        // server_image.setBackgroundDrawable(d);
        //  result=Bitmap.createScaledBitmap(result, width,height, true);
        server_image.setImageBitmap(result);
    }

    // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
}*/

 /* Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_PICTURE);
               Intent intent = new Intent();
                intent.setType("image*//*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);*/