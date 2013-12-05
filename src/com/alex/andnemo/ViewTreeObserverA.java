
package com.alex.andnemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTreeObserverA extends Activity
        implements OnClickListener,
        ViewTreeObserver.OnTouchModeChangeListener,
        ViewTreeObserver.OnGlobalLayoutListener,
        ViewTreeObserver.OnPreDrawListener,
        ViewTreeObserver.OnGlobalFocusChangeListener {

    private TextView tv_show;
    private ViewTreeObserver vto;
    private View all;
    private EditText ed1;
    private EditText ed2;
    private TextView tv_display;
    private Button button;
    private boolean btnClicked;

    @Override
    public void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtreeobserver);
        tv_show = (TextView) this.findViewById(R.id.tv_show);
        all = this.findViewById(R.id.full_screen); // 得到整个屏幕对象 ， 因为顶层 layout 的
                                                   // width 和 height 都是
                                                   // fill_parent
        vto = (ViewTreeObserver) all.getViewTreeObserver(); // 通过
                                                            // getViewTreeObserver
                                                            // 获得
                                                            // ViewTreeObserver
                                                            // 对象
        tv_display = (TextView) this.findViewById(R.id.tv_display);
        ed1 = (EditText) this.findViewById(R.id.ed_enter1);
        ed2 = (EditText) this.findViewById(R.id.ed_enter2);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(this);
        vto.addOnTouchModeChangeListener(this); // 增加对应的 Listener
        vto.addOnGlobalFocusChangeListener(this); // 增加对应的 Listener
       /* vto.addOnPreDrawListener(this); // 增加对应的 Listener*/
        vto.addOnGlobalLayoutListener(this); // 增加对应的 Listener

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        // 改变 ed2 的可见性 ， 会触发 onGlobalLayout 方法的执行
        btnClicked = true;
        if (v.getId() == R.id.button)
        {
            if (ed2.isShown())
                ed2.setVisibility(View.INVISIBLE);
            else
                ed2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        // TODO Auto-generated method stub
        if (oldFocus != null && newFocus != null)
        {
            tv_display.setText("Focus /nFROM:/t" + oldFocus.toString() + "/n     TO:/t"
                    + newFocus.toString());
        }
    }

    @Override
    public boolean onPreDraw() {
        // TODO Auto-generated method stub
        // 在屏幕上画出 ed1 控件之前 ， 给它增加一个提示 ， 并改变其字体大小
        ed1.setHint(" 在 onPreDraw 方法中增加一个提示信息 ");
        ed1.setTextSize((float) 20.0);
        // return false; // Return true to proceed with the current drawing
        // pass, or false to cancel.
        return true; // 如果此处不返回 true ， 则整个界面不能完整显示。
    }

    @Override
    public void onGlobalLayout() {
        // TODO Auto-generated method stub
        if (btnClicked)
        {
            if (!ed2.isShown())
                tv_show.setText(" 第二个 EditText 不见了 ");
            else
                tv_show.setText(" 第二个 EditText 出来了 ");

        }
    }

    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        // TODO Auto-generated method stub
        if (isInTouchMode)
            tv_show.setText("In touch mode");
        else
            tv_show.setText("Not in touch mode");
    }

}
