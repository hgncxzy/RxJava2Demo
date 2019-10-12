package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * take 字符串
 * skip操作符将源Observable发射过的数据过滤掉前n项，而take操作则只取前n项；
 * 另外还有skipLast和takeLast则是从后往前进行过滤。先来看看skip操作符。
 */
public class Take {
    private static final String TAG = "Take";

    @SuppressLint("CheckResult")
    public void testTake() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer strings) {
                        Log.i(TAG, "accept: " + strings);
                    }
                });
    }
}
