package com.qlvb.vnpt.botttt.schedule.app.evenBus;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;

import java.util.List;

import io.realm.RealmList;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 9/5/2018.
 */

public class EventListDialogSelect {
    @Getter
    @Setter
    List<UnitObject> resultsUnitChecked;
    @Getter
    @Setter
    List<UserObject> resultsUserChecked;
    @Getter
    @Setter
    List<GroupObject> resultsGroupChecked;
    @Getter
    @Setter
    List<MinisterObject> resultsMinisterChecked;
    @Getter
    @Setter
    String idSelect;
}
