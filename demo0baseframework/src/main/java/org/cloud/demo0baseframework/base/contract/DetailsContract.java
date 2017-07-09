package org.cloud.demo0baseframework.base.contract;

import org.cloud.demo0baseframework.base.BasePresenter;
import org.cloud.demo0baseframework.base.BaseView;
import org.cloud.demo0baseframework.model.bean.MovieDetailsBean;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/20
 */

public interface DetailsContract {

    interface View extends BaseView {

        void showContent(MovieDetailsBean movieDetailsBean);

        void overRefresh();
    }

    interface Presenter extends BasePresenter<View> {

        void getMovieDetails(long id);

    }
}
