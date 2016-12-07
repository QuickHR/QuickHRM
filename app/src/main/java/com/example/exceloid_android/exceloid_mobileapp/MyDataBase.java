package com.example.exceloid_android.exceloid_mobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by Exceloid-Android on 30-09-2016.
 */

public class MyDataBase{

    public static final String IMAGE_ID = "id";
    public static final String IMAGE = "image";
    private final Context mContext;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "Images.db";
    private static final int DATABASE_VERSION = 1;

    private static final String IMAGES_TABLE = "ImagesTable";

    private static final String CREATE_IMAGES_TABLE =
            "CREATE TABLE " + IMAGES_TABLE + " (" +
                    IMAGE_ID + " INTEGER PRIMARY KEY, "
                    + IMAGE + " BLOB NOT NULL);";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_IMAGES_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_IMAGES_TABLE);
            onCreate(db);
        }
    }

    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }

    public MyDataBase(Context ctx) {
        mContext = ctx;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public MyDataBase open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    // Insert the image to the Sqlite DB
    public void insertImage(byte[] imageBytes) {
        ContentValues cv = new ContentValues();
        cv.put(IMAGE, imageBytes);
        mDb.insert(IMAGES_TABLE, null, cv);
    }

    public void updateImage(int id,byte[] imageBytes){
        ContentValues cv=new ContentValues();
        cv.put(IMAGE, imageBytes);
        mDb.update(IMAGES_TABLE,cv,IMAGE_ID+"="+id,null);
       // mDb.update(IMAGES_TABLE,cv,"where id=1",null);
    }
    // Get the image from SQLite DB
    // We will just get the last image we just saved for convenience...
    public byte[] retreiveImageFromDB() {
        Cursor cur = mDb.query(true, IMAGES_TABLE, new String[]{IMAGE,},
                null, null, null, null,
                IMAGE_ID + " DESC", "1");
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex(IMAGE));
            cur.close();
            return blob;
        }
        cur.close();
        return null;
    }

    public void delete_byID(int id){
        mDb.delete(IMAGES_TABLE, IMAGE_ID+"="+id, null);
    }

    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + IMAGES_TABLE;
        Cursor cursor = mDb.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        mDb.close();
        cursor.close();
        return rowCount;
    }

    public void deleteTables(){
        mDb.delete(IMAGES_TABLE, null, null);
    }
}