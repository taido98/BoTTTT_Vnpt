package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.InstallFileView;

public interface InstallFilePresenter extends Presenter<InstallFileView> {
    void getFile(int id);
}