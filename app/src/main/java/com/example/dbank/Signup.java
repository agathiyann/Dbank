package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    TextInputEditText creatus,createpass,createmob;
    Button createbt;
    FirebaseAuth auth;
String email,pass,createmn;
DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        auth=FirebaseAuth.getInstance();

        creatus=findViewById(R.id.createuser);
        createpass=findViewById(R.id.createpas);
        createmob=findViewById(R.id.creanum);

        createbt=findViewById(R.id.logbt);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        createbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 email=creatus.getText().toString();
                 pass=createpass.getText().toString();
                 createmn=createmob.getText().toString();


                if (email.isEmpty()){
                    creatus.setError("Must Fill This");
                }
                else if(pass.isEmpty()){
                    createpass.setError("Must fill This");
                }
                else if(pass.length() <= 8){

                    createpass.setError("Must 8 Charecters");
                }else if (createmn.isEmpty()){
                 createmob.setError("Must Fill This");
                }else  if(createmn.isEmpty()){
                    createmob.setError("Must 10 Charecters");

                }else{


                    auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            boolean isuser=task.getResult().getSignInMethods().isEmpty();


                            if (isuser){
                                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()){
                                        Toast.makeText(Signup.this, "Created", Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(Signup.this,Login.class);
                                        intent.putExtra("usname",email);
                                        startActivity(intent);
                                        String user= auth.getUid().toString();

                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                HashMap hashMap=new HashMap();
                                                hashMap.put("Usermail",email);
                                                hashMap.put("Password",pass);
                                                hashMap.put("Mob_Number",createmn);

                                                databaseReference.child(user).updateChildren(hashMap);


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });



                                    }
                                    else {
                                        Toast.makeText(Signup.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });


                            }
                            else{
                                Toast.makeText(Signup.this, "This Mail IS Already Taken", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });



                }

            }
        });

    }
}