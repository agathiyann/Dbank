package com.example.dbank;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dbank.databinding.ActivityChangepassBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Changepass extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    TextInputEditText mailn,passn,repas;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        String email=user.getEmail().toString();
        submit=findViewById(R.id.newbt);
        mailn=findViewById(R.id.newma);
        passn=findViewById(R.id.newpas);
        repas=findViewById(R.id.repas);
        String userid=auth.getUid().toString();

        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getmail=mailn.getText().toString();
                String getpass=passn.getText().toString();
                String reepass=repas.getText().toString();


                if (getmail.isEmpty()){
                    mailn.setError("Please fill this");

                }
                else if(getpass.isEmpty()){
                    passn.setError("Please fill this");

                }else if (reepass.isEmpty()){

                    repas.setError("Please fill this");

                }
                else if (getpass.length()<8){
                    passn.setError("8 Charecter Must");

                }
                else if (reepass.length()<8){
                    repas.setError("8 Charecter Must");

                }else if(getpass.equals(reepass)){

                    if (getmail.equals(email)){

                        AuthCredential authCredential= EmailAuthProvider.getCredential(email,getpass);
                        user.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){

                                    user.updatePassword(reepass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                                                databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        HashMap hashMap=new HashMap();
                                                        hashMap.put("Usermail",getmail);
                                                        hashMap.put("Password",reepass);
                                                        databaseReference.updateChildren(hashMap);
                                                        finish();


                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                                Toast.makeText(Changepass.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(Changepass.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });



                                }else {
                                    Toast.makeText(Changepass.this, "Please Check The Password", Toast.LENGTH_SHORT).show();

                                }


                            }

                        });




                    }else {
                        Toast.makeText(Changepass.this, "User_Mail Is Not Same", Toast.LENGTH_SHORT).show();

                    }


                }


                else {
                    // Toast.makeText(Changepass.this, getmail, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Changepass.this, "Passwords Not Same", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
}