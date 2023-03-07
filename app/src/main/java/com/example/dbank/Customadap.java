package com.example.dbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customadap extends BaseAdapter {



    ArrayList<String> exty,extimear,exdate;
    ArrayList<Integer> exa;
    Context context;
    LayoutInflater inflater;

    public Customadap(ArrayList<Integer> exa, ArrayList<String> exty, ArrayList<String> extimear,ArrayList<String> exdate, Context context) {
        this.exa = exa;
        this.exty = exty;
        this.extimear = extimear;
        this.context = context;
this.exdate=exdate;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return exa.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    view=inflater.inflate(R.layout.layout,null);

    TextView textView=(TextView) view.findViewById(R.id.exty);
    TextView textView1=(TextView) view.findViewById(R.id.exam);
    TextView textView2=(TextView) view.findViewById(R.id.exti);
   TextView textView3=(TextView)view.findViewById(R.id.ex);

textView3.setText(exdate.get(i));
        textView1.setText(String.valueOf(exa.get(i)));
        textView2.setText(extimear.get(i));
        textView.setText(exty.get(i));




        return view;
    }
}
