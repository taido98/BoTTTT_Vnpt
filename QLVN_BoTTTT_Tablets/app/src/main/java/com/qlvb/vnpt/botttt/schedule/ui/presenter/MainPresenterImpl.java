package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.MainInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetLoginResult;
import com.qlvb.vnpt.botttt.schedule.ui.view.LoginView;
import com.qlvb.vnpt.botttt.schedule.ui.view.MainView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainPresenterImpl implements MainPresenter {
    @Inject
    MainInteractor mainInteractor;
    private CompositeSubscription subscription;
    MainView mainView;
    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
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
    public void getCount(String token) {
        subscription.add(mainInteractor.executeGetCountResult(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetCountResponse>() {
                    @Override
                    public void call(GetCountResponse response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            mainView.onGetCountSuccess(response.getData());
                        } else {
                            mainView.onGetCountFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        mainView.onGetCountError(e);
                    }
                }));
    }

    @Override
    public void getLogUser() {
        subscription.add(mainInteractor.executeGetLogOut()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetCountResponse>() {
                    @Override
                    public void call(GetCountResponse response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            mainView.onLogOutSuccess();
                        } else {
                            mainView.onGetCountFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        mainView.onGetCountError(e);
                    }
                }));
    }
}