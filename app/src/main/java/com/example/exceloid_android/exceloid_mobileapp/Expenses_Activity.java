package com.example.exceloid_android.exceloid_mobileapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Exceloid-Android on 08-11-2016.
 */

public class Expenses_Activity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase database;
    private ArrayList<String> line_values_Datelist = new ArrayList<String >();
    private ArrayList<String> line_values_Idlist = new ArrayList<String>();
    private ArrayList<String> line_values_Spinnerlist = new ArrayList<String >();
    private ArrayList<String> line_values_Rupeelist = new ArrayList<String>();
    ImageView first_Image,second_Image,third_Image,fourth_Image;
    byte[] blob0,blob1,blob2,blob3;
    Cursor cursor;
    String id_value,update_Value;
    final private static int IMAGE_POPUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_activity);
        setupActionBar();

        listView=(ListView)findViewById(R.id.expandable_listview);

        database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS expense_Values(Id INTEGER PRIMARY KEY, Date VARCHAR, Items VARCHAR, Amount VARCHAR, Rupee VARCHAR, Attendees VARCHAR, FirstImage BLOB, SecondImage BLOB, ThirdImage BLOB, FourthImage BLOB);");
        database.close();

        getting_DatabaseValues();

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"pos..."+line_values_Idlist.get(position),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"position..."+position,Toast.LENGTH_SHORT).show();
                Toast.makeText(Expenses_Activity.this, "vaa.."+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });*/

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

               // position=line_values_Idlist.get(pos);
                update_Value=parent.getItemAtPosition(pos).toString();
                //Toast.makeText(Expenses_Activity.this, ""+update_Value, Toast.LENGTH_SHORT).show();
                registerForContextMenu(listView);
                return false;
            }
        });
    }

    public void getting_DatabaseValues(){
        database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
        cursor=database.rawQuery("SELECT * FROM expense_Values",null);
        line_values_Datelist.clear();line_values_Idlist.clear();
        line_values_Spinnerlist.clear();line_values_Rupeelist.clear();

       // Toast.makeText(Expenses_Activity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        try{
        if(cursor.getCount()!=0&&cursor!=null){

            if(cursor.moveToFirst()){
                do{
                    line_values_Idlist.add(cursor.getString(0));
                   // System.out.println("iddd.."+line_values_Idlist);
                    line_values_Datelist.add(cursor.getString(1));
                    line_values_Spinnerlist.add(cursor.getString(2)+" - "+cursor.getString(5));
                    line_values_Rupeelist.add(cursor.getString(3)+"  "+cursor.getString(4));

                }while (cursor.moveToNext());
                CustomListview customListview=new CustomListview(Expenses_Activity.this,line_values_Idlist,line_values_Datelist,line_values_Spinnerlist,line_values_Rupeelist);
                listView.setAdapter(customListview);
                cursor.close();
            }
        }else{
            CustomListview customListview=new CustomListview(Expenses_Activity.this,line_values_Idlist,line_values_Datelist,line_values_Spinnerlist,line_values_Rupeelist);
            listView.setAdapter(customListview);
        }}catch (Exception e){
            Toast.makeText(Expenses_Activity.this, ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }    }

    private class CustomListview extends BaseAdapter {

        private Context mContext;
        ArrayList<String> listItems1;
        ArrayList<String> listItems2;
        ArrayList<String> listItems3;
        ArrayList<String> listItems4;

        public CustomListview(Context c, ArrayList<String> listItems1, ArrayList<String> listItems2,ArrayList<String> listItems3,ArrayList<String> listItems4) {
            this.mContext = c;
            this.listItems1 = listItems1;
            this.listItems2 = listItems2;
            this.listItems3 = listItems3;
            this.listItems4 = listItems4;
        }

        @Override
        public int getCount() {
            return listItems1.size();
        }

        @Override
        public Object getItem(int position) {
            return listItems1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        public View getView(final int pos, View child, ViewGroup parent) {
           CustomListview.Holder mHolder;
            LayoutInflater layoutInflater;
            if (child == null) {
                layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                child = layoutInflater.inflate(R.layout.expense_listview, null);
                mHolder = new CustomListview.Holder();
                mHolder.tv1 = (TextView) child.findViewById(R.id.id_text_view);
                mHolder.tv2 = (TextView) child.findViewById(R.id.date_textview);
                mHolder.tv3 = (TextView) child.findViewById(R.id.spinner_textview);
                mHolder.tv4 = (TextView) child.findViewById(R.id.rupee_textview);
                mHolder.image=(ImageView)child.findViewById(R.id.image_textview);

                mHolder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        id_value=line_values_Idlist.get(pos).toString();
                        showDialog(IMAGE_POPUP);
                    }
                });
                child.setTag(mHolder);
            } else {
                mHolder = (CustomListview.Holder) child.getTag();
            }
            mHolder.tv1.setText(listItems1.get(pos));
            mHolder.tv2.setText(listItems2.get(pos));
            mHolder.tv3.setText(listItems3.get(pos));
            mHolder.tv4.setText(listItems4.get(pos));
            return child;
        }
        public class Holder {
            TextView tv1;
            TextView tv2;
            TextView tv3;
            TextView tv4;
            ImageView image;
        }            }

    @Override
    protected Dialog onCreateDialog(int id) {

        AlertDialog dialogDetails = null;

        switch (id) {

            case IMAGE_POPUP:

                LayoutInflater inflaterdate = LayoutInflater.from(this);
                View dialogviewdate = inflaterdate.inflate(R.layout.image_list, null);
                AlertDialog.Builder dialogbuilderdate = new AlertDialog.Builder(this);
                dialogbuilderdate.setView(dialogviewdate);
                dialogDetails = dialogbuilderdate.create();
                break;
        }
        return dialogDetails;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case IMAGE_POPUP:
                final AlertDialog image_dialog = (AlertDialog) dialog;
                first_Image=(ImageView)image_dialog.findViewById(R.id.imageview_first);
                second_Image=(ImageView)image_dialog.findViewById(R.id.imageview_second);
                third_Image=(ImageView)image_dialog.findViewById(R.id.imageview_third);
                fourth_Image=(ImageView)image_dialog.findViewById(R.id.imageview_fourth);
                first_Image.setBackgroundDrawable(null);second_Image.setBackgroundDrawable(null);
                third_Image.setBackgroundDrawable(null);fourth_Image.setBackgroundDrawable(null);

                database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
                cursor=database.rawQuery("SELECT * FROM expense_Values",null);
                try{
                    if(cursor!=null && cursor.getCount()!=0){
                        if(cursor.moveToFirst()){
                            do{
                                String image_id=cursor.getString(0);
                                if(image_id.equals(id_value)){
                                    blob0=cursor.getBlob(6);
                                    blob1=cursor.getBlob(7);
                                    blob2=cursor.getBlob(8);
                                    blob3=cursor.getBlob(9);
                                }else{
                                }
                            }while (cursor.moveToNext());
                            cursor.close();
                        } else{
                            first_Image.setBackgroundDrawable(null);
                            second_Image.setBackgroundDrawable(null);
                            third_Image.setBackgroundDrawable(null);
                            fourth_Image.setBackgroundDrawable(null);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(blob0!=null&&blob0.length!=0) {
                    Bitmap map = UserFunctions.getImage(blob0);
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(map, (int) (map.getWidth()), (int) (map.getHeight()), false);
                    Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized);
                    first_Image.setVisibility(View.VISIBLE);
                    first_Image.setImageDrawable(first_drawable);
                }else{
                    first_Image.setVisibility(View.GONE);
                  //  first_Image.setImageDrawable(null);
                }
                if(blob1!=null&&blob1.length!=0) {
                    Bitmap map1 = UserFunctions.getImage(blob1);
                    Bitmap bitmapResized1 = Bitmap.createScaledBitmap(map1, (int) (map1.getWidth()), (int) (map1.getHeight()), false);
                    Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized1);
                    second_Image.setVisibility(View.VISIBLE);
                    second_Image.setImageDrawable(first_drawable);
                }else {
                    second_Image.setVisibility(View.GONE);
                   // second_Image.setImageBitmap(null);
                }
                if(blob2!=null&&blob2.length!=0) {
                    Bitmap map2 = UserFunctions.getImage(blob2);
                    Bitmap bitmapResized2 = Bitmap.createScaledBitmap(map2, (int) (map2.getWidth()), (int) (map2.getHeight()), false);
                    Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized2);
                    third_Image.setVisibility(View.VISIBLE);
                    third_Image.setImageDrawable(first_drawable);
                }else{
                    third_Image.setVisibility(View.GONE);
                   // third_Image.setImageBitmap(null);
                }
                if(blob3!=null&&blob3.length!=0) {
                    Bitmap map3 = UserFunctions.getImage(blob3);
                    Bitmap bitmapResized3 = Bitmap.createScaledBitmap(map3, (int) (map3.getWidth()), (int) (map3.getHeight()), false);
                    Drawable first_drawable = new BitmapDrawable(getResources(), bitmapResized3);
                    fourth_Image.setVisibility(View.VISIBLE);
                    fourth_Image.setImageDrawable(first_drawable);
                }else {
                    fourth_Image.setVisibility(View.GONE);
                  // fourth_Image.setImageBitmap(null);
                }

                first_Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blob0.length!=0&&blob0!=null) {
                            Intent intent = new Intent(Expenses_Activity.this, Sample_Activity.class);
                            intent.putExtra("picture", blob0);
                            startActivity(intent);
                        }else{
                        }
                    }
                });

                second_Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blob1.length!=0&&blob1!=null) {
                            Intent intent = new Intent(Expenses_Activity.this, Sample_Activity.class);
                            intent.putExtra("picture", blob1);
                            startActivity(intent);
                        }else{
                        }
                    }
                });

                third_Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blob2.length!=0&&blob2!=null) {
                            Intent intent = new Intent(Expenses_Activity.this, Sample_Activity.class);
                            intent.putExtra("picture", blob2);
                            startActivity(intent);
                        }else{
                        }
                    }
                });

                fourth_Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blob3.length!=0&&blob3!=null) {
                            Intent intent = new Intent(Expenses_Activity.this, Sample_Activity.class);
                            intent.putExtra("picture", blob3);
                            startActivity(intent);
                        }else{
                        }
                    }
                });
                break;
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Update");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Update"){
            if(update_Value!=null && update_Value.trim().length()!=0){
                Bundle b = new Bundle();
                Intent in=new Intent(Expenses_Activity.this,Update_ExpensesActivity.class);
                b.putString("id",update_Value);
                in.putExtras(b);
                startActivity(in);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Please check your fields",Toast.LENGTH_SHORT).show();
            }
        }
        else if(item.getTitle()=="Delete"){
            AlertDialog.Builder adb = new AlertDialog.Builder(Expenses_Activity.this);
            adb.setTitle("Delete?");
            adb.setMessage("Are you sure you want to delete item");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    database = openOrCreateDatabase("expense_Reports", MODE_PRIVATE, null);
                    cursor=database.rawQuery("SELECT * FROM expense_Values",null);
                    if (cursor != null && cursor.getCount() != 0) {
                        database.execSQL("DELETE FROM expense_Values WHERE Id='"+update_Value+"'");
                        cursor.moveToFirst();
                    }
                    cursor.close();
                    database.close();
                    getting_DatabaseValues();
                }
            });
            adb.show();
        }else{
            return false;
        }
        return true;
    }
}

