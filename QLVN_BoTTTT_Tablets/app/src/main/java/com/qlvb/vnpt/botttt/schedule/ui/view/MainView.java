package com.qlvb.vnpt.botttt.schedule.ui.view;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;

/**
 * Created by THAOPX on 9/5/2018.
 */
public interface MainView extends View {
    void onGetCountSuccess(GetCountResponse.Data dataResponse);
    void onLogOutSuccess();
    void onGetCountFailed(String message);
    void onGetCountError(Throwable e);
}
