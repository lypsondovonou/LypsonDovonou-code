package com.example.vente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vente.DAO.ProductDao;
import com.example.vente.WebServices.ProductWebService;
import com.example.vente.entities.Product;
import com.google.android.material.textfield.TextInputEditText;


public class UpDate extends AppCompatActivity {

    private TextInputEditText designation;
    private TextInputEditText description;
    private TextInputEditText prix;
    private TextInputEditText quantite;
    private TextInputEditText alerte;
    private ProductDao prodao ;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);
        prodao = new ProductDao(this);

        designation = findViewById(R.id.dese);
        description = findViewById(R.id.desce);
        prix = findViewById(R.id.prixe);
        quantite = findViewById(R.id.quantitee);
        alerte = findViewById(R.id.alertee);
        register = findViewById(R.id.enregistrer);

        String nameee = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("myde");
        String namee = getIntent().getStringExtra("mydee");
        String nam = getIntent().getStringExtra("mype");
        String na = getIntent().getStringExtra("myqe");
        String nan = getIntent().getStringExtra("myae");

        designation.setText(name);
        description.setText(namee);
        prix.setText(nam);
        quantite.setText(na);
        alerte.setText(nan);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                Product save = productWebService.updateProduct(produit);
                                System.out.println(save);
                                System.out.println("save :: " + save);
                                runOnUiThread(()->{
                                    if (save != null){
                                        prodao.update(produit.id, produit);
                                    }
                                });
                            }
                    ).start();


                    prodao.update(Integer.parseInt(nameee),produit);


                    Intent intent = new Intent(UpDate.this,ProductList.class);
                    startActivity(intent);


                }
            }
        });
    }



}