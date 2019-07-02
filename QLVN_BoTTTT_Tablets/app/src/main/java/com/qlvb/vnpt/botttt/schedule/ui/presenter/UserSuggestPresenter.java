package com.qlvb.vnpt.botttt.schedule.ui.presenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.UserSuggestView;


public interface UserSuggestPresenter extends Presenter<UserSuggestView> {

    void getListUserSuggest();
    void getListUnitSuggest();


}

