/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 d05660@163.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.cloud.demo0baseframework.ui.presenter;

import org.cloud.demo0baseframework.base.RxPresenter;
import org.cloud.demo0baseframework.base.contract.MovieListContract;
import org.cloud.demo0baseframework.model.DataManager;
import org.cloud.demo0baseframework.model.bean.MovieInfoBean;
import org.cloud.demo0baseframework.model.http.responese.DoubanResponse;
import org.cloud.demo0baseframework.utils.CommonSubscriber;
import org.cloud.demo0baseframework.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public class MovieListPresenter extends RxPresenter<MovieListContract.View> implements MovieListContract.Presenter {

    private DataManager mDataManager;
    //private int PAGE_COUNT = 1;
    private int pageSize = 10;
    private int pageIndex = 1;
    private List<MovieInfoBean> mDatas = new ArrayList<>();

    @Inject
    public MovieListPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    private void getDatas(int categoryId, int pageIndex) {

        int startPage = (pageIndex - 1) * pageSize;
        switch (categoryId) {
            case 100:
                apiSubscribe(mDataManager.getInTheatersMovie("jilin", startPage, pageSize), startPage == 0);
                break;
            case 101:
                apiSubscribe(mDataManager.getComingSoonMovie(startPage, pageSize), startPage == 0);
                break;
            case 102:
                apiSubscribe(mDataManager.getTop250Movie(startPage, pageSize), startPage == 0);
                break;
        }
    }

    private void apiSubscribe(Flowable<DoubanResponse<List<MovieInfoBean>>> flowable, final boolean reset) {
        addSubscribe(flowable
                .compose(RxUtil.<DoubanResponse<List<MovieInfoBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<MovieInfoBean>>handleResult())
                .subscribeWith(new CommonSubscriber<List<MovieInfoBean>>(mView) {
                    @Override
                    public void onNext(List<MovieInfoBean> movieInfoBean) {
                        mDatas = movieInfoBean;
                        if (mView != null) {
                            if (reset) {
                                mView.setNewData(movieInfoBean);
                            } else {
                                mView.addNewData(movieInfoBean);
                            }
                            mView.overRefresh();
                        }

                    }
                })
        );
    }


    @Override
    public void getNewDatas(int categoryId) {
        int pageIndex = 1;
        getDatas(categoryId, pageIndex);
        if (mView != null) {
            mView.setRefreshEnabled(true);
            mView.setLoadMoreEnabled(true);
        }
    }

    @Override
    public void getMoreDatas(int categoryId) {
        if (mDatas.size() != 0) {
            pageIndex++;
            getDatas(categoryId, pageIndex);
        } else {
            if (mView != null) {
                mView.showErrorMsg("没有更多数据了");
                mView.overRefresh();
                mView.setLoadMoreEnabled(false);
            }
        }
    }
}
