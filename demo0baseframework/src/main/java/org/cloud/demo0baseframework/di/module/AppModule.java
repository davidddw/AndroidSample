package org.cloud.demo0baseframework.di.module;

import org.cloud.demo0baseframework.app.App;
import org.cloud.demo0baseframework.model.DataManager;
import org.cloud.demo0baseframework.model.db.DBHelper;
import org.cloud.demo0baseframework.model.db.RealmHelper;
import org.cloud.demo0baseframework.model.http.HttpHelper;
import org.cloud.demo0baseframework.model.http.RetrofitHelper;
import org.cloud.demo0baseframework.model.prefs.PreferencesHelper;
import org.cloud.demo0baseframework.model.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author d05660ddw
 * @version 1.0 2017/6/19
 */
@Module
public class AppModule {
    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
