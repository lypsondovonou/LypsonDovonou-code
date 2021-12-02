package com.example.vente.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vente.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private Databasehelper dataBaseHelper;

    public ProductDao(Context context){
        dataBaseHelper = Databasehelper.getInstance(context);
    }

    public Product insert(Product produit){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", produit.des);
        contentValues.put("description", produit.desc);
        contentValues.put("price", produit.price);
        contentValues.put("quantityInStock", produit.quan);
        contentValues.put("alertQuantity", produit.aler);
        long id = database.insert(Product.TABLE_NAME, null, contentValues);
        produit.id = (int) id;
        return produit;
    }

    public Product getOne(int id){
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String[] column = new String[]{"id", "name", "description", "price", "quantityInStock", "alertQuantity"};

        String where = "id=?";
        String[] whereArgs = new String[]{id+""};
        @SuppressLint("Recycle")
        Cursor cursor = database.query(Product.TABLE_NAME, column, where,
                whereArgs, "", "", "");
        if(cursor!=null && cursor.moveToNext()){

            Product produit = new Product();
            produit.id = cursor.getInt(Math.max(cursor.getColumnIndex("id"), 0));
            produit.des = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
            produit.desc = cursor.getString(Math.max(cursor.getColumnIndex("description"), 0));
            produit.price = cursor.getDouble(Math.max(cursor.getColumnIndex("price"), 0));
            produit.quan = cursor.getInt(Math.max(cursor.getColumnIndex("quantityInStock"), 0));
            produit.aler = cursor.getInt(Math.max(cursor.getColumnIndex("alertQuantity"), 0));
            cursor.close();
            return produit;
        }
        return null;
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String[] column = new String[]{"id", "name", "description", "price", "quantityInStock", "alertQuantity"};

        @SuppressLint("Recycle")
        Cursor cursor = database.query(Product.TABLE_NAME, column, "",
                null, "", "", "");
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                Product produit = new Product();
                produit.id = cursor.getInt(Math.max(cursor.getColumnIndex("id"), 0));
                produit.des = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
                produit.desc = cursor.getString(Math.max(cursor.getColumnIndex("description"), 0));
                produit.price = cursor.getDouble(Math.max(cursor.getColumnIndex("price"), 0));
                produit.quan = cursor.getInt(Math.max(cursor.getColumnIndex("quantityInStock"), 0));
                produit.aler = cursor.getInt(Math.max(cursor.getColumnIndex("alertQuantity"), 0));

                products.add(produit);
            }
            cursor.close();
        }
        return products;
    }

    public Product update(int id, Product product){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", product.des);
        contentValues.put("description",product.desc);
        contentValues.put("price" ,product.price);
        contentValues.put("quantityInStock",product.quan);
        contentValues.put("alertQuantity",product.aler);
        long ide =  database.update(Product.TABLE_NAME,contentValues,"id=?",new String[] {""+id});

        return product;
    }

    public boolean delete(int id){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        int rowDelete = database.delete(Product.TABLE_NAME, "id=?", new String[]{""+id});
        return rowDelete>0;
    }

}
