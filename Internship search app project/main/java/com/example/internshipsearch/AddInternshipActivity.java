package com.example.jobsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobsearch.Model.Data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;



public class AddInternshipActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText internshipTitle;
    private EditText internshipDesc;
    private EditText internshipSkills;
    private EditText internshipCompany;
    private EditText internshipDuration;

    private Button btnPost;

    //Firebase

    private FirebaseAuth mAuth;
    private DatabaseReference mInternshipPost;

    private DatabaseReference mPublicDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_internship);

        toolbar=findViewById(R.id.toolbar_post_internship);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Post Internship");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser= mAuth.getCurrentUser();
        String uId= Objects.requireNonNull(mUser).getUid();

        mInternshipPost= FirebaseDatabase.getInstance("https://jobsearch-9ee17-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Internship Post").child(uId);

        mPublicDatabase=FirebaseDatabase.getInstance("https://jobsearch-9ee17-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Public database");

        AddInternship();

    }

    private void AddInternship() {
        internshipTitle=findViewById(R.id.internship_title);
        internshipDesc=findViewById(R.id.internship_desc);
        internshipSkills=findViewById(R.id.internship_skills);
        internshipCompany=findViewById(R.id.internship_company);
        internshipDuration=findViewById(R.id.internship_duration);

        btnPost=findViewById(R.id.btn_internship_post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=internshipTitle.getText().toString().trim();
                String description=internshipDesc.getText().toString().trim();
                String skills=internshipSkills.getText().toString().trim();
                String company=internshipCompany.getText().toString().trim();
                String duration=internshipDuration.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    internshipTitle.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    internshipDesc.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(skills)) {
                    internshipSkills.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(company)) {
                    internshipCompany.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(duration)) {
                    internshipDuration.setError("Required Field...");
                    return;
                }

                String id=mInternshipPost.push().getKey();

                String date=DateFormat.getDateInstance().format(new Date());

                Data data=new Data(title, description, skills, company, duration, id, date);
                
                mInternshipPost.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),PostInternshipActivity.class));


            }
        });
    }


}