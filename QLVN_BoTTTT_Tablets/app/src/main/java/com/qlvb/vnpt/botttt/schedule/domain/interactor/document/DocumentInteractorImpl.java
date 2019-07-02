package com.qlvb.vnpt.botttt.schedule.domain.interactor.document;

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
import com.qlvb.vnpt.botttt.schedule.domain.repository.api.DocumentApi;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

public class DocumentInteractorImpl implements DocumentInteractor{
    @Inject
    DocumentApi documentApi;

    @Override
    public Observable<Response<ListDocumentResponse>> getListDocument(ListDocumentRequest request) {
        return documentApi.getListDocument(request);
    }

    @Override
    public Observable<Response<DetailDocumentResponse>> getDetailDocument(String id) {
        return documentApi.getDetailDocument(id);
    }

    @Override
    public Observable<Response<ChuyenVanBanResponse>> getSendDocument(String value, SendDocumentRequest sendDocumentRequest) {
        return documentApi.sendDocument(value, sendDocumentRequest);
    }

    @Override
    public Observable<Response<ChuyenVanBanResponse>> luanChuyenDocument(String value, LuanChuyenDocumentRequest luanChuyenDocumentRequest) {
        return documentApi.luanChuyenVanBan(value, luanChuyenDocumentRequest);
    }

    @Override
    public Observable<ResponseBody> installFile(int id) {
        return documentApi.downloadFile(id);
    }

    @Override
    public Observable<Response<SignResponse>> signFile(SignRequest request) {
        return documentApi.signFile(request);
    }

    @Override
    public Observable<Response<LuuVanBanResponse>> saveFile(String token, String id, Integer type) {
        return documentApi.saveFile(token, id, type);
    }

    @Override
    public Observable<Response<ListFileRelatedResponse>> getlistFileRelated(String id) {
        return documentApi.listFileRelated(id);
    }
}
