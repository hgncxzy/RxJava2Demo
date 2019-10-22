package com.xzy.rxjava2demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xzy.rxjava2demo.basic.Basic;
import com.xzy.rxjava2demo.error.OnErrorResumeNext;
import com.xzy.rxjava2demo.error.OnErrorReturn;
import com.xzy.rxjava2demo.error.OnExceptionResumeNext;
import com.xzy.rxjava2demo.error.RepeatWhen;
import com.xzy.rxjava2demo.error.Retry;
import com.xzy.rxjava2demo.error.RetryWhen;
import com.xzy.rxjava2demo.operations.Buffer;
import com.xzy.rxjava2demo.operations.Compose;
import com.xzy.rxjava2demo.operations.Concat;
import com.xzy.rxjava2demo.operations.ConcatEager;
import com.xzy.rxjava2demo.operations.ConcatMap;
import com.xzy.rxjava2demo.operations.Distinct;
import com.xzy.rxjava2demo.operations.Filter;
import com.xzy.rxjava2demo.operations.FlatMap;
import com.xzy.rxjava2demo.operations.FromArray;
import com.xzy.rxjava2demo.operations.Interval;
import com.xzy.rxjava2demo.operations.Just;
import com.xzy.rxjava2demo.operations.Map;
import com.xzy.rxjava2demo.operations.Merge;
import com.xzy.rxjava2demo.operations.Skip;
import com.xzy.rxjava2demo.operations.Take;
import com.xzy.rxjava2demo.operations.Zip;

/**
 * test RxJava2
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RxJava2 基本用法
        Basic basic = new Basic();
        basic.toScribe();
        // just
        Just just = new Just();
        just.testJust();
        // fromArray
        FromArray fromArray = new FromArray();
        fromArray.testFromArray();
        // interval
        Interval interval = new Interval();
        interval.testInterval();
        // map
        Map map = new Map();
        map.testMap();
        // flatMap
        FlatMap flatMap = new FlatMap();
        flatMap.testFlatMap();
        // concatMap
        ConcatMap concatMap = new ConcatMap();
        concatMap.testConcatMap();
        // compose
        Compose compose = new Compose();
        compose.testCompose();
        // filter
        Filter filter = new Filter();
        filter.testFilter();
        // distinct
        Distinct distinct = new Distinct();
        distinct.testDistinct();
        // buffer
        Buffer buffer = new Buffer();
        buffer.testBuffer();
        // skip
        Skip skip = new Skip();
        skip.testSkip();
        // take
        Take take = new Take();
        take.testTake();
        // merge
        Merge merge = new Merge();
        merge.testMerge();
        // concat
        Concat concat = new Concat();
        concat.testConcat();
        // zip
        Zip zip = new Zip();
        zip.testZip();
        // concatEager
        ConcatEager concatEager = new ConcatEager();
        concatEager.testConcatEager();
        // onErrorReturn
        new OnErrorReturn();
        // onErrorResumeNext
        new OnErrorResumeNext();
        // onExceptionResumeNext
        new OnExceptionResumeNext();
        // retry
        new Retry();
        // retryWhen
        new RetryWhen();
        // repeatWhen
        new RepeatWhen();
    }
}
