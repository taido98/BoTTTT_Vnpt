package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.LuuVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LuuVanBanView extends View {
    void onSaveDocumnetSuccess(LuuVanBanResponse dataResponse);
    void onSaveDocumentFailed(String message);
    void onSaveDocumentError(Throwable e);

    void onCancelSaveDocumnetSuccess(LuuVanBanResponse dataResponse);
    void onCancelSaveDocumentFailed(String message);
    void onCancelSaveDocumentError(Throwable e);
}
