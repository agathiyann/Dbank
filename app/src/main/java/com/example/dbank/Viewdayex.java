package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Viewdayex extends AppCompatActivity {
    ArrayList<String> exty,extimear,exdate;
    ArrayList<Integer> exa;
TextView textView,bal,txin;
    int sum;
            ListView listView1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdayex);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        listView1=findViewById(R.id.listv1);
        exa=new ArrayList<>();
        exty=new ArrayList<>();
        extimear=new ArrayList<>();
        exdate=new ArrayList<String>();
        textView=findViewById(R.id.excome);
        bal=findViewById(R.id.balcome);
        txin=findViewById(R.id.income);

        String Day=new SimpleDateFormat("EEE,d (MMM)").format(Calendar.getInstance().getTime());
        String DayYear = new SimpleDateFormat(" MMM_yyyy").format(Calendar.getInstance().getTime());
        databaseReference= FirebaseDatabase.getInstance().getReference(DayYear);
        databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        if (snapshot.exists()) {
            for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                //Toast.makeText(Viewdayex.this, "koil", Toast.LENGTH_SHORT).show();
                String datechild = snapshot1.child("Date").getValue().toString();
                String getam = snapshot1.child("Amount").getValue().toString();
                String getty = snapshot1.child("Ex Type").getValue().toString();
                String gettime=snapshot1.child("Time").getValue().toString();
int get=Integer.parseInt(getam);


                exa.add(get);
                exty.add(getty);
                extimear.add(gettime);
                exdate.add(datechild);

          }

            sum=0;
            for (int le=0;le<exa.size();le++){

                sum =sum+exa.get(le);


            }
            textView.setText(String.valueOf(sum));


            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("Income").child(DayYear);

            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChildren()){
                        String income=snapshot.child("Income").getValue().toString();

                        txin.setText(income);


                        int balance=Integer.parseInt(income)-sum;

                        bal.setText(String.valueOf(balance));


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



         //  Toast.makeText(Viewdayex.this, String.valueOf(exdate.get(2)), Toast.LENGTH_SHORT).show();

            Customadap customadap=new Customadap(exa,exty,extimear,exdate,Viewdayex.this);
            listView1.setAdapter(customadap);


        }else {
            Toast.makeText(Viewdayex.this, "NO Expence", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});



    }
}