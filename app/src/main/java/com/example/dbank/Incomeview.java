package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Incomeview extends AppCompatActivity {

    EditText incomety,incomeam;
    Button btin;
  EditText txad;
  DatabaseReference databaseReference;
  long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomeview);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        incomety=findViewById(R.id.extype);
        incomeam=findViewById(R.id.extamou);
        btin=findViewById(R.id.incomup);




        String DayYear = new SimpleDateFormat(" MMM_yyyy").format(Calendar.getInstance().getTime());
        String Day=new SimpleDateFormat("EEE,d (MMM)").format(Calendar.getInstance().getTime());


    databaseReference= FirebaseDatabase.getInstance().getReference(DayYear);
        //databaseReference1= FirebaseDatabase.getInstance().getReference();

btin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String typein=incomety.getText().toString();
        String incom=incomeam.getText().toString();



        if (typein.isEmpty()){
            incomety.setError("Please Fill Type Of Expence");
        }
        else if(incom.isEmpty()){
            incomeam.setError("Please Fill The Amount");

        }

        else{





            String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    size= (int) dataSnapshot.getChildrenCount();

                    //ed_regno.setText("22MCA0"+String.valueOf(size+1));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap hashMap=new HashMap();
                    hashMap.put("Ex Type",typein);
                    hashMap.put("Amount",incom);
                    hashMap.put("Time",currentTime);
                    hashMap.put("Date",Day);
                    databaseReference.child(String.valueOf(size)).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()){
                                finish();
                                Toast.makeText(Incomeview.this, "Added", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Incomeview.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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