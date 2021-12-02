package com.example.vente.entities;

import androidx.room.ColumnInfo;

public class Product {

    @ColumnInfo(autogenerate = true)
    public int id;
    @ColumnInfo(name = "serverId")
    public int serverId;
    @ColumnInfo(name = "desc")
    public String desc;
    @ColumnInfo(name = "des")
    public String des;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quan")
    public double quan;
    @ColumnInfo(name = "aler")
    public double aler;

    public static final String TABLE_NAME = "products";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, " +
            "name VARCHAR(100), description TEXT, price REAL default 0, quantityInStock INTEGER default 0," +
            "alertQuantity INTEGER default 0 )";

    public Product() {
    }

    public Product(String des, String desc,  double price, double quan, double aler) {
        this.des = des;
        this.desc = desc;
        this.price = price;
        this.quan = quan;
        this.aler = aler;

    }

    @Override
    public String toString() {
        return "Product{" +
                ", des='" + des + '\'' +
                "desc='" + desc + '\'' +
                ", price=" + price +
                ", quan=" + quan +
                ", aler=" + aler +
                '}';
    }
}
