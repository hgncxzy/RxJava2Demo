package com.xzy.rxjava2demo.error;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * repeatWhen 操作符不会响应 onError 事件，也就是说遇到 onError 事件时，不会重复。
 * repeatWhen 只会响应 onCompleted 事件
 */
public class RepeatWhen {
    private static final String TAG = "RepeatWhen";
    private static final String TAG1 = "RepeatWhen1";

    public RepeatWhen() {
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> Log.i(TAG, "value1:" + integer))
                        .doOnError(throwable -> Log.e(TAG, "throwable:" + throwable.getMessage()))
                        .repeatWhen(objectFlowable -> {
                            Log.i(TAG, "repeatWhen:**");
                            return objectFlowable;
                        })
                        .doOnNext(integer -> Log.i(TAG, "value2:" + integer))
                        .subscribe(integer -> Log.i(TAG, "value2:" + integer), throwable -> Log.e(TAG, "捕获异常")));

        /**
         * 打印结果：repeatWhen 事件打印一次，其余循环打印
         * 2019-10-22 11:11:14.987 17008-17008/com.xzy.rxjava2demo I/RepeatWhen: repeatWhen:**
         * 2019-10-22 11:11:16.989 17008-17057/com.xzy.rxjava2demo I/RepeatWhen: value1:1
         * 2019-10-22 11:11:16.990 17008-17057/com.xzy.rxjava2demo I/RepeatWhen: value2:1
         * 2019-10-22 11:11:16.990 17008-17057/com.xzy.rxjava2demo I/RepeatWhen: value2:1
         * 2019-10-22 11:11:18.992 17008-17060/com.xzy.rxjava2demo I/RepeatWhen: value1:1
         * 2019-10-22 11:11:18.992 17008-17060/com.xzy.rxjava2demo I/RepeatWhen: value2:1
         * */

        compositeDisposable
                .add(Flowable.just(111)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            Log.i(TAG1, "value1:" + integer);
                            throw new Exception();
                        })
                        .doOnError(throwable -> Log.e(TAG1, "throwable:" + throwable.getMessage()))
                        .repeatWhen(objectFlowable -> {
                            Log.i(TAG1, "repeatWhen:**");
                            return objectFlowable;
                        })
                        .doOnNext(integer -> Log.i(TAG1, "value2:" + integer))
                        .subscribe(integer -> Log.i(TAG1, "value2:" + integer), new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG1, "捕获异常");
                            }
                        }));
        /**
         * 打印结果：执行一次，repeatWhen 无法响应 onError 事件
         * 2019-10-22 11:15:09.927 17309-17309/? I/RepeatWhen: repeatWhen:**
         * 2019-10-22 11:15:11.928 17309-17333/com.xzy.rxjava2demo I/RepeatWhen: value1:111
         * 2019-10-22 11:15:11.928 17309-17333/com.xzy.rxjava2demo E/RepeatWhen: throwable:null
         * 2019-10-22 11:15:11.928 17309-17333/com.xzy.rxjava2demo E/RepeatWhen: 捕获异常
         *
         * **/
    }
}
