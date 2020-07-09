package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import org.reactivestreams.Subscriber;

import java.util.Objects;

import io.reactivex.Flowable;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * 触发 onExceptionResumeNext 的是源 Observable 发生了异常（Exception）
 * 如果源 Observable 发生了错误仍然像默认情况一样调用 Observer 的 onError 方法。
 *
 * @author xzy
 */
public class OnExceptionResumeNext {
    private static final String TAG = "OnExceptionResumeNext";

    @SuppressLint("CheckResult")
    public OnExceptionResumeNext() {
        compositeDisposable
                .add(Flowable.just(1)
                        .doOnNext(integer -> {
                            MyLogger.INSTANCE.d(TAG, "抛出异常");
                            throw new Exception("exception");
                        })
                        .doOnError(throwable -> {
                            MyLogger.INSTANCE.d(TAG, "throwable1:" + throwable.getMessage());
                        })
                        .onExceptionResumeNext(s -> {
                            MyLogger.INSTANCE.d(TAG, "onExceptionResumeNext 执行了 " + s);
                        })
                        .doOnNext(integer -> {
                            MyLogger.INSTANCE.d(TAG, "value:" + integer);
                        })
                        .subscribe(o -> {
                        }, throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage()))));

        /**
         * 打印结果
         * 2020-07-09 11:49:03.274 16699-16699/com.xzy.rxjava2demo D/OnExceptionResumeNext: 抛出异常
         * 2020-07-09 11:49:03.275 16699-16699/com.xzy.rxjava2demo D/OnExceptionResumeNext: throwable1:exception
         * 2020-07-09 11:49:03.275 16699-16699/com.xzy.rxjava2demo D/OnExceptionResumeNext: onExceptionResumeNext 执行了 0
         *
         * */
    }
}
