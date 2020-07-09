package com.xzy.rxjava2demo.operations;

import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;


import java.util.Arrays;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ：created by xzy.
 * @date ：2020/7/8
 */
public class FromIterable {
    private static final String TAG = "FromIterable";

    public void testFromIterable() {
        String[] words = {"Hello", "Rxjava2", "My name is Silence", "What's your name"};
        Observable<String> observable = Observable.fromIterable(Arrays.asList(words));
        // 将会依次调用：
        // onNext("Hello");
        // onNext("Rxjava2");
        // onNext("My name is Silence");
        // onNext("What's your name");
        // onCompleted();
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
                MyLogger.INSTANCE.d(TAG, "onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + s);
                MyLogger.INSTANCE.d(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e);
                MyLogger.INSTANCE.d(TAG, "onError:" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
                MyLogger.INSTANCE.d(TAG, "onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
