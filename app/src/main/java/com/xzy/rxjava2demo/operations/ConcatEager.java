package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * concatEager 操作符
 * 前面说到 concat 串行有序，而 concatEager 则是并行且有序
 *
 * @author xzy
 */
public class ConcatEager {
    private static final String TAG = "ConcatEager";

    @SuppressLint("CheckResult")
    public void testConcatEager() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<String> observable2 = Observable.just("a", "b", "c");
        Observable
                .concatEager(Observable.fromArray(observable1, observable2))
                .subscribe((Consumer<Serializable>) serializable -> {
                    Log.i(TAG, "accept: " + serializable);
                    MyLogger.INSTANCE.d(TAG, "accept: " + serializable);
                });
    }
}
