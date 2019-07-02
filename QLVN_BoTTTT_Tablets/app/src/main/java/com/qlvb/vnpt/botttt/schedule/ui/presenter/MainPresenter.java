package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.MainView;

/**
 * Created by THAOPX on 8/28/2018.
 */
public interface MainPresenter extends Presenter<MainView>{
    void getCount(String token);
    void getLogUser();
}
