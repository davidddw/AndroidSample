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
package org.cloud.demo0baseframework.model.http;

import org.cloud.demo0baseframework.model.bean.MovieDetailsBean;
import org.cloud.demo0baseframework.model.bean.MovieInfoBean;
import org.cloud.demo0baseframework.model.http.api.DoubanApis;
import org.cloud.demo0baseframework.model.http.responese.DoubanResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/7
 */

public class RetrofitHelper implements HttpHelper {

    private DoubanApis mDoubanApis;

    @Inject
    public RetrofitHelper(DoubanApis doubanApis) {
        mDoubanApis = doubanApis;
    }

    @Override
    public Flowable<DoubanResponse<List<MovieInfoBean>>> getComingSoonMovie(int start, int count) {
        return mDoubanApis.getComingSoonMovie(start, count);
    }

    @Override
    public Flowable<DoubanResponse<List<MovieInfoBean>>> getInTheatersMovie(String city, int start, int count) {
        return mDoubanApis.getInTheatersMovie(city, start, count);
    }

    @Override
    public Flowable<DoubanResponse<List<MovieInfoBean>>> getTop250Movie(int start, int count) {
        return mDoubanApis.getTopMovie(start, count);
    }

    @Override
    public Flowable<MovieDetailsBean> getMovieDetails(long customId) {
        return mDoubanApis.getMovieDetails(customId);
    }
}
