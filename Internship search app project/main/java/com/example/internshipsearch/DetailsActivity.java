package com.example.jobsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView mTitle;
    private TextView mDate;
    private TextView mDescription;
    private TextView mSkills;
    private TextView mCompany;
    private TextView mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar=findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mTitle=findViewById(R.id.detail_title);
        mDate=findViewById(R.id.detail_date);
        mDescription=findViewById(R.id.detail_desc);
        mSkills=findViewById(R.id.detail_skills);
        mCompany=findViewById(R.id.detail_company);
        mDuration=findViewById(R.id.detail_duration);

        //Receive Data

        Intent intent=getIntent();

        String title= intent.getStringExtra("title");
        String date=intent.getStringExtra("date");
        String description=intent.getStringExtra("description");
        String skills=intent.getStringExtra("skills");
        String company=intent.getStringExtra("company");
        String duration=intent.getStringExtra("duration");


        mTitle.setText(title);
        mDate.setText(date);
        mDescription.setText(description);
        mSkills.setText(skills);
        mCompany.setText(company);
        mDuration.setText(duration);

    }
}