package org.cloud.demo0baseframework.utils;

import org.cloud.demo0baseframework.model.http.exception.ApiException;
import org.cloud.demo0baseframework.model.http.responese.DoubanResponse;
import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/7
 */

public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<DoubanResponse<T>, T> handleResult() {
        return new FlowableTransformer<DoubanResponse<T>, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<DoubanResponse<T>> doubanResponse) {
                return doubanResponse.flatMap(new Function<DoubanResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(@NonNull DoubanResponse<T> tDoubanResponse) throws Exception {
                        if (tDoubanResponse.getTotal()>0) {
                            return createData(tDoubanResponse.getSubjects());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

}
