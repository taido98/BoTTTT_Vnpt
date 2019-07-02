package com.qlvb.vnpt.botttt.schedule.domain.interactor.user;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;
import com.qlvb.vnpt.botttt.schedule.domain.repository.api.UserApi;

import javax.inject.Inject;

import rx.Observable;


public class MainInteractorImpl implements MainInteractor {
    @Inject
    UserApi userApi;

    @Override
    public Observable<GetCountResponse> executeGetCountResult(String token) {
        return userApi.getCount(token);
    }

    @Override
    public Observable<GetCountResponse> executeGetLogOut() {
        return userApi.getLogOutUser();
    }
}
