package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class OnExceptionResumeNext {
    private static final String TAG = "OnExceptionResumeNext";
    @SuppressLint("CheckResult")
    public OnExceptionResumeNext(){
        Flowable.just(1)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        throw new Exception();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,"throwable1:"+throwable.getMessage());
                    }
                })
                .onExceptionResumeNext(new Publisher<Integer>() {
                    @Override
                    public void subscribe(Subscriber<? super Integer> s) {
                        Log.e(TAG,"我执行了");
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"value:"+integer);
                    }
                })
                .subscribe();
                /**
                 * 打印结果
                 * 2019-10-21 22:17:45.979 10172-10172/com.xzy.rxjava2demo E/OnExceptionResumeNext: throwable1:null
                 * 2019-10-21 22:17:45.979 10172-10172/com.xzy.rxjava2demo E/OnExceptionResumeNext: 我执行了
                 *
                 * */
    }
}
