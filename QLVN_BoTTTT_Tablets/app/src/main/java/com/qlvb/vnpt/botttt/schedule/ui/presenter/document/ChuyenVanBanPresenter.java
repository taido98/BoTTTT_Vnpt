package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ChuyenVanBanView;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface ChuyenVanBanPresenter extends Presenter<ChuyenVanBanView>{
    void sendDocument(String value, SendDocumentRequest sendDocumentRequest);
}
