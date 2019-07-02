package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import android.util.Log;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListDocumentView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListFileRelatedView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ListFileRelatedPresenterImpl implements ListFileRelatedPresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    ListFileRelatedView listDocumentView;

    @Override
    public void setView(ListFileRelatedView view) {
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
    public void getListDocument(String id) {
        subscription.add(documentInteractor.getlistFileRelated(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListFileRelatedResponse>>() {
                    @Override
                    public void call(Response<ListFileRelatedResponse> response) {
//                        String url = response.raw().request().url().toString();
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            Log.d("@@@@@@@@",response.body().getData()  + "@@@ "  );
                            listDocumentView.onListFileRelatedSuccess(response.body().getData());
                        } else {
                            listDocumentView.onListFileRelatedFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        listDocumentView.onListFileRelatedError(e);
                    }
                }));
    }
}