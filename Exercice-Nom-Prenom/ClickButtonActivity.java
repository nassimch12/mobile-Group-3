package com.example.exercice;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class ClickButtonActivity extends Activity {
    Button myButton;
    TextView Bienvenue;
    EditText nom;
    EditText prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = (EditText)findViewById(R.id.nomInput);
        prenom = (EditText) findViewById(R.id.prenomInput);
        myButton = (Button) findViewById(R.id.myButton);
        Bienvenue = (TextView) findViewById(R.id.BienvenueText);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inNom = nom.getText().toString();
                String inPrenom = prenom.getText().toString();

                Bienvenue.setText("Bienvenue " + inPrenom + " " + inNom);
            }
        });


    }
}
