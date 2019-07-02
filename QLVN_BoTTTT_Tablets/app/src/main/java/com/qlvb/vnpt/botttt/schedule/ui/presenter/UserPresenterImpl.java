package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetListUnitResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetMinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class UserPresenterImpl implements UserPresenter {
    @Inject
    UserInteractor userInteractor;
    private CompositeSubscription subscription;
    UserView userView;
    @Override
    public void setView(UserView view) {
        userView = view;
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
    public void getListUser() {
        subscription.add(userInteractor.executeUserResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetlistUserResult>() {
                    @Override
                    public void call(GetlistUserResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListUserSuccess(response.getData());
                        } else {
                            userView.onGetListFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        userView.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListUnit() {
        subscription.add(userInteractor.executeUnitResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetListUnitResult>() {
                    @Override
                    public void call(GetListUnitResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListUnitSuccess(response.getData());
                        } else {
                            userView.onGetListFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        userView.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListGroup() {
        subscription.add(userInteractor.executeGroupResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetGroupResult>() {
                    @Override
                    public void call(GetGroupResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListGroupSuccess(response.getData());
                        } else {
                            userView.onGetListFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        userView.onGetListError(e);
                    }
                }));
    }

    @Override
    public void getListMinister() {
        subscription.add(userInteractor.executeMinisterResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetMinisterObject>() {
                    @Override
                    public void call(GetMinisterObject response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListMinisterSuccess(response.getData());
                        } else {
                            userView.onGetListFailed(response.getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        userView.onGetListError(e);
                    }
                }));
    }
}