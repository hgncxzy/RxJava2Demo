package com.xzy.rxjava2demo.operations;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * compose 操作符的用法
 * 把多个 Observable 转化成一个 Observable
 */
public class Compose {
    private static final String TAG = "Compose";

    private <T> ObservableTransformer<T, T> applyObservableAsync() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @SuppressLint("CheckResult")
    public void testCompose() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .compose(this.<Integer>applyObservableAsync())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer strings) {
                        Log.i(TAG, "accept: " + strings);
                    }
                });
    }
}
