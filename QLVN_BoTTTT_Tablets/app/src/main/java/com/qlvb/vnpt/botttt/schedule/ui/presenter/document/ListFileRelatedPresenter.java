package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListFileRelatedView;

public interface ListFileRelatedPresenter extends Presenter<ListFileRelatedView> {
    void getListDocument(String id);
}
