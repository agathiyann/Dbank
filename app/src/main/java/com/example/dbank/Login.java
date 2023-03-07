package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    TextInputEditText usname,pass;
    Button btnlo;
    FirebaseAuth  auth;
    TextView createtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        btnlo=findViewById(R.id.logbt);
        usname=findViewById(R.id.loguser);
        pass=findViewById(R.id.logpas);
        createtx=findViewById(R.id.create);

        auth=FirebaseAuth.getInstance();

        btnlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userlo=usname.getText().toString();
                String passlo=pass.getText().toString();


                if (userlo.isEmpty()){
                    usname.setError("Must Fill This");
                }
                else if(passlo.isEmpty()){
                    pass.setError("Must fill This");
                }
                else if(passlo.length() <= 8){

                    pass.setError("Must 8 Charecters");
                }else{


                    auth.signInWithEmailAndPassword(userlo,passlo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                startActivity(new Intent(Login.this,New_Mainpage.class));
                      Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });




                }


            }
        });


        createtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });






    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
     if (user !=null){
         startActivity(new Intent(Login.this,New_Mainpage.class));
         finish();
     }
    }



}