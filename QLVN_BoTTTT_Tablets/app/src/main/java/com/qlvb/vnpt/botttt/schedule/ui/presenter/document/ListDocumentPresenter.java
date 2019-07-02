package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListDocumentView;

public interface ListDocumentPresenter extends Presenter<ListDocumentView> {
    void getListDocument(ListDocumentRequest request);
}