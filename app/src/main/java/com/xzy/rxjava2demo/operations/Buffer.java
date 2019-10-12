package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * buffer 操作符
 * 主要是缓存，把源Observable转换成一个新的Observable。
 * 这个新的Observable每次发射的是一组List，而不是单独的一个个的发送数据源
 */
public class Buffer {
    private static final String TAG = "Buffer";

    @SuppressLint("CheckResult")
    public void testBuffer() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .buffer(2) // 每次缓存 2 个
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> strings) {
                        for (Integer integer : strings) {
                            Log.i(TAG, "accept: " + integer);
                        }
                        Log.i(TAG, "accept: ----------------------->");
                    }
                });
    }
}
