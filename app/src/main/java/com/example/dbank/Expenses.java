package com.example.dbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Expenses extends AppCompatActivity {

    CardView java_addexpense,java_addincome,java_viewdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        java_addexpense=findViewById(R.id.xml_addexpense);
        java_addincome=findViewById(R.id.xml_addincome);
        java_viewdetails=findViewById(R.id.xml_viewdetails);

        java_addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Expenses.this,Incomeview.class));

            }
        });

        java_addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Expenses.this,Addincome.class));

            }
        });

        java_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Expenses.this,ViewExpenseandIncomepage.class));

            }
        });

    }
}