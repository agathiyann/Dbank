package com.example.dbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class New_Mainpage extends AppCompatActivity {

    CardView java_selectbank,java_expensetrack,java_upprofile;

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(New_Mainpage.this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        builder.show();

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mainpage);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        java_selectbank=findViewById(R.id.xml_selectbank);
        java_expensetrack=findViewById(R.id.xml_expensetracker);
        java_upprofile=findViewById(R.id.xml_upprofile);

        java_selectbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(New_Mainpage.this,MAinpage.class));

            }
        });

        java_upprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(New_Mainpage.this,Changepass.class));

            }
        });

        java_expensetrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(New_Mainpage.this,Expenses.class));

            }
        });


    }

}