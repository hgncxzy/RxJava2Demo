package com.xzy.rxjava2demo.operations;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * just 操作符的用法
 * <p>
 * 此操作符是将传入的参数依次发出来.
 */
public class Just {
    private static final String TAG = "Just";

    public void testJust() {
        Observable<String> observable = Observable.just("Hello", "RxJava2", "My name is XZY", "What's your name");
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
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError:" + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete:");
            }
        };

        observable.subscribe(observer);

    }
}
