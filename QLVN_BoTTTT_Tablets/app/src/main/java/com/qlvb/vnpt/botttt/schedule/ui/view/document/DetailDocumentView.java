package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

import java.util.List;

public interface DetailDocumentView extends View {
    void onDetailDocumentSuccess(DetailDocumentResponse.Data dataResponse);
    void onDetailDocumentFailed(String message);
    void onDetailDocumentError(Throwable e);
}
