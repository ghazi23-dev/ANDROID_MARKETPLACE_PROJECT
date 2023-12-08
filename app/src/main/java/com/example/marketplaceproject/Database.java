package com.example.marketplaceproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;

    //DB Information
    private static final String DATABASE_NAME = "MARKETPLACE.DB";
    private static final int DATABASE_VERSION = 1;

    //TABLE INFORMATION
    private static final String TABLE_NAME = "MarketplaceList";

    //COLUMNS INFORMATION
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "product_title";
    private static final String COLUMN_DESCRIPTION = "product_description";
    private static final String COLUMN_SELLERINFO = "product_sellerinfo";
    private static final String COLUMN_PRICE = "product_price";




    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    //DB no exist
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_PRICE + " TEXT," +
               COLUMN_SELLERINFO + " TEXT" +
                ");";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addproduct(String title ,String description , String price   ,String sellerinfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PRICE , price);
        cv.put(COLUMN_SELLERINFO,sellerinfo);


        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();}
    }

    // Recycle
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){
          cursor=  db.rawQuery(query, null);

        }
        return cursor;
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}