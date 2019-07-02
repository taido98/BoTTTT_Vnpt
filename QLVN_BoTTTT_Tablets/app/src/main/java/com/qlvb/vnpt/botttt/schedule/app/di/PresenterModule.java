package com.qlvb.vnpt.botttt.schedule.app.di;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.LoginPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.LoginPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by linhl on 8/24/2018.
 */
@Module(complete = false, library = true)
public class PresenterModule {
    @Provides
    public UserPresenter provideUserPresenter(UserPresenterImpl impl) {
        return impl;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(LoginPresenterImpl impl) {
        return impl;
    }
}
