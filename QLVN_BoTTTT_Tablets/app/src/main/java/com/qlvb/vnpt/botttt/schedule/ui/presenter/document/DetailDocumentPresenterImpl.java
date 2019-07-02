package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.DetailDocumentView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListDocumentView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailDocumentPresenterImpl implements DetailDocumentPresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    DetailDocumentView detailDocumentView;

    @Override
    public void setView(DetailDocumentView view) {
        detailDocumentView = view;
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
    public void getDetailDocument(String id) {
        subscription.add(documentInteractor.getDetailDocument(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<DetailDocumentResponse>>() {
                    @Override
                    public void call(Response<DetailDocumentResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            detailDocumentView.onDetailDocumentSuccess(response.body().getData());
                        } else {
                            detailDocumentView.onDetailDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        detailDocumentView.onDetailDocumentError(e);
                    }
                }));
    }
}
