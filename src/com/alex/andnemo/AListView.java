package com.alex.andnemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

public class AListView extends ListView {

    private Context mCtx;
    private AListViewHeader mavh;
    private Scroller mScroller;
    private GestureDetector mGd;
    private int headerViewHeight = 0;
    private LinearLayout mLL;
    private boolean isFirst = true;
    
    public AListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mCtx = context;
        init();
    }

    public AListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        mCtx = context;
        init();
    }

    public AListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mCtx = context;
        init();
    }
    
    private void init() {
        mavh = new AListViewHeader(mCtx);
        mLL = (LinearLayout) mavh.findViewById(R.id.ah_ll);
        addHeaderView(mavh);
        
        mScroller = new Scroller(mCtx);
        mGd = new GestureDetector(mCtx, new AOnGestureListener());
        mavh.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                headerViewHeight = mLL.getHeight();
                System.out.println("53>>>>>>>>>>>" + headerViewHeight);
                getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });
    }
    
    
    
    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        if(mScroller.computeScrollOffset()) {
            LinearLayout.LayoutParams lp = 
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, -mScroller.getCurrY());
            mavh.setLayoutParams(lp);
        }
       /* System.out.println(mScroller.getCurrY());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, -mScroller.getCurrY());
        mavh.getHeaderView().setLayoutParams(lp);*/
        //setSelection(0);
        if(!isFirst)
            invalidate();
        super.computeScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch(ev.getAction()) {
            case MotionEvent.ACTION_UP:
                mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), 
                        -mScroller.getFinalX(), -mScroller.getFinalY());
                invalidate();
                break;
            default:
                System.out.println("default");
                mGd.onTouchEvent(ev);
                break;
        }
        return super.onTouchEvent(ev);
    }
    
    
    private class AOnGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            System.out.println(mScroller.getFinalY()+", "+distanceY);
            isFirst = true;
            mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), 0, (int)distanceY/2);
            invalidate();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // TODO Auto-generated method stub
            return false;
        }
        
    }
}
