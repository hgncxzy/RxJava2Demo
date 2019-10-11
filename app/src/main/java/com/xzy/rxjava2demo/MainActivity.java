package com.xzy.rxjava2demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xzy.rxjava2demo.basic.Basic;
import com.xzy.rxjava2demo.operations.FromArray;
import com.xzy.rxjava2demo.operations.Just;

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
    }
}
