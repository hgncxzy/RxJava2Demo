package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * interval 操作符的用法
 * 这个其实就是定时器，用了它你可以抛弃CountDownTimer，表示定时执行。
 */
public class Interval {
    private static final String TAG = "Interval";

    @SuppressLint("CheckResult")
    public void testInterval() {
        Observable.interval(2, TimeUnit.SECONDS) // 每隔 2 秒执行一次
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        Log.i(TAG, "accept: " + aLong.intValue());
                    }
                });
    }
}
