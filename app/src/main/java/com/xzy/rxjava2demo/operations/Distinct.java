package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * distinct 操作符
 * 这个操作符其实就更简单了。比如说，我要在一组数据中去掉重复的内容，就要用到它。也就是去重。
 * 它只允许还没有发射的数据项通过。发射过的数据项直接pass。
 */
public class Distinct {
    private static final String TAG = "Distinct";

    @SuppressLint("CheckResult")
    public void testDistinct() {
        Observable.just(1, 2, 3, 4, 2, 3, 5, 6, 1, 3)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) {
                        Log.i(TAG, "accept: " + s);
                    }
                });
    }
}
