package com.examples.customtouch;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zhu on 2016/8/7.
 */
public class ScrollerActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroller_layout2);
        findViewById(R.id.sl).scrollTo(0,100);
    }
}
