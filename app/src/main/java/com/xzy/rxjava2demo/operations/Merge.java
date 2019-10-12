package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * merge 操作符
 * merge是将多个操作符合并到一个Observable中进行发射，
 * merge可能让合并到Observable的数据发生错乱。（并行无序）
 */
public class Merge {
    private static final String TAG = "Merge";

    @SuppressLint("CheckResult")
    public void testMerge() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(1, 2, 3);
        Observable.merge(observable1, observable2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        Log.i(TAG, "accept: " + integer);
                    }
                });
    }
}
