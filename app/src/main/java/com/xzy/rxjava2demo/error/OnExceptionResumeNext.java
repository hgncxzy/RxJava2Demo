package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Objects;

import io.reactivex.Flowable;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * 触发 onExceptionResumeNext 的是源 Observable 发生了异常（Exception）
 * 如果源 Observable 发生了错误仍然像默认情况一样调用 Observer 的 onError 方法。
 */
public class OnExceptionResumeNext {
    private static final String TAG = "OnExceptionResumeNext";

    @SuppressLint("CheckResult")
    public OnExceptionResumeNext() {
        compositeDisposable
                .add(Flowable.just(1)
                        .doOnNext(integer -> {
                            throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG, "throwable1:" + throwable.getMessage()))
                        .onExceptionResumeNext(s -> Log.e(TAG, "我执行了"))
                        .onExceptionResumeNext(new Flowable<Integer>() {
                            @Override
                            protected void subscribeActual(Subscriber<? super Integer> s) {

                            }
                        })
                        .doOnNext(integer -> Log.i(TAG, "value:" + integer))
                        .subscribe(o -> {
                        }, throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage()))));

        /**
         * 打印结果
         * 2019-10-21 22:17:45.979 10172-10172/com.xzy.rxjava2demo E/OnExceptionResumeNext: throwable1:null
         * 2019-10-21 22:17:45.979 10172-10172/com.xzy.rxjava2demo E/OnExceptionResumeNext: 我执行了
         *
         * */
    }
}
