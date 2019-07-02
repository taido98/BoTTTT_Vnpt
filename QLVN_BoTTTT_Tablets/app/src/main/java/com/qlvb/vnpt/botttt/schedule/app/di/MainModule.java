package com.qlvb.vnpt.botttt.schedule.app.di;

import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.MainInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.MainInteractorImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.MainPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class MainModule {
    @Provides
    MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter) {
        return mainPresenter;
    }
    @Provides
    MainInteractor provideMainInteractor(MainInteractorImpl mainInteractor) {
        return mainInteractor;
    }

}
