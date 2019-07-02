package com.qlvb.vnpt.botttt.schedule.ui.presenter;


import com.qlvb.vnpt.botttt.schedule.ui.view.View;

public interface Presenter<T extends View> {

    void setView(T view);

    void onViewCreate();

    void onViewStart();

    void onViewResume();

    void onViewPause();

    void onViewStop();

    void onViewDestroy();

}