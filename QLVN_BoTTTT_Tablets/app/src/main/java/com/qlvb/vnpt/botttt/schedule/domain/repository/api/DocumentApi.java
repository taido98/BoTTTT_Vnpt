package com.qlvb.vnpt.botttt.schedule.domain.repository.api;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.LuanChuyenDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.LuuVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.SignResponse;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface DocumentApi {
    @POST("/qlvbldcv/api/minister/getlistdocument/")
    Observable<Response<ListDocumentResponse>> getListDocument(@Body ListDocumentRequest request);

    @GET("/qlvbldcv/api/minister/getdetaildocument/{id}/") //
    Observable<Response<DetailDocumentResponse>> getDetailDocument(@Path("id") String id);

    @POST("/qlvbldcv/api/minister/tranferdocument/")    //
    Observable<Response<ChuyenVanBanResponse>> sendDocument(@Header("x-authentication-token") String token,
                                                            @Body SendDocumentRequest sendDocumentRequest);

    @POST("/qlvbldcv/api/minister/rotatedocument/")
    Observable<Response<ChuyenVanBanResponse>> luanChuyenVanBan(@Header("x-authentication-token") String token,
                                                            @Body LuanChuyenDocumentRequest luanChuyenDocumentRequest);

    // tải file
    @GET("/qlvbldcv/api/file/download/{id}/")
    Observable<ResponseBody> downloadFile(@Path("id") int id);

    // sign unsign file
    @POST("/qlvbldcv/api/minister/signdocument/")
    Observable<Response<SignResponse>> signFile(@Body SignRequest request);

    // luu file
    @GET("/qlvbldcv/api/minister/savedocument/{id}/{type}/")
    Observable<Response<LuuVanBanResponse>> saveFile(
            @Header("x-authentication-token") String token,
            @Path("id") String id,
            @Path("type") Integer type);

    // list file related
    @GET("/qlvbldcv/api/minister/getallfile/{id}/")
    Observable<Response<ListFileRelatedResponse>> listFileRelated(@Path("id") String id);
}
