package com.xzy.rxjava2demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xzy.rxjava2demo.basic.Basic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RxJava2 基本用法
        Basic basic = new Basic();
        basic.toScribe();
    }
}
