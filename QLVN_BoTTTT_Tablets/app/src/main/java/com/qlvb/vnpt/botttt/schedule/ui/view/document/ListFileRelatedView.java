package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

import java.util.List;

public interface ListFileRelatedView extends View {
    void onListFileRelatedSuccess(List<ListFileRelatedResponse.Data> data);
    void onListFileRelatedFailed(String message);
    void onListFileRelatedError(Throwable e);
}
