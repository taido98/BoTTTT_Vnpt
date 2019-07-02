package com.qlvb.vnpt.botttt.schedule.app.di;

import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractorImpl;
import com.qlvb.vnpt.botttt.schedule.domain.repository.api.UserApi;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserSuggestPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserSuggestPresenterImpl;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListDocumentPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListDocumentPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by MinhDN on 18/4/2018.
 */
@Module(complete = false, library = true)
public class UserModule {
    @Provides
    UserInteractor provideUserInteractor(UserInteractorImpl userInteractor) {
        return userInteractor;
    }

    @Provides
    UserSuggestPresenter provideUserInteractor(UserSuggestPresenterImpl userInteractor) {
        return userInteractor;
    }

    @Provides
    UserApi provideRegisterApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build().create(UserApi.class);
    }

}
