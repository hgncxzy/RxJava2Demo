package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

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
                .zip(observable1, observable2, new BiFunction<Integer, String, String>() {

                    @Override
                    public String apply(Integer integer, String s) {
                        return integer + s;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Log.i(TAG, "apply: " + s);
                    }
                });
    }
}
