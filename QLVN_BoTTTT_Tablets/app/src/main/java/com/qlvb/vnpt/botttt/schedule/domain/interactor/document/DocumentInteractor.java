package com.qlvb.vnpt.botttt.schedule.domain.interactor.document;

import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.LuanChuyenDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.LuuVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.SignResponse;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

public interface DocumentInteractor {
    Observable<Response<ListDocumentResponse>> getListDocument(ListDocumentRequest request);
    Observable<Response<DetailDocumentResponse>> getDetailDocument(String id);
    Observable<Response<ChuyenVanBanResponse>> getSendDocument(String value, SendDocumentRequest sendDocumentRequest);
    Observable<Response<ChuyenVanBanResponse>> luanChuyenDocument(String value, LuanChuyenDocumentRequest luanChuyenDocumentRequest);
    Observable<ResponseBody> installFile(int id);
    Observable<Response<SignResponse>> signFile(SignRequest request);
    Observable<Response<LuuVanBanResponse>> saveFile(String token, String id, Integer type);
    Observable<Response<ListFileRelatedResponse>> getlistFileRelated(String id);
}
