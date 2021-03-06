package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import io.reactivex.Observable;

/**
 * skip 操作符
 * skip 操作符将源 Observable 发射过的数据过滤掉前 n 项，而 take 操作则只取前 n 项；
 * 另外还有 skipLast 和 takeLast 则是从后往前进行过滤。
 *
 * @author xzy
 */
public class Skip {
    private static final String TAG = "Skip";

    @SuppressLint("CheckResult")
    public void testSkip() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .skip(2)
                .subscribe(string -> {
                    Log.i(TAG, "accept: " + string);
                    MyLogger.INSTANCE.d(TAG, "accept: " + string);
                });
    }
}
