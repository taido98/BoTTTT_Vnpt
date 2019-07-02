package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.LuuVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ChuyenVanBanView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuuVanBanView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class LuuVanBanPresenterImpl implements LuuVanBanPresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    LuuVanBanView luuVanBanView;

    @Override
    public void setView(LuuVanBanView luuVanBanView) {
        this.luuVanBanView = luuVanBanView;
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
    public void saveDocument(String token, String id, Integer type) {
        subscription.add(documentInteractor.saveFile(token, id, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<LuuVanBanResponse>>() {
                    @Override
                    public void call(Response<LuuVanBanResponse> response) {
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            luuVanBanView.onSaveDocumnetSuccess(response.body());
                        } else {
                            luuVanBanView.onSaveDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        luuVanBanView.onSaveDocumentError(e);
                    }
                }));
    }

    @Override
    public void cancelSaveDocument(String token, String id, Integer type) {
        subscription.add(documentInteractor.saveFile(token, id, type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<LuuVanBanResponse>>() {
                    @Override
                    public void call(Response<LuuVanBanResponse> response) {
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            luuVanBanView.onCancelSaveDocumnetSuccess(response.body());
                        } else {
                            luuVanBanView.onCancelSaveDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        luuVanBanView.onCancelSaveDocumentError(e);
                    }
                }));
    }
}