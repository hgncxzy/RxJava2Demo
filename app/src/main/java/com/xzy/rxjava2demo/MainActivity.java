package com.xzy.rxjava2demo;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

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
import com.xzy.rxjava2demo.operations.FromIterable;
import com.xzy.rxjava2demo.operations.Interval;
import com.xzy.rxjava2demo.operations.Just;
import com.xzy.rxjava2demo.operations.Map;
import com.xzy.rxjava2demo.operations.Merge;
import com.xzy.rxjava2demo.operations.Skip;
import com.xzy.rxjava2demo.operations.Take;
import com.xzy.rxjava2demo.operations.Zip;

import io.reactivex.disposables.CompositeDisposable;


/**
 * test RxJava2
 *
 * @author xzy
 */
public class MainActivity extends AppCompatActivity {
    private TextView resultTv;
    private ScrollView scrollView;
    private StringBuilder sb = new StringBuilder();
    public static CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler();
    }

    private void clearResult() {
        sb.delete(0, sb.length());
        resultTv.setText("");
    }

    private void handler() {
        // RxJava2 基本用法
        findViewById(R.id.btn1).setOnClickListener(v -> {
            clearResult();
            Basic basic = new Basic();
            basic.toScribe();
        });
        // just
        findViewById(R.id.btn2).setOnClickListener(v -> {
            clearResult();
            Just just = new Just();
            just.testJust();
        });
        // fromArray
        findViewById(R.id.btn3).setOnClickListener(v -> {
            clearResult();
            FromArray fromArray = new FromArray();
            fromArray.testFromArray();
        });
        // fromIterable
        findViewById(R.id.btn3_1).setOnClickListener(v -> {
            clearResult();
            FromIterable fromIterable = new FromIterable();
            fromIterable.testFromIterable();
        });
        // interval
        findViewById(R.id.btn4).setOnClickListener(v -> {
            clearResult();
            Interval interval = new Interval();
            interval.testInterval();
        });
        // map
        findViewById(R.id.btn5).setOnClickListener(v -> {
            clearResult();
            Map map = new Map();
            map.testMap();
        });
        // flatMap
        findViewById(R.id.btn6).setOnClickListener(v -> {
            clearResult();
            FlatMap flatMap = new FlatMap();
            flatMap.testFlatMap();
        });
        // concatMap
        findViewById(R.id.btn7).setOnClickListener(v -> {
            clearResult();
            ConcatMap concatMap = new ConcatMap();
            concatMap.testConcatMap();
        });
        // compose
        findViewById(R.id.btn8).setOnClickListener(v -> {
            clearResult();
            Compose compose = new Compose();
            compose.testCompose();
        });
        // filter
        findViewById(R.id.btn9).setOnClickListener(v -> {
            clearResult();
            Filter filter = new Filter();
            filter.testFilter();
        });
        // distinct
        findViewById(R.id.btn10).setOnClickListener(v -> {
            clearResult();
            Distinct distinct = new Distinct();
            distinct.testDistinct();
        });
        // buffer
        findViewById(R.id.btn11).setOnClickListener(v -> {
            clearResult();
            Buffer buffer = new Buffer();
            buffer.testBuffer();
        });
        // skip
        findViewById(R.id.btn12).setOnClickListener(v -> {
            clearResult();
            Skip skip = new Skip();
            skip.testSkip();
        });
        // take
        findViewById(R.id.btn13).setOnClickListener(v -> {
            clearResult();
            Take take = new Take();
            take.testTake();
        });
        // merge
        findViewById(R.id.btn14).setOnClickListener(v -> {
            clearResult();
            Merge merge = new Merge();
            merge.testMerge();
        });
        // concat
        findViewById(R.id.btn15).setOnClickListener(v -> {
            clearResult();
            Concat concat = new Concat();
            concat.testConcat();
        });
        // zip
        findViewById(R.id.btn16).setOnClickListener(v -> {
            clearResult();
            Zip zip = new Zip();
            zip.testZip();
        });

        // concatEager
        findViewById(R.id.btn17).setOnClickListener(v -> {
            clearResult();
            ConcatEager concatEager = new ConcatEager();
            concatEager.testConcatEager();
        });
        // onErrorReturn
        findViewById(R.id.btn18).setOnClickListener(v -> {
            clearResult();
            new OnErrorReturn();
        });
        // onErrorResumeNext
        findViewById(R.id.btn19).setOnClickListener(v -> {
            clearResult();
            new OnErrorResumeNext();
        });
        // onExceptionResumeNext
        findViewById(R.id.btn20).setOnClickListener(v -> {
            clearResult();
            new OnExceptionResumeNext();
        });
        // retry
        findViewById(R.id.btn21).setOnClickListener(v -> {
            clearResult();
            new Retry().test1();
        });
        findViewById(R.id.btn22).setOnClickListener(v -> {
            clearResult();
            new Retry().test2();
        });
        // retryWhen
        findViewById(R.id.btn23).setOnClickListener(v -> {
            clearResult();
            new RetryWhen();
        });
        // repeatWhen
        findViewById(R.id.btn24).setOnClickListener(v -> {
            clearResult();
            new RepeatWhen();
        });
    }

    private void initView() {
        resultTv = findViewById(R.id.tvResult);
        scrollView = findViewById(R.id.sv_result);
        MyLogger.INSTANCE.registerObserver(this, (aBoolean, s) -> {
            sb.append(s).append("\n");
            setResultText(sb.toString());
            return null;
        });
    }

    private void setResultText(String resultText) {
        resultTv.setText(resultText);
        resultTv.post(() -> scrollView.smoothScrollBy(0, 1000));
    }
}
