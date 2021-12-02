package com.example.vente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vente.DAO.ProductDao;
import com.example.vente.WebServices.ProductWebService;
import com.example.vente.entities.Product;
import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity {

    private TextInputEditText designation;
    private TextInputEditText description;
    private TextInputEditText prix;
    private TextInputEditText quantite;
    private TextInputEditText alerte;
    private ProductDao prodao ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodao = new ProductDao(this);

        designation = findViewById(R.id.des);
        description = findViewById(R.id.desc);
        prix = findViewById(R.id.prix);
        quantite = findViewById(R.id.quantite);
        alerte = findViewById(R.id.alerte);

    }

    public void Ajouter (View view)
    {
        if (description.getText().toString().isEmpty() || designation.getText().toString().isEmpty() || prix.getText().toString().isEmpty() || quantite.getText().toString().isEmpty() || alerte.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Un champs est vide",Toast.LENGTH_SHORT).show();
        }else
        {
            Product produit = new Product(
                     designation.getText().toString(),
                     description.getText().toString(),
                     Double.parseDouble (prix.getText().toString()),
                     Double.parseDouble(quantite.getText().toString()),
                     Double.parseDouble(alerte.getText().toString())
            );


            new Thread(
                    ()->{
                        ProductWebService productWebService = new ProductWebService();
                        Product save = productWebService.createProduct(produit);
                        System.out.println(save);
                        System.out.println("save :: " + save);
                        runOnUiThread(()->{
                            if (save != null){
                                prodao.insert(produit);
                            }
                        });
                    }
            ).start();

            Intent intent = getIntent();
            intent.putExtra("des",produit.des);
            intent.putExtra("desc",produit.desc);
            intent.putExtra("pri", Double.toString(produit.price));
            intent.putExtra("quan",Double.toString(produit.quan));
            intent.putExtra("aler",Double.toString(produit.aler));
            setResult(RESULT_OK,intent);
            finish();





//            Intent intent = new Intent(MainActivity.this,ProductList.class);
//            startActivity(intent);


        }
    }
}