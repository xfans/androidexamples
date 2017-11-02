/*
 * Copyright (c) 2012 Wireless Designs, LLC
 *
 * See the file license.txt for copying permission.
 */
package com.examples.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examples.demo.ex1.MoveLoggerActivity;
import com.examples.demo.ex10.ScrollerActivity;
import com.examples.demo.ex11.ScrollerActivity2;
import com.examples.demo.ex12.TouchActivity;
import com.examples.demo.ex13.WaterActivity;
import com.examples.demo.ex14.PullToRefreshActivity;
import com.examples.demo.ex15.PhotoViewActivity;
import com.examples.demo.ex16.SVGTestActivity;
import com.examples.demo.ex17.CanvasLayerActivity;
import com.examples.demo.ex2.TouchListenerActivity;
import com.examples.demo.ex3.TouchDelegateActivity;
import com.examples.demo.ex4.TouchForwardActivity;
import com.examples.demo.ex5.TwoDimensionScrollActivity;
import com.examples.demo.ex6.TwoDimensionGestureScrollActivity;
import com.examples.demo.ex7.MultitouchActivity;
import com.examples.demo.ex8.TouchInterceptActivity;
import com.examples.demo.ex9.ImageActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends ListActivity implements OnItemClickListener {

    List<Class> mActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getListView().setSelectionFromTop(17,-200);

        List<Class> excludeList = new ArrayList<Class>();
        excludeList.add(this.getClass());
        mActivities = ClassUtils.getActivitiesClass(this, getPackageName(), excludeList);

//        Collections.sort(mActivities, new Comparator<Class>() {
//            @Override
//            public int compare(Class o1, Class o2) {
//                String s1 = o1.toString();
//                String ns1 = s1.substring(0,s1.lastIndexOf("."));
//
//                String s2 = o2.toString();
//                String ns2 = s2.substring(0,s2.lastIndexOf("."));
//
//                return ns1.compareTo(ns2);
//            }
//        });
        getListView().setAdapter(new Adapter());
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(MainActivity.this, mActivities.get(position)));
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mActivities.size();
        }

        @Override
        public Object getItem(int position) {
            return mActivities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_item, null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText(mActivities.get(position).getSimpleName());
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
