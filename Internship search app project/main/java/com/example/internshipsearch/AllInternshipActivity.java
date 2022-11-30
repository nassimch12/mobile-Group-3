package com.example.jobsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobsearch.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllInternshipActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    //firebase

    private DatabaseReference mAllInternships;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_internship);

        toolbar=findViewById(R.id.all_internship_posts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All internships");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Database

        mAllInternships=FirebaseDatabase.getInstance("https://jobsearch-9ee17-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Public database");
        mAllInternships.keepSynced(true);


        recyclerView=findViewById(R.id.recycler_all_internships);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mAllInternships,Data.class)
                        .build();


        FirebaseRecyclerAdapter<Data, AllInternshipViewHolder>adapter=new FirebaseRecyclerAdapter<Data, AllInternshipViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AllInternshipViewHolder holder, int position, @NonNull Data model) {

                holder.setInternshipTitle(model.getTitle());
                holder.setInternshipDate(model.getDate());
                holder.setInternshipDesc(model.getDescription());
                holder.setInternshipSkills(model.getSkills());
                holder.setInternshipCompany(model.getCompany());
                holder.setInternshipDuration(model.getDuration());

                holder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getApplicationContext(), DetailsActivity.class);

                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("date", model.getDate());
                        intent.putExtra("description", model.getDescription());
                        intent.putExtra("skills", model.getSkills());
                        intent.putExtra("company", model.getCompany());
                        intent.putExtra("duration", model.getDuration());

                        startActivity(intent);

                    }
                });


            }

            @NonNull
            @Override
            public AllInternshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allinternshippost,parent,false);
                return new AllInternshipViewHolder(view);

            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    public static class AllInternshipViewHolder extends RecyclerView.ViewHolder{

        View myView;


        public AllInternshipViewHolder(View itemView){
            super(itemView);
            myView=itemView;
        }

        public void setInternshipTitle(String title){
            TextView myTitle=myView.findViewById(R.id.all_internship_posts_title);
            myTitle.setText(title);

        }

        public void setInternshipDate(String date){
            TextView myDate=myView.findViewById(R.id.all_internship_posts_date);
            myDate.setText(date);
        }

        public void setInternshipDesc(String desc){
            TextView myDesc=myView.findViewById(R.id.all_internship_posts_desc);
            myDesc.setText(desc);
        }

        public void setInternshipSkills(String skills){
            TextView mySkills=myView.findViewById(R.id.all_internship_posts_skills);
            mySkills.setText(skills);
        }

        public void setInternshipCompany(String company){
            TextView myCompany=myView.findViewById(R.id.all_internship_posts_company);
            myCompany.setText(company);
        }

        public void setInternshipDuration(String duration){
            TextView myDuration=myView.findViewById(R.id.all_internship_posts_duration);
            myDuration.setText(duration);
        }

    }
}