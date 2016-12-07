package com.example.exceloid_android.exceloid_mobileapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Exceloid-Android on 17-11-2016.
 */

public class Update_ExpensesActivity extends AppCompatActivity {

    Button save,attachment;
    static TextView date_textview;
    ImageView first_Image,second_Image,third_Image,fourth_Image;
    EditText enter_amount,attendees_howmany;
    Spinner items_spinner,rupee_spinner;
    String[] s2=new String[]{"Business Meal","Entertainment Meal","Personal Meal","Family Meal"};
    String[] s3=new String[]{"Rs","$"};
    SQLiteDatabase database;
    private static String rupee_String,attendees_String,item_String,amount_String,date_String,weekDay,datetext_String;
    private static int day,month,year;
    private static final int SELECT_PICTURE = 200;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    UserFunctions userFunctions;
    byte[] inputCamera;byte[] inputGallery;
    byte[] inputFirstImage,inputSecondImage,inputThirdImage,inputFourthImage;
    byte[] blob0,blob1,blob2,blob3;
    String spinner_one,spinner_two,id;
    long firstvalue,secondvalue,thirdvalue,fourthvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexpenses_acitvity);
        setupActionBar();

        save = (Button) findViewById(R.id.save_expenses);
        attachment = (Button) findViewById(R.id.add_attachment);
        date_textview = (TextView) findViewById(R.id.date_spinner);
        items_spinner = (Spinner) findViewById(R.id.items_spinner);
        rupee_spinner = (Spinner) findViewById(R.id.rupee_spinner);
        attendees_howmany = (EditText) findViewById(R.id.attendees_howmany);
        enter_amount = (EditText) findViewById(R.id.enter_amount);
        first_Image = (ImageView) findViewById(R.id.first_Image_view);
        second_Image = (ImageView) findViewById(R.id.second_Image_view);
        third_Image = (ImageView) findViewById(R.id.third_Image_view);
        fourth_Image = (ImageView) findViewById(R.id.fourth_Image_view);
        userFunctions = new UserFunctions();

        database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS expense_Values(Id INTEGER PRIMARY KEY, Date VARCHAR, Items VARCHAR, Amount VARCHAR, Rupee VARCHAR, Attendees VARCHAR, FirstImage BLOB, SecondImage BLOB, ThirdImage BLOB, FourthImage BLOB);");
        database.close();

        Bundle b = getIntent().getExtras();
        id = b.getString("id");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(Update_ExpensesActivity.this, R.layout.spinner, R.id.text_spinner, s2);
        items_spinner.setAdapter(adp1);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(Update_ExpensesActivity.this, R.layout.spinner, R.id.text_spinner, s3);
        rupee_spinner.setAdapter(adp2);

        getting_DatabaseValues();
        forImage_button();

        int spinnerone = adp1.getPosition(spinner_one);
        items_spinner.setSelection(spinnerone);
        int spinnertwo = adp2.getPosition(spinner_two);
        rupee_spinner.setSelection(spinnertwo);

        date_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_String=date_textview.getText().toString();
                if(date_String!=null&&date_String.trim().length()!=0) {
                    String parts1[]=date_String.split(",");
                    String s2=parts1[1];
                    String parts[] = s2.split("-");
                    int i = 1;
                    day = Integer.parseInt(parts[2]);
                    month = Integer.parseInt(parts[1]) - Integer.valueOf(i);
                    year = Integer.parseInt(parts[0]);
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getSupportFragmentManager(), "fromPicker");
                }else{

                }
            }
        });

        items_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                item_String = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        rupee_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rupee_String = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datetext_String=date_textview.getText().toString();
                amount_String=enter_amount.getText().toString();
                attendees_String=attendees_howmany.getText().toString();

                if(first_Image.getBackground()!=null) {
                    Bitmap bitmap1 = ((BitmapDrawable)first_Image.getBackground()).getBitmap();
                    ByteArrayOutputStream bos1=new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 50, bos1);
                    inputFirstImage=bos1.toByteArray();
                    firstvalue = inputFirstImage.length/1024;
                }else{
                    inputFirstImage=null;
                    firstvalue=0;
                }
                if(second_Image.getBackground()!=null){
                    Bitmap bitmap1 = ((BitmapDrawable)second_Image.getBackground()).getBitmap();
                    ByteArrayOutputStream bos1=new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 50, bos1);
                    inputSecondImage=bos1.toByteArray();
                    secondvalue = inputSecondImage.length/1024;
                }else{
                    inputSecondImage=null;
                    secondvalue=0;
                }

                if(third_Image.getBackground()!=null){
                    Bitmap bitmap2 = ((BitmapDrawable)third_Image.getBackground()).getBitmap();
                    ByteArrayOutputStream bos2=new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 50, bos2);
                    inputThirdImage=bos2.toByteArray();
                    thirdvalue = inputThirdImage.length/1024;
                }else{
                    inputThirdImage=null;
                    thirdvalue=0;
                }

                if(fourth_Image.getBackground()!=null){
                    Bitmap bitmap3 = ((BitmapDrawable)fourth_Image.getBackground()).getBitmap();
                    ByteArrayOutputStream bos3=new ByteArrayOutputStream();
                    bitmap3.compress(Bitmap.CompressFormat.JPEG, 50, bos3);
                    inputFourthImage=bos3.toByteArray();
                    fourthvalue = inputFourthImage.length/1024;
                }else{
                    inputFourthImage=null;
                    fourthvalue=0;
                }
                long total=firstvalue+secondvalue+thirdvalue+fourthvalue;

           if(total<2000){
                    Toast.makeText(getApplicationContext(),""+total,Toast.LENGTH_SHORT).show();
                if(enter_amount.getText().toString()!=null&&enter_amount.getText().toString().trim().length()!=0&&attendees_howmany.getText().toString()!=null&&attendees_howmany.getText().toString().trim().length()!=0&&
                        datetext_String.trim().length()!=0&&datetext_String.toString()!=null&&amount_String.trim().length()!=0&&amount_String.toString()!=null&&attendees_String.trim().length()!=0&&attendees_String.toString()!=null){
                    try {
                        database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
                        ContentValues values = new ContentValues();

                        values.put("Date", datetext_String); // date
                        values.put("Items", item_String); // items
                        values.put("Amount", amount_String); // amount
                        values.put("Rupee", rupee_String); // rupee
                        values.put("Attendees", attendees_String); // attendees
                        values.put("FirstImage", inputFirstImage); // first_image
                        values.put("SecondImage", inputSecondImage); // second_image
                        values.put("ThirdImage", inputThirdImage); // third_image
                        values.put("FourthImage", inputFourthImage); // fourth_image
                        // Inserting Row
                        database.update("expense_Values",values,"Id="+id,null);
                        database.close();
                        Intent in=new Intent(Update_ExpensesActivity.this,Expenses_Activity.class);
                        startActivity(in);
                        finish();
                    }catch (Exception e){
                        Toast.makeText(Update_ExpensesActivity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(Update_ExpensesActivity.this,"please check your fields", Toast.LENGTH_SHORT).show();
                }}
           else{
                        Toast.makeText(getApplicationContext(),"please check your images size is more than 2mb < "+total,Toast.LENGTH_SHORT).show();
                    }
            }
        });

        first_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items_first = {"Remove Attachment"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_ExpensesActivity.this);
                //  builder.setTitle("Select Option!");

                builder.setItems(items_first, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items_first[item].equals("Remove Attachment")) {
                            attachment.setVisibility(View.VISIBLE);
                            first_Image.setBackgroundDrawable(null);
                            first_Image.setVisibility(View.GONE);
                            inputFirstImage=null;
                        }             }
                });
                builder.show();

            }
        });

        second_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items_second = {"Remove Attachment"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_ExpensesActivity.this);
                // builder.setTitle("Select Option!");

                builder.setItems(items_second, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items_second[item].equals("Remove Attachment")) {
                            attachment.setVisibility(View.VISIBLE);
                            second_Image.setBackgroundDrawable(null);
                            second_Image.setVisibility(View.GONE);
                            inputSecondImage=null;
                        }             }
                });
                builder.show();

            }
        });

        third_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items_third = {"Remove Attachment"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_ExpensesActivity.this);
                //   builder.setTitle("");

                builder.setItems(items_third, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items_third[item].equals("Remove Attachment")) {
                            attachment.setVisibility(View.VISIBLE);
                            third_Image.setBackgroundDrawable(null);
                            third_Image.setVisibility(View.GONE);
                            inputThirdImage=null;
                        }             }
                });
                builder.show();

            }
        });

        fourth_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items_fourth = {"Remove Attachment"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_ExpensesActivity.this);
                // builder.setTitle("Select Option!");

                builder.setItems(items_fourth, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items_fourth[item].equals("Remove Attachment")) {
                            attachment.setVisibility(View.VISIBLE);
                            fourth_Image.setBackgroundDrawable(null);
                            fourth_Image.setVisibility(View.GONE);
                            inputFourthImage=null;
                        }             }
                });
                builder.show();
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items_one = {"Choose from Gallery", "Take Photo"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_ExpensesActivity.this);
                builder.setTitle("Select Action!");

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
                builder.show();
            }
        });

    }

    public void getting_DatabaseValues() {
        database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
        Cursor cursor=database.rawQuery("SELECT * FROM expense_Values",null);
        try {
            if (cursor.getCount() != 0 && cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String s1 = cursor.getString(0);
                        if (s1.equals(id)) {
                            date_textview.setText(cursor.getString(1));
                            spinner_one=cursor.getString(2);
                            enter_amount.setText(cursor.getString(3));
                            spinner_two=cursor.getString(4);
                            attendees_howmany.setText(cursor.getString(5));
                            blob0=cursor.getBlob(6);
                            blob1=cursor.getBlob(7);
                            blob2=cursor.getBlob(8);
                            blob3=cursor.getBlob(9);
                        } else {
                            //  Toast.makeText(getContext(),"no records",Toast.LENGTH_SHORT).show();
                        }
                    } while (cursor.moveToNext());
                    cursor.close();
                }
            } else {

            }
            if(blob0!=null&&blob0.length!=0) {
               // inputFirstImage=blob0;
                Bitmap map = UserFunctions.getImage(blob0);
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                first_Image.setVisibility(View.VISIBLE);
                Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized);
                first_Image.setBackgroundDrawable(first_drawable);
                //first_Image.setImageBitmap(bitmapResized);
            }else{
                first_Image.setVisibility(View.GONE);
                first_Image.setBackgroundDrawable(null);
            }
            if(blob1!=null&&blob1.length!=0) {
               // inputSecondImage=blob1;
                Bitmap map1 = UserFunctions.getImage(blob1);
                Bitmap bitmapResized1 = Bitmap.createScaledBitmap(map1, (int) (map1.getWidth()), (int) (map1.getHeight()), false);
                second_Image.setVisibility(View.VISIBLE);
                Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized1);
                second_Image.setBackgroundDrawable(first_drawable);
               //second_Image.setImageBitmap(bitmapResized1);
            }else {
                second_Image.setVisibility(View.GONE);
                second_Image.setBackgroundDrawable(null);
            }
            if(blob2!=null&&blob2.length!=0) {
               // inputThirdImage=blob2;
                Bitmap map2 = UserFunctions.getImage(blob2);
                Bitmap bitmapResized2 = Bitmap.createScaledBitmap(map2, (int) (map2.getWidth()), (int) (map2.getHeight()), false);
                third_Image.setVisibility(View.VISIBLE);
                Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized2);
                third_Image.setBackgroundDrawable(first_drawable);
               // third_Image.setImageBitmap(bitmapResized2);
            }else{
                third_Image.setVisibility(View.GONE);
                third_Image.setBackgroundDrawable(null);
            }
            if(blob3!=null&&blob3.length!=0) {
               // inputFourthImage=blob3;
                Bitmap map3 = UserFunctions.getImage(blob3);
                Bitmap bitmapResized3 = Bitmap.createScaledBitmap(map3, (int) (map3.getWidth()), (int) (map3.getHeight()), false);
                fourth_Image.setVisibility(View.VISIBLE);
                Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized3);
                fourth_Image.setBackgroundDrawable(first_drawable);
               // fourth_Image.setImageBitmap(bitmapResized3);
            }else {
                fourth_Image.setVisibility(View.GONE);
                fourth_Image.setBackgroundDrawable(null);
            }

        } catch (Exception e) {
            // Toast.makeText(getContext(),""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
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
            try {
                InputStream iStream1 = getContentResolver().openInputStream(selectedImageUri);
                inputGallery = UserFunctions.getBytes(iStream1);
                long lengthbmp = inputGallery.length/1024;
                if(lengthbmp<4000){
                    loadImage_FromGalllery();
                }else{
                    Toast.makeText(getApplicationContext(),"please check your image size is more than 4mb",Toast.LENGTH_SHORT).show();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    private void previewCapturedImage() {
        try {
            if (null != fileUri) {
                try {
                    InputStream iStream = getContentResolver().openInputStream(fileUri);
                    inputCamera = UserFunctions.getBytes(iStream);
                    long lengthbmp = inputCamera.length/1024;
                    if(lengthbmp<4000){
                        loadImage_FromCamera();
                    }else{
                        Toast.makeText(getApplicationContext(),"please check your image size is more than 4mb",Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } } catch (NullPointerException e) {
            e.printStackTrace();
        }    }
    public void loadImage_FromGalllery(){
        first_Image.setVisibility(View.VISIBLE);
        Drawable drawable_one=first_Image.getBackground();
        if(drawable_one!=null){
            second_Image.setVisibility(View.VISIBLE);
            Drawable drawable_two=second_Image.getBackground();
            if(drawable_two!=null){
                third_Image.setVisibility(View.VISIBLE);
                Drawable drawable_three=third_Image.getBackground();
                if(drawable_three!=null){
                    fourth_Image.setVisibility(View.VISIBLE);
                    attachment.setVisibility(View.GONE);
                    Bitmap map = UserFunctions.getImage(inputGallery);
                    if(map.getWidth()>2100&&map.getHeight()>2100) {
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else if(map.getWidth()>1000&&map.getHeight()>1000){
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else{
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }
                }
                else {
                    Bitmap map = UserFunctions.getImage(inputGallery);
                    if(map.getWidth()>2100&&map.getHeight()>2100) {
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else if(map.getWidth()>1000&&map.getHeight()>1000){
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else{
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }
                }
            }else{
                Bitmap map=UserFunctions.getImage(inputGallery);
                if(map.getWidth()>2100&&map.getHeight()>2100) {
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }else if(map.getWidth()>1000&&map.getHeight()>1000){
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }else{
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }
            }
        }else{
            Bitmap map=UserFunctions.getImage(inputGallery);
            if(map.getWidth()>2100&&map.getHeight()>2100) {
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }else if(map.getWidth()>1000&&map.getHeight()>1000){
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }else{
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }
        }
    }

    public void loadImage_FromCamera(){

        first_Image.setVisibility(View.VISIBLE);
        Drawable drawable_one=first_Image.getBackground();
        if(drawable_one!=null){
            second_Image.setVisibility(View.VISIBLE);
            Drawable drawable_two=second_Image.getBackground();
            if(drawable_two!=null){
                third_Image.setVisibility(View.VISIBLE);
                Drawable drawable_three=third_Image.getBackground();
                if(drawable_three!=null){
                    fourth_Image.setVisibility(View.VISIBLE);
                    attachment.setVisibility(View.GONE);
                    Bitmap map = UserFunctions.getImage(inputCamera);
                    if(map.getWidth()>2100&&map.getHeight()>2100) {
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else if(map.getWidth()>1000&&map.getHeight()>1000){
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else{
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                        fourth_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }
                }
                else {
                    Bitmap map = UserFunctions.getImage(inputCamera);
                    if(map.getWidth()>2100&&map.getHeight()>2100) {
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else if(map.getWidth()>1000&&map.getHeight()>1000){
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }else{
                        Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                        third_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                    }
                }
            }else{
                Bitmap map=UserFunctions.getImage(inputCamera);
                if(map.getWidth()>2100&&map.getHeight()>2100) {
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }else if(map.getWidth()>1000&&map.getHeight()>1000){
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }else{
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                    second_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
                }
            }
        }else{
            Bitmap map=UserFunctions.getImage(inputCamera);
            if(map.getWidth()>2100&&map.getHeight()>2100) {
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()/4), (int) (map.getHeight()/4), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }else if(map.getWidth()>1000&&map.getHeight()>1000){
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()*0.5), (int) (map.getHeight()*0.5), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }else{
                Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                first_Image.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmapResized));
            }
        }
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }}

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            weekDay = dayFormat.format(calendar.getTime());
            // Toast.makeText(getActivity(), ""+weekDay, Toast.LENGTH_SHORT).show();
            int i=month+1;
            date_String=year+"-"+i+"-"+day;
            date_textview.setText(weekDay+","+date_String);
        }
    }
    public void setupActionBar(){

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            Intent in=new Intent(Update_ExpensesActivity.this,Expenses_Activity.class);
            startActivity(in);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void forImage_button(){

        if(blob0!=null&&blob0.length!=0&&blob1!=null&&blob1.length!=0&&blob2!=null&&blob2.length!=0&&blob3!=null&&blob3.length!=0){
            attachment.setVisibility(View.GONE);
        }else{
            attachment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent in1=new Intent(Update_ExpensesActivity.this,Expenses_Activity.class);
        startActivity(in1);
        finish();
        super.onBackPressed();
    }
}
