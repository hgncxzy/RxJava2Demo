package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import io.reactivex.Observable;

/**
 * zip 操作符
 * 此操作符可合并多个 Observable 发送的数据项，根据他们的类型进行重新变换，并发射一个新的值。
 */
public class Zip {
    private static final String TAG = "Zip";

    @SuppressLint("CheckResult")
    public void testZip() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<String> observable2 = Observable.just("a", "b", "c");
        Observable
                .zip(observable1, observable2, (integer, s) -> integer + s)
                .subscribe(s -> {
                    Log.i(TAG, "apply: " + s);
                    MyLogger.INSTANCE.d(TAG, "apply: " + s);
                });
    }
}
