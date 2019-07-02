package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetListUnitResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetMinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.UserSuggestView;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class UserSuggestPresenterImpl implements UserSuggestPresenter {
    @Inject
    UserInteractor userInteractor;
    private CompositeSubscription subscription;
    UserSuggestView userView;
    @Override
    public void setView(UserSuggestView view) {
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
    public void getListUserSuggest() {
        subscription.add(userInteractor.executeUserSuggestResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetlistUserResult>() {
                    @Override
                    public void call(GetlistUserResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListUserSuggestSuccess(response.getData());
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
    public void getListUnitSuggest() {
        subscription.add(userInteractor.executeUnitSuggestResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetListUnitResult>() {
                    @Override
                    public void call(GetListUnitResult response) {
                        if (response.getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            userView.onGetListUnitSuggestSuccess(response.getData());
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