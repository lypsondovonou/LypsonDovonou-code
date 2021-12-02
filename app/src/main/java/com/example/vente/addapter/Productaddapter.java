package com.example.vente.addapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vente.ConfirmDelete;
import com.example.vente.DAO.ProductDao;
import com.example.vente.Information;
import com.example.vente.R;
import com.example.vente.UpDate;
import com.example.vente.entities.Product;

import java.util.List;

public class Productaddapter extends BaseAdapter {
    private TextView name;
    private TextView price;
    private Context context;
    private List<Product> products;
    private LayoutInflater recuperation;
    private ProductDao productDao;


    public Productaddapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.recuperation = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = recuperation.inflate(R.layout.listaddapter,null);
        productDao = new ProductDao(context);

        Product produitcourant = getItem(i);
        String produitnom = produitcourant.desc;
        Double produitprix = produitcourant.price;
        name  = view.findViewById(R.id.name);
        price = view.findViewById(R.id.prix);

        name.setText(produitnom);
        price.setText(produitprix + "FCFA");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cool = new Intent(context, Information.class);
                cool.putExtra("myd",getItem(i).des);
                cool.putExtra("myde",getItem(i).desc);
                cool.putExtra("myp",Double.toString(getItem(i).price));
                cool.putExtra("myq",Double.toString(getItem(i).quan));
                cool.putExtra("mya",Double.toString(getItem(i).aler));
                context.startActivity(cool);


            }

        });

        view.findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cool = new Intent(context, ConfirmDelete.class);
                cool.putExtra("idd", Integer.toString(getItem(i).id));
                cool.putExtra("name",getItem(i).des);
                cool.putExtra("descript",getItem(i).desc);
                cool.putExtra("prix",Double.toString(getItem(i).price));
                cool.putExtra("quanti",Double.toString(getItem(i).quan));
                cool.putExtra("alerte",Double.toString(getItem(i).aler));
                context.startActivity(cool);
            }
        });

        view.findViewById(R.id.mod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cool = new Intent(context, UpDate.class);
                cool.putExtra("id",Integer.toString(getItem(i).id));
                cool.putExtra("myde",getItem(i).des);
                cool.putExtra("mydee",getItem(i).desc);
                cool.putExtra("mype",Double.toString(getItem(i).price));
                cool.putExtra("myqe",Double.toString(getItem(i).quan));
                cool.putExtra("myae",Double.toString(getItem(i).aler));
                context.startActivity(cool);
            }
        });

    return view;
    }
}
