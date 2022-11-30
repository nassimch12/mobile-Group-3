package com.example.jobsearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobsearch.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PostInternshipActivity extends AppCompatActivity {

    private FloatingActionButton tabBtn;
    private Toolbar toolbar;

    private RecyclerView recyclerView;

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_internship);

        toolbar = findViewById(R.id.toolbar_post_internship);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Posted Internships");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabBtn=findViewById(R.id.tab_add);

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser mUser= mAuth.getCurrentUser();
        String uId=mUser.getUid();

        mDatabase= FirebaseDatabase.getInstance("https://jobsearch-9ee17-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Internship Post").child(uId);

        recyclerView=findViewById(R.id.recycler_internship_post_id);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);




        tabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),AddInternshipActivity.class));

            }
        });



    }



    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mDatabase,Data.class)
                        .build();


        FirebaseRecyclerAdapter<Data, MyViewHolder>adapter=new FirebaseRecyclerAdapter<Data, MyViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {

                holder.setInternshipTitle(model.getTitle());
                holder.setInternshipDate(model.getDate());
                holder.setInternshipDesc(model.getDescription());
                holder.setInternshipSkills(model.getSkills());
                holder.setInternshipCompany(model.getCompany());
                holder.setInternshipDuration(model.getDuration());

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.internship_post_item,parent,false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myView;


        public MyViewHolder(View itemView){
            super(itemView);
            myView=itemView;
        }

        public void setInternshipTitle(String title){
            TextView myTitle=myView.findViewById(R.id.internship_title1);
            myTitle.setText(title);

        }

        public void setInternshipDate(String date){
            TextView myDate=myView.findViewById(R.id.internship_date1);
            myDate.setText(date);
        }

        public void setInternshipDesc(String desc){
            TextView myDesc=myView.findViewById(R.id.internship_desc1);
            myDesc.setText(desc);
        }

        public void setInternshipSkills(String skills){
            TextView mySkills=myView.findViewById(R.id.internship_skills1);
            mySkills.setText(skills);
        }

        public void setInternshipCompany(String company){
            TextView myCompany=myView.findViewById(R.id.internship_company1);
            myCompany.setText(company);
        }

        public void setInternshipDuration(String duration){
            TextView myDuration=myView.findViewById(R.id.internship_duration1);
            myDuration.setText(duration);
        }

    }
}