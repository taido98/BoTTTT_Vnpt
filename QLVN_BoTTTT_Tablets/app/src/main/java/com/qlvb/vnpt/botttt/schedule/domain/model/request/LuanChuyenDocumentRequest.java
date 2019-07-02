package com.qlvb.vnpt.botttt.schedule.domain.model.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 9/6/2018.
 */
public class LuanChuyenDocumentRequest {
    @SerializedName("comment")
    @Setter
    @Getter
    private String comment;

    @SerializedName("docId")
    @Setter @Getter
    private String docId;

    @SerializedName("userPrimary")
    @Setter @Getter
    private String userPrimary;

    @SerializedName("userProcess")
    @Setter @Getter
    private String userProcess;



}
