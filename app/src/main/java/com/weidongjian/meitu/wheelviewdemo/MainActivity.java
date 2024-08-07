package com.weidongjian.meitu.wheelviewdemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemScrollListener;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoopView loopView = (LoopView) findViewById(R.id.loopView);
        initLoopView(loopView);

        LoopView loopView2 = (LoopView) findViewById(R.id.loopView2);
        initLoopView(loopView2);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loopView.setCurrentPosition(4);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLoopView(LoopView loopView) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("item gp" + i);
        }
        //滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (toast == null) {
                    toast = Toast.makeText(MainActivity.this, "item " + index, Toast.LENGTH_SHORT);
                }
                toast.setText("item " + index);
                toast.show();
            }
        });
        loopView.setOnItemScrollListener(new OnItemScrollListener() {
            @Override
            public void onItemScrollStateChanged(LoopView loopView, int currentPassItem, int oldScrollState, int scrollState, int totalScrollY) {
                Log.i("gy", String.format("onItemScrollStateChanged currentPassItem %d  oldScrollState %d  scrollState %d  totalScrollY %d", currentPassItem, oldScrollState, scrollState, totalScrollY));
            }

            @Override
            public void onItemScrolling(LoopView loopView, int currentPassItem, int scrollState, int totalScrollY) {
                Log.i("gy", String.format("onItemScrolling currentPassItem %d  scrollState %d  totalScrollY %d", currentPassItem, scrollState, totalScrollY));
            }
        });
        //设置原始数据
        loopView.setItems(list);
        //设置初始位置
        loopView.setInitPosition(4);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
