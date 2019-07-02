package com.qlvb.vnpt.botttt.schedule.domain.interactor.user;


import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;

import rx.Observable;

/**
 * Created by linhl on 4/13/2018.
 */

public interface MainInteractor {
    Observable<GetCountResponse> executeGetCountResult(String token);
    Observable<GetCountResponse> executeGetLogOut();

}
