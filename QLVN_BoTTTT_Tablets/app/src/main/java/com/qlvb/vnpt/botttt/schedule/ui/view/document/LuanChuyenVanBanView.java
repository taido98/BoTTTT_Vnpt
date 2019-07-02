package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LuanChuyenVanBanView extends View {
    void onLuanChuyenDocumnetSuccess(ChuyenVanBanResponse dataResponse);
    void onLuanChuyenDocumentFailed(String message);
    void onLuanChuyenDocumentError(Throwable e);
}
