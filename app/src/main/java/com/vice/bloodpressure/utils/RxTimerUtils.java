package com.vice.bloodpressure.utils;


import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 *
 */
public class RxTimerUtils {
    private Disposable mDisposable;

    /**
     * milliseconds毫秒后执行指定动作
     *
     * @param milliSeconds
     * @param rxAction
     */
    public void timer(long milliSeconds, final RxAction rxAction) {
        Observable.timer(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            rxAction.action(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //取消订阅
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        //取消订阅
                        cancel();
                    }
                });
    }

    /**
     * 每隔milliseconds毫秒后执行指定动作
     *
     * @param milliSeconds
     * @param rxAction
     */
    public void interval(long milliSeconds, final RxAction rxAction) {
        Observable.interval(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            rxAction.action(number);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancel();

                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });
    }

    /**
     * 在指定的开始和结束时间范围内，每隔milliSeconds毫秒执行一次指定动作
     *
     * @param startTime 指定开始时间（单位：毫秒）
     * @param endTime   指定结束时间（单位：毫秒）
     * @param milliSeconds 执行间隔时间（单位：毫秒）
     * @param rxAction    执行的动作
     */
    public void intervalInRange(long startTime, long endTime, long milliSeconds, final RxAction rxAction) {
        // 确保endTime > startTime，且计算合理的count值
        if (startTime >= endTime) {
            throw new IllegalArgumentException("endTime must be greater than startTime.");
        }

        long duration = endTime - startTime;
        long count = duration / milliSeconds; // 计算循环次数

        // 考虑到可能有余数，确保最后一次执行也能包含进去
        if (duration % milliSeconds != 0) {
            count++;
        }

        Observable.intervalRange(0, count, startTime, milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {
                        mDisposable = disposable;
                    }

                    @Override
                    public void onNext(@NonNull Long number) {
                        if (rxAction != null) {
                            // number是从0开始的计数器，不是实际的时间戳
                            rxAction.action(startTime + (number * milliSeconds)); // 计算并传递实际时间戳
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        cancel();
                    }
                });
    }


    /**
     * 取消订阅
     */
    public void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public interface RxAction {
        /**
         * 让调用者指定指定动作
         *
         * @param number
         */
        void action(long number);
    }
}