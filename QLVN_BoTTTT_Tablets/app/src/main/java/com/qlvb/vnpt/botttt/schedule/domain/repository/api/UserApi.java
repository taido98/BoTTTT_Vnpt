package com.qlvb.vnpt.botttt.schedule.domain.repository.api;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetCountResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetListUnitResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetLoginResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetMinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.LoginRequest;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by linhl on 4/13/2018.
 */

public interface UserApi {
    @GET("/qlvbldcv/api/user/getlistuser/")
    Observable<GetlistUserResult> getListUser();

    @GET("/qlvbldcv/api/minister/getgroup/")
    Observable<GetGroupResult> getListGroup();

    @GET("/qlvbldcv/api/unit/getlistunit/")
    Observable<GetListUnitResult> getListUnit();

    @GET("/qlvbldcv/api/logout/")
    Observable<GetCountResponse> getLogOutUser();

    @GET("/qlvbldcv/api/minister/getminister/")
    Observable<GetMinisterObject> getListMinister();

    @GET("/qlvbldcv/api/unit/getlistunitsuggest/")
    Observable<GetListUnitResult> getListUnitSuggest();

    @GET("/qlvbldcv/api/user/getlistusersuggest/")
    Observable<GetlistUserResult> getListUsersuggest();


    @POST("/qlvbldcv/api/login/v2/")
    Observable<GetLoginResult> getLogin(@Body LoginRequest loginRequest);

    @GET("/qlvbldcv/api/minister/countdocument/")
    Observable<GetCountResponse> getCount(
            @Header("x-authentication-token") String token
    );

//    @FormUrlEncoded
//    @POST(ServiceUrl.POST_SUBMIT_REGISTER)
//    Observable<Response<CommonApiResult>> submitRegisterApi(@Field("token") String token,
//                                                            @Field("otp") String otp);
//
//    // get history rank
//    @GET(ServiceUrl.GET_HISTORY_RANK)
//    Observable<Response<HistoryRankResponse>> getHistoryRank(@Query("Tokenhoivien") String Tokenhoivien);

}
