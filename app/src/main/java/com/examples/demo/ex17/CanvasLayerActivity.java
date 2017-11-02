package com.examples.demo.ex17;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.examples.demo.R;

/**
 * Created by xfans on 17-9-18.
 */

public class CanvasLayerActivity extends Activity {
    CustomCanvasLayerView customCanvasLayerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canvas_layer_view);
        customCanvasLayerView = (CustomCanvasLayerView) findViewById(R.id.view);
        customCanvasLayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        customCanvasLayerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CanvasLayerActivity.this,"change",Toast.LENGTH_LONG).show();
                customCanvasLayerView.setColor();
            }
        });
    }
}
