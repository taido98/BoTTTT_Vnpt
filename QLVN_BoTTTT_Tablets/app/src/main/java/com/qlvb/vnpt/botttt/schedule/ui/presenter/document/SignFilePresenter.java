package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.SignDocumentView;

public interface SignFilePresenter extends Presenter<SignDocumentView> {
    void signFile(SignRequest request);
}