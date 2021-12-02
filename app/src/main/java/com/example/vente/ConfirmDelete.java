package com.example.vente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vente.DAO.ProductDao;
import com.example.vente.WebServices.ProductWebService;
import com.example.vente.entities.Product;

public class ConfirmDelete extends AppCompatActivity {

    private Button delete ;
    private ProductDao proDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        delete = findViewById(R.id.btn_confirm_delete);

        proDao = new ProductDao(this);

        String id = getIntent().getStringExtra("idd");
        String name = getIntent().getStringExtra("name");
        String desc = getIntent().getStringExtra("descript");
        String pri = getIntent().getStringExtra("prix");
        String quant = getIntent().getStringExtra("quanti");
            String alt = getIntent().getStringExtra("alerte");

            Product product = new Product(name,desc, Double.parseDouble(pri), Double.parseDouble(quant),Double.parseDouble(alt));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(
                        ()->{
                            ProductWebService productWebService = new ProductWebService();
                            Product save = productWebService.deleteProduct(product);
                            System.out.println(save);
                            System.out.println("save :: " + save);
                            runOnUiThread(()->{
                                if (save != null){
                                    proDao.delete(Integer.parseInt(id));
                                }

                            });
                        }
                ).start();
                Intent intent = new Intent(ConfirmDelete.this, ProductList.class);
                startActivity(intent);
            }
        });



    }
}