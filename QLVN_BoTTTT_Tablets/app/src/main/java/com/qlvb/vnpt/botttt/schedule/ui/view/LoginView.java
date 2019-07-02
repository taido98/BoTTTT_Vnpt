package com.qlvb.vnpt.botttt.schedule.ui.view;

import com.qlvb.vnpt.botttt.schedule.domain.model.LoginObject;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LoginView extends View{
    void onGetloginSuccess(LoginObject dataResponse);
    void onGetLoginFailed(String message);
    void onGetLoginError(Throwable e);
}
