package com.qlvb.vnpt.botttt.schedule.domain.interactor.user;


import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetListUnitResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetLoginResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetMinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;

import rx.Observable;

/**
 * Created by linhl on 4/13/2018.
 */

public interface UserInteractor {
    Observable<GetlistUserResult> executeUserResult();
    Observable<GetlistUserResult> executeUserSuggestResult();

    Observable<GetListUnitResult> executeUnitSuggestResult();
    Observable<GetListUnitResult> executeUnitResult();
    Observable<GetGroupResult> executeGroupResult();
    Observable<GetMinisterObject> executeMinisterResult();

    Observable<GetLoginResult> getLoginResult(String username, String password, String tokenFireBase );
}
