package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetLoginResult;
import com.qlvb.vnpt.botttt.schedule.ui.view.LoginView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenterImpl implements LoginPresenter {
    @Inject
    UserInteractor userInteractor;
    private CompositeSubscription subscription;
    LoginView loginView;
    @Override
    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {
        subscription.unsubscribe();

    }

    @Override
    public void getLogin(String username, String password, String tokenFireBase) {
        subscription.add(userInteractor.getLoginResult(username, password, tokenFireBase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetLoginResult>() {
                    @Override
                    public void call(GetLoginResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            loginView.onGetloginSuccess(response.getData());
                        } else {
                            loginView.onGetLoginFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        loginView.onGetLoginError(e);
                    }
                }));
    }
}