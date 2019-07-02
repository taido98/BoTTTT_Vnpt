package com.qlvb.vnpt.botttt.schedule.domain.interactor.user;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetListUnitResult;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetLoginResult;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetMinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.LoginRequest;
import com.qlvb.vnpt.botttt.schedule.domain.repository.api.UserApi;

import javax.inject.Inject;

import rx.Observable;


public class UserInteractorImpl implements UserInteractor {
    @Inject
    UserApi userApi;

    @Override
    public Observable<GetlistUserResult> executeUserResult() {
        return userApi.getListUser();
    }

    @Override
    public Observable<GetlistUserResult> executeUserSuggestResult() {
        return userApi.getListUsersuggest();
    }

    @Override
    public Observable<GetListUnitResult> executeUnitSuggestResult() {
        return userApi.getListUnitSuggest();
    }

    @Override
    public Observable<GetListUnitResult> executeUnitResult() {
        return userApi.getListUnit();
    }

    @Override
    public Observable<GetGroupResult> executeGroupResult() {
        return userApi.getListGroup();
    }

    @Override
    public Observable<GetMinisterObject> executeMinisterResult() {
        return userApi.getListMinister();
    }


    @Override
    public Observable<GetLoginResult> getLoginResult(String username, String password, String tokenFireBase) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setTokenFireBase(tokenFireBase);
        loginRequest.setUsername(username);
        return userApi.getLogin(loginRequest);
    }
}
