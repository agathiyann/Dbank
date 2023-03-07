package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Addincome extends AppCompatActivity {
    Button imcombt;
    EditText editText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_addincome);
        imcombt=findViewById(R.id.incomupbt);
        editText=findViewById(R.id.addinc);


        String DayYear = new SimpleDateFormat(" MMM_yyyy").format(Calendar.getInstance().getTime());
        databaseReference= FirebaseDatabase.getInstance().getReference("Income").child(DayYear);

        imcombt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Get=editText.getText().toString();
                if (Get.isEmpty()){
                    editText.setError("Please FIll This");
                }else {

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            HashMap hashMap=new HashMap();
                            hashMap.put("Income",Get);
                            databaseReference.updateChildren(hashMap);

                            Toast.makeText(Addincome.this, "Added", Toast.LENGTH_SHORT).show();
                             finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }


            }
        });


    }
}