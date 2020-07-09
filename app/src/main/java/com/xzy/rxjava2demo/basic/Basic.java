package com.xzy.rxjava2demo.basic;

import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * RxJava2 的基本用法。三步骤
 * <p>
 * 1. 创建 Observable 对象
 * 2. 创建 Observer 对象
 * 3. 使用 Observable 对象订阅 Observer 对象
 * @author xzy
 */
public class Basic {
    private static final String TAG = "Basic";

    /**
     * 创建 Observable 对象
     *
     * @return Observable
     */
    private Observable<String> createObservable() {
        Observable<String> mObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("Hello");
                emitter.onNext("RxJava2");
                emitter.onNext("My name is XZY");
                emitter.onNext("What's your name");
                //一旦调用onComplete,下面将不在接受事件
                emitter.onComplete();
            }
        });
        return mObservable;
    }

    /**
     * 创建 Observer 对象
     *
     * @return Observer
     */
    private Observer<String> createObserver() {
        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: " + d);
                MyLogger.INSTANCE.d(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(String string) {
                Log.i(TAG, "onNext: " + string);
                MyLogger.INSTANCE.d(TAG, "onNext: " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e);
                MyLogger.INSTANCE.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
                MyLogger.INSTANCE.d(TAG, "onComplete ");
            }
        };

        return mObserver;
    }

    /**
     * 使用 Observable 对象 订阅 Observer 对象
     */
    public void toScribe() {
        createObservable().subscribe(createObserver());
    }
}
