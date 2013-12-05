package com.alex.andnemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class AListViewHeader extends LinearLayout {

    private Context mCtx;
    private LinearLayout mView;
    

    public AListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        // TODO Auto-generated constructor stub
        initView();
    }

    public AListViewHeader(Context context) {
        super(context);
        mCtx = context;
        // TODO Auto-generated constructor stub
        initView();
    }

    private void initView() {
        setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        mView = (LinearLayout) LayoutInflater.from(mCtx).inflate(R.layout.alist_header, null);
        addView(mView, lp);
    }
    
    public View getHeaderView() {
        return mView;
    }
    
}
