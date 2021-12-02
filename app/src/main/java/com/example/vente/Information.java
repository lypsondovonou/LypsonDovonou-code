package com.example.vente;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vente.DAO.ProductDao;
import com.example.vente.entities.Product;

public class Information extends AppCompatActivity {
    private TextView desi;
    private TextView descr;
    private TextView pri;
    private TextView quant;
    private TextView alert;
    private ProductDao productDao;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        productDao = new ProductDao(this);


        desi = findViewById(R.id.nature);
        descr = findViewById(R.id.utilite);
        pri = findViewById(R.id.valeur);
        quant = findViewById(R.id.stock);
        alert = findViewById(R.id.alarme);


       String desig = getIntent().getStringExtra("myd");
       String descri = getIntent().getStringExtra("myde");
        String prise = getIntent().getStringExtra("myp");
        String qua = getIntent().getStringExtra("myq");
        String ale= getIntent().getStringExtra("mya");

        Product product = new Product(desig,descri,Double.parseDouble(prise), Double.parseDouble(qua),Double.parseDouble(ale));

        desi.setText(desig);
        descr.setText(descri);
        pri.setText(prise);
        quant.setText(qua);
        alert.setText(ale);

    }

}