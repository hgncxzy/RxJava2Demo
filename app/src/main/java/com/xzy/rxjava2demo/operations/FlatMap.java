package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * flatMap 操作符的用法
 * flatMap 的操作符是将 Observable 发射的数据集合变成一个 Observable 集合。
 * 也就是说它可以将一个观察对象变换成多个观察对象，但是并不能保证事件的顺序。
 */
public class FlatMap {
    private static final String TAG = "FlatMap";

    @SuppressLint("CheckResult")
    public void testFlatMap() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                })
                .flatMap((Function<Integer, ObservableSource<String>>) integer -> {
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        list.add("I am value " + integer + "\n");
                    }
                    Log.i(TAG, "list.size = " + list.size());
                    return Observable.fromIterable(list);
                })
                .subscribe(s -> Log.i(TAG, "accept: " + s)
                        , throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage())));
    }
}
