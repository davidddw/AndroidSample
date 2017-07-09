package org.cloud.demo0baseframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.cloud.demo0baseframework.app.App;
import org.cloud.demo0baseframework.di.component.DaggerFragmentComponent;
import org.cloud.demo0baseframework.di.component.FragmentComponent;
import org.cloud.demo0baseframework.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    protected abstract void initInject();
}
