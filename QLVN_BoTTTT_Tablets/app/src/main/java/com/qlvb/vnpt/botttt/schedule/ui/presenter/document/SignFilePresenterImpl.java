package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.SignResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.SignDocumentView;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SignFilePresenterImpl implements SignFilePresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    SignDocumentView signDocumentView;

    @Override
    public void setView(SignDocumentView view) {
        signDocumentView = view;
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
    public void signFile(SignRequest request) {
        subscription.add(documentInteractor.signFile(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<SignResponse>>() {
                    @Override
                    public void call(Response<SignResponse> response) {
                        String url = response.raw().request().url().toString();
                        if (response.body().getStatus().getCode().equals(StatusCode.RESPONSE_ERROR_CODE_0)) {
                            signDocumentView.onSignDocumentSuccess(response.body().getData());
                        } else {
                            signDocumentView.onSignDocumentFailed(response.body().getStatus().getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        signDocumentView.onSignDocumentError(e);
                    }
                }));
    }
}
