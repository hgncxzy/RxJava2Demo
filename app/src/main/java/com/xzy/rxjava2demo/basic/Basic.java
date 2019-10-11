package com.xzy.rxjava2demo.basic;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Basic {
    private static final String TAG = "Basic";

    public Basic() {

    }

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

    private Observer<String> createObserver() {
        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(String string) {
                Log.i(TAG, "onNext: " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        };

        return mObserver;
    }

    public void toScribe() {
        createObservable().subscribe(createObserver());
    }
}
