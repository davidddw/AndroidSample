package org.cloud.demo0baseframework.base.contract;

import org.cloud.demo0baseframework.base.BasePresenter;
import org.cloud.demo0baseframework.base.BaseView;
import org.cloud.demo0baseframework.model.bean.MovieInfoBean;

import java.util.List;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public interface MovieListContract {

    interface View extends BaseView {

        void addNewData(List<MovieInfoBean> mList);

        void setNewData(List<MovieInfoBean> mList);

        void overRefresh();

        void setRefreshEnabled(boolean flag);

        void setLoadMoreEnabled(boolean flag);

    }

    interface Presenter extends BasePresenter<MovieListContract.View> {

        void getNewDatas(int start);

        void getMoreDatas(int start);

    }
}
