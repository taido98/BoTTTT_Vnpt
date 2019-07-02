package com.qlvb.vnpt.botttt.schedule.ui.view;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;

import java.util.List;


public interface UserView extends View{
    void onGetListUserSuccess(List<UserObject> dataResponse);
    void onGetListFailed(String message);

    void onGetListUnitSuccess(List<UnitObject> dataResponse);
    void onGetListGroupSuccess(List<GroupObject> dataResponse);
    void onGetListMinisterSuccess(List<MinisterObject> dataResponse);

    void onGetListError(Throwable e);

}
