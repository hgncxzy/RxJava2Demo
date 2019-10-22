package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;

/**
 * concat 操作符
 * 将多个 Observable 发射的数据进行合并并且发射，
 * 和merge 不同的是，merge 是无序的，而 concat 是有序的。（串行有序）没有发射完前一个它一定不会发送后一个。
 */
public class Concat {
    private static final String TAG = "Concat";

    @SuppressLint("CheckResult")
    public void testConcat() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6);
        Observable
                .concat(observable1, observable2)
                .subscribe(integer -> Log.i(TAG, "accept: " + integer));
    }
}
