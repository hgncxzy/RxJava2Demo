package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;

/**
 * take 字符串
 * skip 操作符将源 Observable 发射过的数据过滤掉前 n 项，而 take 操作则只取前 n 项；
 * 另外还有 skipLast 和 takeLast 则是从后往前进行过滤。
 */
public class Take {
    private static final String TAG = "Take";

    @SuppressLint("CheckResult")
    public void testTake() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .take(3)
                .subscribe(strings -> Log.i(TAG, "accept: " + strings));
    }
}
