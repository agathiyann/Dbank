package com.example.dbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewExpenseandIncomepage extends AppCompatActivity {

    CardView java_monthexpense,java_monthchart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenseand_incomepage);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        java_monthexpense=findViewById(R.id.xml_monthexpense);
        java_monthchart=findViewById(R.id.xml_chartview);

        java_monthexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewExpenseandIncomepage.this,Viewdayex.class));

            }
        });

        java_monthchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewExpenseandIncomepage.this,Viewday.class));

            }
        });

    }
}