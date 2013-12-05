
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
        all = this.findViewById(R.id.full_screen); // �õ�������Ļ���� �� ��Ϊ���� layout ��
                                                   // width �� height ����
                                                   // fill_parent
        vto = (ViewTreeObserver) all.getViewTreeObserver(); // ͨ��
                                                            // getViewTreeObserver
                                                            // ���
                                                            // ViewTreeObserver
                                                            // ����
        tv_display = (TextView) this.findViewById(R.id.tv_display);
        ed1 = (EditText) this.findViewById(R.id.ed_enter1);
        ed2 = (EditText) this.findViewById(R.id.ed_enter2);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(this);
        vto.addOnTouchModeChangeListener(this); // ���Ӷ�Ӧ�� Listener
        vto.addOnGlobalFocusChangeListener(this); // ���Ӷ�Ӧ�� Listener
       /* vto.addOnPreDrawListener(this); // ���Ӷ�Ӧ�� Listener*/
        vto.addOnGlobalLayoutListener(this); // ���Ӷ�Ӧ�� Listener

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        // �ı� ed2 �Ŀɼ��� �� �ᴥ�� onGlobalLayout ������ִ��
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
        // ����Ļ�ϻ��� ed1 �ؼ�֮ǰ �� ��������һ����ʾ �� ���ı��������С
        ed1.setHint(" �� onPreDraw ����������һ����ʾ��Ϣ ");
        ed1.setTextSize((float) 20.0);
        // return false; // Return true to proceed with the current drawing
        // pass, or false to cancel.
        return true; // ����˴������� true �� ���������治��������ʾ��
    }

    @Override
    public void onGlobalLayout() {
        // TODO Auto-generated method stub
        if (btnClicked)
        {
            if (!ed2.isShown())
                tv_show.setText(" �ڶ��� EditText ������ ");
            else
                tv_show.setText(" �ڶ��� EditText ������ ");

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
