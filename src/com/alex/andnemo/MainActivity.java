
package com.alex.andnemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    
    private static final String TAG = "MainActivity";
    private ListView funcListV;
    private List<String> funcListL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        funcListV = (ListView) findViewById(R.id.act_main_funclist);
        funcListL = new ArrayList<String>();
        funcListL.add("load more list");
        funcListL.add("calendar");
        ArrayAdapter<String> aad = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, 
                android.R.id.text1, funcListL);
        funcListV.setAdapter(aad);
        funcListV.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                switch(position) {
                case 0: {
                    Intent i = new Intent(MainActivity.this, LoadMoreListA.class);
                    startActivity(i);
                }
                break;
                case 1: {
                    Intent i = new Intent(MainActivity.this, CalendarA.class);
                    startActivity(i);
                }
                break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
