package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface ChuyenVanBanView extends View {
    void onSendDocumnetSuccess(ChuyenVanBanResponse dataResponse);
    void onSendDocumentFailed(String message);
    void onSendDocumentError(Throwable e);
}
