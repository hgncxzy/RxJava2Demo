package com.xzy.rxjava2demo.error;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xzy.rxjava2demo.MyLogger;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.xzy.rxjava2demo.MainActivity.compositeDisposable;

/**
 * retryWhen 操作符响应 onError 事件，也就是说当触发了 onError 事件之后，才会触发 retryWhen 事件
 *
 * @author xzy
 */
public class RetryWhen {
    private static final String TAG = "RetryWhen";

    @SuppressLint("CheckResult")
    public RetryWhen() {
        final int[] time = {0};
        compositeDisposable
                .add(Flowable.just(1)
                        .delay(2, TimeUnit.SECONDS)
                        .doOnNext(integer -> {
                            time[0]++;
                            if (time[0] <= 3) {
                                MyLogger.INSTANCE.i(TAG, "value:" + integer);
                                throw new Exception();
                            }
                        })
                        .doOnError(throwable -> MyLogger.INSTANCE.e(TAG, "throwable:" + throwable.getMessage()))
                        .retryWhen(throwableFlowable -> {
                            MyLogger.INSTANCE.i(TAG, "执行 retryWhen");
                            return throwableFlowable.delay(2, TimeUnit.SECONDS);
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                MyLogger.INSTANCE.d(TAG, "doOnComplete");
                            }
                        })
                        .doOnNext(integer -> MyLogger.INSTANCE.i(TAG, "value2:" + integer))
                        .subscribe(integer -> MyLogger.INSTANCE.i(TAG, "subscribe"),
                                throwable -> MyLogger.INSTANCE.e(TAG, "捕获异常")));
        /**
         * 打印结果：retryWhen 事件打印一次，条件控制执行 3 次重试，然后结束。
         *
         * 2020-07-09 15:41:59.273 24962-24962/com.xzy.rxjava2demo I/RetryWhen: 执行 retryWhen
         * 2020-07-09 15:42:01.278 24962-25029/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2020-07-09 15:42:01.278 24962-25029/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         * 2020-07-09 15:42:05.281 24962-25040/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2020-07-09 15:42:05.281 24962-25040/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         * 2020-07-09 15:42:09.283 24962-25042/com.xzy.rxjava2demo I/RetryWhen: value:1
         * 2020-07-09 15:42:09.284 24962-25042/com.xzy.rxjava2demo E/RetryWhen: throwable:null
         * 2020-07-09 15:42:13.286 24962-25031/com.xzy.rxjava2demo I/RetryWhen: value2:1
         * 2020-07-09 15:42:13.286 24962-25031/com.xzy.rxjava2demo I/RetryWhen: subscribe
         * 2020-07-09 15:42:13.286 24962-25031/com.xzy.rxjava2demo D/RetryWhen: doOnComplete
         *
         * */
    }
}
