package org.cloud.demo0baseframework.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import org.cloud.demo0baseframework.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/19
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
