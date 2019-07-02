package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.DetailDocumentView;


public interface DetailDocumentPresenter extends Presenter<DetailDocumentView> {
    void getDetailDocument(String id);
}