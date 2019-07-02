package com.qlvb.vnpt.botttt.schedule.ui.presenter.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.LuanChuyenDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.Presenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuanChuyenVanBanView;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LuanChuyenVanBanPresenter extends Presenter<LuanChuyenVanBanView>{
    void luanChuyenDocument(String value, LuanChuyenDocumentRequest luanChuyenDocumentRequest);
}
