package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.LuanChuyenDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ChuyenVanBanView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuanChuyenVanBanView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LuanChuyenVanBanPresenterImpl implements LuanChuyenVanBanPresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    LuanChuyenVanBanView luanChuyenVanBanView;

    @Override
    public void setView(LuanChuyenVanBanView luanChuyenVanBanView) {
        this.luanChuyenVanBanView = luanChuyenVanBanView;
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
    public void luanChuyenDocument(String value, LuanChuyenDocumentRequest luanChuyenDocumentRequest) {
        subscription.add(documentInteractor.luanChuyenDocument(value, luanChuyenDocumentRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ChuyenVanBanResponse>>() {
                    @Override
                    public void call(Response<ChuyenVanBanResponse> response) {
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            luanChuyenVanBanView.onLuanChuyenDocumnetSuccess(response.body());
                        } else {
                            luanChuyenVanBanView.onLuanChuyenDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        luanChuyenVanBanView.onLuanChuyenDocumentError(e);
                    }
                }));
    }
}