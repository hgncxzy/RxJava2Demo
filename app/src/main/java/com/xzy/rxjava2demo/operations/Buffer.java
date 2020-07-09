package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import io.reactivex.Observable;


/**
 * buffer 操作符
 * 主要是缓存，把源Observable转换成一个新的Observable。
 * 这个新的Observable每次发射的是一组List，而不是单独的一个个的发送数据源
 *
 * @author xzy
 */
public class Buffer {
    private static final String TAG = "Buffer";

    @SuppressLint("CheckResult")
    public void testBuffer() {
        Observable.just(1, 2, 3, 4, 5, 6)
                // 每次缓存 2 个
                .buffer(2)
                .subscribe(strings -> {
                    for (Integer integer : strings) {
                        Log.i(TAG, "accept: " + integer);
                        MyLogger.INSTANCE.d(TAG, "accept: " + integer);
                    }
                    Log.i(TAG, "accept: ----------------------->");

                });
    }
}
