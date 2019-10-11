package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * map 操作符的用法
 * map操作符通过指定一个Function对象，将Observable转换为一个新的Observable对象并发射，
 * 观察者将收到新的Observable处理。
 */
public class Map {
    private static final String TAG = "Map";

    /**
     * 通过 map 操作符将 Observable<Int> 转换为 Observable<String>
     */
    @SuppressLint("CheckResult")
    public void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "This is result " + integer + "\n";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) {
                Log.i(TAG, "accept: " + str);
            }
        });
    }
}
