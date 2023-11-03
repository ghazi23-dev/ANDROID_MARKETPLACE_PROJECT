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

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +  // Add space before "TEXT"
                COLUMN_DESCRIPTION + " TEXT);";  // Add space before "TEXT"
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addproduct(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();}
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){
          cursor=  db.rawQuery(query, null);

        }
        return cursor;
    }
}
