package com.examples.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.demo.widget.PullToRefreshView;

/**
 * Created by xfan on 2017/2/28.
 */

public class PullToRefreshActivity extends Activity{
    private PullToRefreshView mPullToRefreshView;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_activity);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new Adapter());
    }

    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 200;
        }

        @Override
        public Object getItem(int position) {
            return "Hello"+position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(PullToRefreshActivity.this);

            tv.setText("Hello"+position);
            return tv;
        }
    }
}
