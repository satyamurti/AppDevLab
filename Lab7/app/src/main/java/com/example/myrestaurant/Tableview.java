package com.example.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Tableview extends AppCompatActivity {
    TableLayout t1;
    ArrayList<mydata> mydatalist;
    Button bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableview);
        loadData();
         t1 = (TableLayout) findViewById(R.id.mytable);
        setview(t1);
        bck = findViewById(R.id.bck);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Tableview.this,MainActivity.class);
                startActivity(a);
                finish();
            }
        });
    }

    private void setview(TableLayout t1) {
        int n = mydatalist.size();
        for (int i = 0; i < n; i++) {
            mydata temp = mydatalist.get(i);
            int order_no = i+1;
            int table_no = temp.gettno();
            String d1 = temp.getd1();
            String d2 = temp.getd2();
            String d3 = temp.getd3();
            String d4 = temp.getd4();

            TableRow newRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            newRow.setLayoutParams(lp);
            newRow.setPadding(5, 5, 5, 5);


            TextView column1 = new TextView(this);
            TextView column2 = new TextView(this);
            TextView column3 = new TextView(this);
            TextView column4 = new TextView(this);
            TextView column5 = new TextView(this);
            TextView column6 = new TextView(this);

            column1.setText(Integer.toString(order_no));
            column1.setPadding(5, 5, 5, 5);
            column1.setTextColor(Color.parseColor("#E8630A"));
            column1.setTextSize(20);

            column2.setText(Integer.toString(table_no));
            column2.setPadding(5, 5, 5, 5);
            column2.setTextColor(Color.parseColor("#E8630A"));
            column2.setTextSize(20);

            column3.setText(d1);
            column3.setPadding(5, 5, 5, 5);
            column3.setTextColor(Color.parseColor("#E8630A"));
            column3.setTextSize(20);

            column4.setText(d2);
            column4.setPadding(5, 5, 5, 5);
            column4.setTextColor(Color.parseColor("#E8630A"));
            column4.setTextSize(20);

            column5.setText(d3);
            column5.setPadding(5, 5, 5, 5);
            column5.setTextColor(Color.parseColor("#E8630A"));
            column5.setTextSize(20);

            column6.setText(d4);
            column6.setPadding(5, 5, 5, 5);
            column6.setTextColor(Color.parseColor("#E8630A"));
            column6.setTextSize(20);

            newRow.addView(column1);
            newRow.addView(column2);
            newRow.addView(column3);
            newRow.addView(column4);
            newRow.addView(column5);
            newRow.addView(column6);


            t1.addView(newRow, new TableLayout.LayoutParams());
        }
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("data list", null);
        Type type = new TypeToken<ArrayList<mydata>>() {}.getType();
        mydatalist = gson.fromJson(json, type);
        if (mydatalist == null) {
            mydatalist = new ArrayList<>();
        }
        else{
            Toast.makeText(Tableview.this, "data present" ,Toast.LENGTH_LONG ).show();
        }
    }
}