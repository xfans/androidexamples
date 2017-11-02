package com.examples.demo.ex10;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.demo.R;

/**
 * Created by zhu on 2016/8/7.
 */
public class ScrollerActivity extends Activity {
    private ViewPager mViewPager;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroller_layout);
//        findViewById(R.id.sl).scrollTo(0, 100);
                mLayoutInflater = LayoutInflater.from(this);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v = mLayoutInflater.inflate(R.layout.move_logger,container,false);
                TextView tv = (TextView)v.findViewById(R.id.view_logall);
                tv.setText("position:"+position);
                container.addView(v);
                return v;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("ScrollerLayout","position:"+position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
