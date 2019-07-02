package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.user.UserInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListDocumentView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ListDocumentPresenterImpl implements ListDocumentPresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    ListDocumentView listDocumentView;

    @Override
    public void setView(ListDocumentView view) {
        listDocumentView = view;
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
    public void getListDocument(ListDocumentRequest request) {
        subscription.add(documentInteractor.getListDocument(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListDocumentResponse>>() {
                    @Override
                    public void call(Response<ListDocumentResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            listDocumentView.onGetListDocumentSuccess(response.body().getData());
                        } else {
                            listDocumentView.onGetListDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        listDocumentView.onGetListDocumentError(e);
                    }
                }));
    }
}