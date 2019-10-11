package com.xzy.rxjava2demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xzy.rxjava2demo.basic.Basic;
import com.xzy.rxjava2demo.operations.FlatMap;
import com.xzy.rxjava2demo.operations.FromArray;
import com.xzy.rxjava2demo.operations.Interval;
import com.xzy.rxjava2demo.operations.Just;
import com.xzy.rxjava2demo.operations.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RxJava2 基本用法
        Basic basic = new Basic();
        basic.toScribe();
        // just
        Just just = new Just();
        just.testJust();
        // fromArray
        FromArray fromArray = new FromArray();
        fromArray.testFromArray();
        // interval
        Interval interval = new Interval();
        interval.testInterval();
        // map
        Map map = new Map();
        map.testMap();
        // flatMap
        FlatMap flatMap = new FlatMap();
        flatMap.testFlatMap();
    }
}
