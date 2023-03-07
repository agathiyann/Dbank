package com.example.dbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Dayexpense extends AppCompatActivity {
    ListView  listView;
    TextView textView;
    DatabaseReference databaseReference;
    ArrayList<String> getdate;
    ArrayList<String> getamt;
    ArrayList<String> gettype;
    ArrayList<String> getdtime;

    String firetime,firedate,fireamt,firetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayexpense);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        listView=findViewById(R.id.lstview);
        textView=findViewById(R.id.currentdat);
        
        getdate=new ArrayList<>();
        getamt=new ArrayList<>();
        gettype=new ArrayList<>();
        getdtime=new ArrayList<>();

        String DayYear = new SimpleDateFormat(" MMM_yyyy").format(Calendar.getInstance().getTime());
        String Day=new SimpleDateFormat("EEE,d (MMM)").format(Calendar.getInstance().getTime());
        textView.setText(Day);
        textView.setTextSize(25);
        databaseReference= FirebaseDatabase.getInstance().getReference(DayYear);

databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){
            for(DataSnapshot snapshot1:snapshot.getChildren()){


                 firetime=snapshot1.child("Time").getValue().toString();
                 fireamt=snapshot1.child("Amount").getValue().toString();
                 firetype=snapshot1.child("Ex Type").getValue().toString();
                 firedate=snapshot1.child("Date").getValue().toString();
                getdate.add(firetime);
                getamt.add(fireamt);
                gettype.add(firetype);
                getdtime.add(firedate);
            }
        }
        else {
            Toast.makeText(Dayexpense.this, "No Expense", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Dayexpense.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,getdate);
        listView.setAdapter(arrayAdapter);
    }
    @Override
    public void onCancelled(@NonNull DatabaseError error) {
    }
});
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String get=getdate.get(i);
        String get1=getdate.get(i);
        String get2=getamt.get(i);
        String get3=gettype.get(i);
        String get4=getdtime.get(i);

        Intent intent=new Intent(getApplicationContext(),Viewday.class);
        intent.putExtra("get",get);
        intent.putExtra("firetime",get1);
        intent.putExtra("firedate",get2);
        intent.putExtra("fireamt",get3);
        intent.putExtra("firetype",get4);
        startActivity(intent);
    }
});
    }
}