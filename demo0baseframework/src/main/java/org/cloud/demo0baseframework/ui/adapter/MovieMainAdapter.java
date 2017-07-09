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
package org.cloud.demo0baseframework.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.cloud.demo0baseframework.constants.Constants;
import org.cloud.demo0baseframework.model.bean.Channel;
import org.cloud.demo0baseframework.ui.fragment.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public class MovieMainAdapter extends FragmentStatePagerAdapter {

    private List<Channel> mChannelList = new ArrayList<>();
    private List<MovieListFragment> mFragments = new ArrayList<>();

    public MovieMainAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        for (int i = 0; i < Constants.DEFAULT_CHANNELS.length; i++) {
            Channel channel = new Channel();
            channel.setName(Constants.DEFAULT_CHANNELS[i]);
            channel.setId(i + 100);
            mChannelList.add(channel);
        }
        mFragments.clear();
        for (int i = 0; i < mChannelList.size(); i++) {
            mFragments.add(MovieListFragment.newInstance(mChannelList.get(i).getId()));
        }

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mChannelList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannelList.get(position).getName();
    }
}