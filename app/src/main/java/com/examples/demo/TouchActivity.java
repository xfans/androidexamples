package com.examples.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.examples.demo.utils.LogText;
import com.examples.demo.widget.LLayout;
import com.examples.demo.widget.LView;

/**
 * Created by xfans on 16/7/6.
 */
public class TouchActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    private LView mLView;
    private LLayout mLLayout;
    private CheckBox mCheckBox1, mCheckBox2, mCheckBox3;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_test);
        mLView = (LView) findViewById(R.id.lv);
        mLLayout = (LLayout) findViewById(R.id.ll);
        mTextView = (TextView) findViewById(R.id.tv);
        mButton = (Button) findViewById(R.id.clear);
        mCheckBox1 = (CheckBox) findViewById(R.id.cb1);
        mCheckBox2 = (CheckBox) findViewById(R.id.cb2);
        mCheckBox3 = (CheckBox) findViewById(R.id.cb3);
        mCheckBox1.setOnCheckedChangeListener(this);
        mCheckBox2.setOnCheckedChangeListener(this);
        mCheckBox3.setOnCheckedChangeListener(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText("");
            }
        });
        LogText.setMtv(mTextView);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb1:
                mLView.setOnTouchEvent(isChecked);
                break;
            case R.id.cb2:
                mLLayout.setOnTouchEvent(isChecked);
                break;
            case R.id.cb3:
                mLLayout.setOnInterceptTouchEvent(isChecked);
                break;
        }
    }
}
