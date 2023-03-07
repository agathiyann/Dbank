package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Viewday extends AppCompatActivity {
    PieChart  pieChart;
    DatabaseReference databaseReference,databaseReference1;
    ArrayList<Integer> arrayList;
    float sum;
TextView balem,examt,inc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewday);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        arrayList=new ArrayList<>();
       pieChart=findViewById(R.id.pie);
       balem=findViewById(R.id.balam);
       examt=findViewById(R.id.exam);
       inc=findViewById(R.id.incm);



        String DayYear = new SimpleDateFormat(" MMM_yyyy").format(Calendar.getInstance().getTime());
        databaseReference1=FirebaseDatabase.getInstance().getReference(DayYear);
        databaseReference=FirebaseDatabase.getInstance().getReference("Income").child(DayYear);
databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.hasChildren()){

            String geta=snapshot.child("Income").getValue().toString();

            float get=Float.parseFloat(geta);

            databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.hasChildren()){

                        for (DataSnapshot snapshot1:snapshot.getChildren()){

                        String amou=snapshot1.child("Amount").getValue().toString();

                           // Toast.makeText(Viewday.this, amou, Toast.LENGTH_SHORT).show();

                      arrayList.add(Integer.parseInt(amou));

                        }

                         sum=0;

                        for(int i=0;i<arrayList.size();i++){

                            sum=sum+(arrayList.get(i));
                        }
                         float ba=(((get-sum)/get)*100);


                        float ex=((sum/get)*100);

                        float bal=get-sum;


                       // Toast.makeText(Viewday.this, String.valueOf(ex), Toast.LENGTH_SHORT).show();

                        pieChart.addPieSlice(new PieModel(ba, Color.parseColor("green")));

                        pieChart.addPieSlice(new PieModel(ex,Color.parseColor("#EF5350")));
                        pieChart.startAnimation();

                        DecimalFormat decimalFormat=new DecimalFormat("#.#");
                        String bale=String.valueOf(decimalFormat.format(ba));
                          String exe=String.valueOf(decimalFormat.format(ex));

                          DecimalFormat decimalFormat1=new DecimalFormat("#");
                          String st=String.valueOf(decimalFormat1.format(bal));
                          String st1=String.valueOf(decimalFormat1.format(sum));
                        balem.setText(st+""+"("+bale+"%"+")");
                                examt.setText(st1+""+"("+exe+"%"+")");
                        inc.setText(geta);

                    }else {

                        Toast.makeText(Viewday.this, "No Expense", Toast.LENGTH_SHORT).show();

                    }





                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }else {
            Toast.makeText(Viewday.this, "No Income ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

    }
}