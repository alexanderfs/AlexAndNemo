package com.alex.andnemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alex.andnemo.XListView.IXListViewListener;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreListA extends Activity {
    private static final String TAG = "LoadMoreListA";
    private AListView alv;
    private List<String> data = new ArrayList<String>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loadmorelist);
        alv = (AListView) findViewById(R.id.act_lm_list);
        for(int i = 0; i < 3; i++) {
            data.add("²âÊÔ" + i);
        }
        AListAdapter ala = new AListAdapter();
        alv.setAdapter(ala);
       /* alv.setXListViewListener(new IXListViewListener() {
            
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alv.stopRefresh();
                    }
                }, 2000);
            }
            
            @Override
            public void onLoadMore() {
                // TODO Auto-generated method stub
                
            }
        });*/
    }
    
    private class AListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder vh;
            if(convertView == null) {
                convertView = LayoutInflater.from(LoadMoreListA.this)
                        .inflate(android.R.layout.simple_list_item_1, null);
                vh = new ViewHolder();
                vh.tv = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(vh);
            }
            vh = (ViewHolder)convertView.getTag();
            vh.tv.setText(data.get(position));
            return convertView;
        }
    }
    
    private static class ViewHolder {
        TextView tv;
    }
}
