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
package org.cloud.demo0baseframework.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.cloud.demo0baseframework.R;
import org.cloud.demo0baseframework.base.BaseActivity;
import org.cloud.demo0baseframework.base.contract.DetailsContract;
import org.cloud.demo0baseframework.model.bean.Cast;
import org.cloud.demo0baseframework.model.bean.MovieDetailsBean;
import org.cloud.demo0baseframework.ui.presenter.MovieDetailsPresenter;
import org.cloud.demo0baseframework.utils.GlideHelper;
import org.cloud.demo0baseframework.utils.StringFormatUtil;
import org.cloud.demo0baseframework.utils.SystemUtil;

import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MovieDetailsActivity extends BaseActivity<MovieDetailsPresenter> implements DetailsContract.View {

    private static final String ARG_REPOSITORY = "arg_repository";

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar_douban_detail)
    Toolbar toolbarDoubanDetail;

    @BindView(R.id.img_item_bg)
    ImageView ivBaseTitlebarBg;
    @BindView(R.id.iv_one_photo)
    ImageView ivOnePhoto;
    @BindView(R.id.tv_one_rating_rate)
    TextView tvOneRatingRate;
    @BindView(R.id.tv_one_rating_number)
    TextView tvOneRatingNumber;
    @BindView(R.id.tv_one_genres)
    TextView tvOneGenres;
    @BindView(R.id.tv_one_day)
    TextView tvOneDay;
    @BindView(R.id.tv_one_city)
    TextView tvOneCity;
    @BindView(R.id.tv_formerly)
    TextView tvFormerly;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.tv_movie_top_detail)
    TextView tvMovieTopDetail;

    public static void startWithRepository(long subjectId, Activity startingActivity) {
        Intent intent = new Intent(startingActivity, MovieDetailsActivity.class);
        intent.putExtra(ARG_REPOSITORY, subjectId);
        startingActivity.startActivity(intent);
    }

    /**
     * 加载titlebar背景
     */
    private void setImgHeaderBg(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            // 高斯模糊背景  参数：12,5
            Glide.with(this).load(imgUrl)
                    .error(R.mipmap.stackblur_default)
                    .bitmapTransform(new BlurTransformation(this, 12, 5)).into(ivBaseTitlebarBg);
        }
    }

    @Override
    public void showContent(MovieDetailsBean data) {
        toolbarDoubanDetail.setTitle(data.getTitle());
        toolbarDoubanDetail.setSubtitleTextColor(Color.WHITE);
        setImgHeaderBg(data.getImages().getMedium());
        GlideHelper.loadMovieTopImg(ivOnePhoto, data.getImages().getLarge());
        tvOneRatingRate.setText("评分：" + data.getRating().getAverage());
        tvOneGenres.setText("类型：" + StringFormatUtil.formatGenres(data.getGenres()));
        tvOneDay.setText("上映日期：" + data.getYear());
        tvFormerly.setText("原名：" + data.getOriginal_title());
        tvOneRatingNumber.setText(data.getRatings_count() + "人评分");
        tvOneCity.setText("制作国家/地区：" + data.getCountries() + "");
        tvMovieTopDetail.setText(data.getSummary());
    }

    @Override
    public void overRefresh() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    protected void initEventAndData() {
        long subjectId = getIntent().getLongExtra(ARG_REPOSITORY, 0);
        mPresenter.getMovieDetails(subjectId);
        setSupportActionBar(toolbarDoubanDetail);
        toolbarDoubanDetail.setTitle("");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }
        toolbarDoubanDetail.setTitleTextColor(Color.WHITE);
        toolbarDoubanDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        toolbarDoubanDetail.setTitleTextAppearance(this, R.style.ToolBar_Title);
//        toolbarDoubanDetail.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);
//        toolbarDoubanDetail.inflateMenu(R.menu.base_header_menu);
        toolbarDoubanDetail.setOverflowIcon(getResources().getDrawable(R.mipmap.actionbar_more));
//        toolbarDoubanDetail.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.actionbar_more:// 更多信息
//                        setTitleClickMore();
//                        break;
//                }
//                return true;
//            }
//        });

        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        //mToolbar.setVisibility(View.INVISIBLE);
        progressbar.setVisibility(android.view.View.VISIBLE);
    }
}
