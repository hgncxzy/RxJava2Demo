package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * map 操作符的用法
 * map 操作符通过指定一个 Function 对象，将 Observable 转换为一个新的 Observable 对象并发射，
 * 观察者将收到新的 Observable 并处理。
 */
public class Map {
    private static final String TAG = "Map";

    /**
     * 通过 map 操作符将 Observable<Int> 转换为 Observable<String>
     */
    @SuppressLint("CheckResult")
    public void testMap() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onNext(4);
                })
                .map(integer -> "This is result " + integer + "\n")
                .subscribe(str -> Log.i(TAG, "accept: " + str));
    }
}
