package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;


public interface UserPresenter extends Presenter<UserView> {
    void getListUser();
    void getListUnit();
    void getListGroup();
    void getListMinister();


}

