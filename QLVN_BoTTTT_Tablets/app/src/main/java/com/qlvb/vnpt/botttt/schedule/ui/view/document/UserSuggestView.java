package com.qlvb.vnpt.botttt.schedule.ui.view.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;
import com.qlvb.vnpt.botttt.schedule.ui.view.View;

import java.util.List;


public interface UserSuggestView extends View{
    void onGetListUserSuggestSuccess(List<UserObject> dataResponse);
    void onGetListFailed(String message);

    void onGetListUnitSuggestSuccess(List<UnitObject> dataResponse);

    void onGetListError(Throwable e);

}
