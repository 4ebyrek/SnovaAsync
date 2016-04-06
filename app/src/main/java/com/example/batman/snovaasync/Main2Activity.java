package com.example.batman.snovaasync;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       // lv = (ListView) findViewById(R.id.listView2);
        TextView txv = (TextView) findViewById(R.id.txv);
        Intent intent = getIntent();
        txv.setText(intent.getStringExtra("first"));
        //adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,intent.getStringArrayListExtra("first"));
        //lv.setAdapter(adapter1);

    }

}
