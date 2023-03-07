package com.example.dbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MAinpage extends AppCompatActivity {
    ImageView sbim,icicim,canm,tmbm,axism,hdfcm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sbim=findViewById(R.id.sbi);
        icicim=findViewById(R.id.icici);
        canm=findViewById(R.id.canera);
        tmbm=findViewById(R.id.tmb);
        axism=findViewById(R.id.axies);
        hdfcm=findViewById(R.id.hdfc);


        sbim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.onlinesbi.sbi/")));
            }
        });

        icicim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.icicibank.com/")));
            }
        });
        hdfcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hdfcbank.com/")));
            }
        });
        canm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://canarabank.com/")));
            }
        });
        tmbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tmbnet.in/")));
            }
        });
        axism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.axisbank.com/")));
            }
        });




       /* Toolbar toolbar=findViewById(R.id.add);
        Toolbar toolbar1=findViewById(R.id.list);


        toolbar.inflateMenu(R.menu.addincome);
        toolbar1.inflateMenu(R.menu.monthexpence);*/

/*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
if (item.getItemId()==R.id.addexpense){

    startActivity(new Intent(MAinpage.this,Incomeview.class));
}
else  if (item.getItemId()==R.id.inc){
    startActivity(new Intent(MAinpage.this,Addincome.class));
}
else if (item.getItemId()==R.id.pro){
    startActivity(new Intent(MAinpage.this,Changepass.class));
}


        return true;
    }
});

toolbar1.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {

 if(item.getItemId()==R.id.monthexpense){
            startActivity(new Intent(MAinpage.this,Viewdayex.class));
        }
 else if(item.getItemId()==R.id.viewcha){
     startActivity(new Intent(MAinpage.this,Viewday.class));
 }


        return true;
    }
});*/


    }


}