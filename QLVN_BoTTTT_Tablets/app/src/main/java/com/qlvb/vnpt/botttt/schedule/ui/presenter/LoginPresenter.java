package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.LoginView;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface LoginPresenter extends Presenter<LoginView>{
    void getLogin(String username, String password, String tokenFireBase);
}
