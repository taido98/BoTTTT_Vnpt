package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

import java.util.List;

public interface ListDocumentView extends View {
    void onGetListDocumentSuccess(List<ListDocumentResponse.Datum> dataResponse);
    void onGetListDocumentFailed(String message);
    void onGetListDocumentError(Throwable e);
}
