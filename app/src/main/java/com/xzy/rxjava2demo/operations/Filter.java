package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import io.reactivex.Observable;

/**
 * filter 操作符
 * filter 操作符是对源 Observable 产生的结果进行有规则的过滤。只有满足规则的结果才会提交到观察者手中。
 *
 * @author xzy
 */
public class Filter {
    private static final String TAG = "Filter";

    @SuppressLint("CheckResult")
    public void testFilter() {
        Observable
                .just(1, 2, 3)
                .filter(integer -> integer < 3)
                .subscribe(s -> {
                    Log.i(TAG, "accept: " + s);
                    MyLogger.INSTANCE.d(TAG, "accept: " + s);
                });
    }
}
