package org.cloud.demo0baseframework.di.module;

import android.app.Activity;

import org.cloud.demo0baseframework.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/19
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
