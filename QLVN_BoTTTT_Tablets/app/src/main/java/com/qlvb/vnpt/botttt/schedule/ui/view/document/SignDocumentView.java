package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.SignResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;


public interface SignDocumentView extends View {
    void onSignDocumentSuccess(SignResponse.Data response);
    void onSignDocumentFailed(String message);
    void onSignDocumentError(Throwable e);
}
