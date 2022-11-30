package com.example.jobsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private Button btnAllInternships;
    private Button btnPostInternship;

    //Toolbar

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar=findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Internship search app");

        btnAllInternships=findViewById(R.id.btn_allInterships);
        btnPostInternship=findViewById(R.id.btn_postInternship);

        btnPostInternship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), PostInternshipActivity.class));

            }
        });

        btnAllInternships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(), AllInternshipActivity.class));

            }
        });
    }
}