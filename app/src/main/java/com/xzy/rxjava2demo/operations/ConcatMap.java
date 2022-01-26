package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.xzy.rxjava2demo.MyLogger;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * concatMap 操作符的用法。
 * concatMap 的操作符是将Observable发射的数据集合变成一个 Observable 集合。
 * 也就是说它可以将一个观察对象变换成多个观察对象，并且能保证事件的顺序。
 *
 * @author xzy
 */
public class ConcatMap {
    private static final String TAG = "ConcatMap";

    @SuppressLint("CheckResult")
    public void testConcatMap() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            MyLogger.INSTANCE.d(TAG, "emitter: " + emitter.toString());
        }).concatMap((Function<Integer, ObservableSource<String>>) integer -> {
            final List<String> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                list.add("I am value " + integer + "\n");
            }
            Log.i(TAG, "list.size = " + list.size());
            MyLogger.INSTANCE.d(TAG, "list.size = " + list.size());
            // 延迟 1 秒，方便观察结果
            return Observable.fromIterable(list).delay(1000, TimeUnit.MILLISECONDS);
//                return Observable.fromIterable(list);
        }).subscribe(s -> {
            Log.i(TAG, "accept: " + s);
            MyLogger.INSTANCE.d(TAG, "accept: " + s);
        });

        String[] arr = {"1", "2", "3"};
        Flowable.fromArray(arr)
                .concatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull String s) throws Exception {
                        return Flowable.just(s);
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return s + 100;
                    }
                })
                .flatMap(new Function<String, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(@NonNull String s) throws Exception {
                        return Flowable.just(s);
                    }
                })
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("xzy", "xzy--" + o.toString());
                    }
                })
                .subscribe();

        /**
         *
         * 2022-01-26 15:49:58.715 27039-27039/com.xzy.rxjava2demo D/xzy: xzy--1100
         * 2022-01-26 15:49:58.715 27039-27039/com.xzy.rxjava2demo D/xzy: xzy--2100
         * 2022-01-26 15:49:58.716 27039-27039/com.xzy.rxjava2demo D/xzy: xzy--3100
         * **/
    }
}
