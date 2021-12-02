package com.example.vente;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vente.DAO.ProductDao;
import com.example.vente.WebServices.ProductWebService;
import com.example.vente.addapter.Productaddapter;
import com.example.vente.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {
    private ListView list;
    private ProductDao proddao;
    private Productaddapter productaddapter ;
    List<Product> list1 = new ArrayList<>();
    protected static int MAIN_CALL = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        list = findViewById(R.id.list);
        proddao = new ProductDao(this);
        productaddapter = new Productaddapter(this , list1);
        ProductWebService productWebService = new ProductWebService();


        new Thread(new Runnable() {
            final List<Product> localProducts = new ArrayList<>();

            @Override
            public void run() {

                localProducts.addAll(productWebService.getProducts());
                runOnUiThread(() ->{
                    list1.addAll(localProducts);
                    productaddapter.notifyDataSetChanged();
                });

            }
        }).start();



//        new Thread(new Runnable() {
//            final List<Product> localProducts = new ArrayList<>();
//
//            @Override
//            public void run() {
//
//                localProducts.addAll(proddao.findAll());
//                runOnUiThread(() ->{
//                    list1.addAll(localProducts);
//                    productaddapter.notifyDataSetChanged();
//                        });
//
//            }
//        }).start();

        list.setAdapter(new Productaddapter(this,list1));


 }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        getMenuInflater().inflate(R.menu.meuvlist,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void produitcre (MenuItem menuItem){
        Intent intent = new Intent(ProductList.this,MainActivity.class);
        startActivityIfNeeded(intent,MAIN_CALL);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_CALL && resultCode == RESULT_OK)
        {
            String desig = data.getStringExtra("des");
            String descri = data.getStringExtra("desc");
            String prise = data.getStringExtra("pri");
            String qua = data.getStringExtra("quan");
            String ale = data.getStringExtra("aler");

            Product product = new Product(desig,descri,Double.parseDouble(prise),Double.parseDouble(qua),Double.parseDouble(ale));
            list1.add(product);
            productaddapter.notifyDataSetChanged();

        }
    }
}