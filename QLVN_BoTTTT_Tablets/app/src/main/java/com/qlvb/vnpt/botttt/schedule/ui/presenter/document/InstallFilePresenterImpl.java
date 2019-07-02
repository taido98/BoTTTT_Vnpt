package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;
import com.qlvb.vnpt.botttt.schedule.app.utils.StatusCode;
import com.qlvb.vnpt.botttt.schedule.domain.interactor.document.DocumentInteractor;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.InstallFileView;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class InstallFilePresenterImpl implements InstallFilePresenter {
    @Inject
    DocumentInteractor documentInteractor;
    private CompositeSubscription subscription;
    InstallFileView installFileView;

    @Override
    public void setView(InstallFileView view) {
        installFileView = view;
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
    public void getFile(int id) {
        subscription.add(documentInteractor.installFile(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody object) {
                        installFileView.onInstallFileSuccess(object);

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        installFileView.onInstallFileError(e);
                    }
                }));
    }
}