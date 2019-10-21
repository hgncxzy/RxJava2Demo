package com.xzy.rxjava2demo.error;

import android.util.Log;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class OnErrorResumeNext {
    private static final String TAG = "OnErrorResumeNext";
    public OnErrorResumeNext(){
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
                .onErrorResumeNext(new Function<Throwable, Publisher<? extends Integer>>() {
                    @Override
                    public Publisher<? extends Integer> apply(Throwable throwable) throws Exception {
                        Log.e(TAG,"throwable2:"+throwable.getMessage());
                       // return Flowable.empty();
                        return Flowable.just(1);
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
         * 2019-10-21 22:09:25.043 9934-9934/com.xzy.rxjava2demo E/OnErrorResumeNext: throwable1:null
         * 2019-10-21 22:09:25.044 9934-9934/com.xzy.rxjava2demo E/OnErrorResumeNext: throwable2:null
         * 2019-10-21 22:09:25.044 9934-9934/com.xzy.rxjava2demo I/OnErrorResumeNext: value:1
         *
         * */
    }
}
