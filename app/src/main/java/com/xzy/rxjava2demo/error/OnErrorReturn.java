package com.xzy.rxjava2demo.error;

import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class OnErrorReturn {
    private static final String TAG = "OnErrorReturn";
    public OnErrorReturn(){
        Flowable.just(1)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"value:"+integer);
                        throw new Exception();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,"throwable1:"+throwable.getMessage());
                    }
                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        Log.e(TAG,"throwable2:"+throwable.getMessage());
                        return 2;
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,"value2:"+integer);
                    }
                })
                .subscribe();
        /**
         * 打印结果
         * 2019-10-21 21:57:28.612 9580-9580/com.xzy.rxjava2demo I/OnErrorReturn: value:1
         * 2019-10-21 21:57:28.613 9580-9580/com.xzy.rxjava2demo E/OnErrorReturn: throwable1:null
         * 2019-10-21 21:57:28.613 9580-9580/com.xzy.rxjava2demo E/OnErrorReturn: throwable2:null
         * 2019-10-21 21:57:28.614 9580-9580/com.xzy.rxjava2demo I/OnErrorReturn: value2:2
         *
         * */
    }
}
