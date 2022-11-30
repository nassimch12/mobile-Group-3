package com.example.jobsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passwordReg;
    private Button btnLogin;
    private Button btnReg;

    //Firebase Auth

    private FirebaseAuth mAuth;

    //progress dialog
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();

        mDialog=new ProgressDialog(this);

        Registration();

    }

    private void Registration() {

        emailReg=findViewById(R.id.email_register);
        passwordReg=findViewById(R.id.register_password);

        btnReg=findViewById(R.id.btn_registeration);
        btnLogin=findViewById(R.id.btn_login);//check id name

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailReg.getText().toString().trim();
                String pass=passwordReg.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    emailReg.setError("Required Field!");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    passwordReg.setError("Required Field!");
                    return;
                }

                if(TextUtils.getTrimmedLength(pass) < 6){
                    passwordReg.setError("Password must 6 characters or longer!");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                            mDialog.dismiss();

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }

                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
    }
}