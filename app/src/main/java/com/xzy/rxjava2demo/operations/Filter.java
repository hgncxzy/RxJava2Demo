package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * filter 操作符
 * filter 操作符是对源 Observable 产生的结果进行有规则的过滤。只有满足规则的结果才会提交到观察者手中。
 */
public class Filter {
    private static final String TAG = "Filter";

    @SuppressLint("CheckResult")
    public void testFilter() {
        Observable
                .just(1, 2, 3)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer < 3;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) {
                        Log.i(TAG, "accept: " + s);
                    }
                });
    }
}
