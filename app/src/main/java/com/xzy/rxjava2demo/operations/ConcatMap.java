package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * concatMap 操作符的用法。
 * concatMap 的操作符是将Observable发射的数据集合变成一个 Observable 集合。
 * 也就是说它可以将一个观察对象变换成多个观察对象，并且能保证事件的顺序。
 */
public class ConcatMap {
    private static final String TAG = "ConcatMap";

    @SuppressLint("CheckResult")
    public void testConcatMap() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        }).concatMap((Function<Integer, ObservableSource<String>>) integer -> {
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("I am value " + integer + "\n");
            }
            // 延迟 1 秒，方便观察结果
            return Observable.fromIterable(list).delay(1000, TimeUnit.MILLISECONDS);
//                return Observable.fromIterable(list);
        }).subscribe(s -> Log.i(TAG, "accept: " + s));
    }
}
