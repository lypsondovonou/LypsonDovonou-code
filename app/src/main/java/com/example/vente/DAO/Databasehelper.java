package com.example.vente.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.vente.entities.Product;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "project27.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = "DataBaseHelper";

    private static Databasehelper databaseHelper;

    private Databasehelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    public static Databasehelper getInstance(Context context) {
        if(databaseHelper==null){
            databaseHelper = new Databasehelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: avant la creation");
        sqLiteDatabase.execSQL(Product.CREATE_TABLE);
        Log.d(TAG, "onCreate: apres la creation");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
