package com.qlvb.vnpt.botttt.schedule.domain.model.request;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 9/6/2018.
 */
public class SendDocumentRequest {
    @SerializedName("comment")
    @Setter
    @Getter
    private String comment;

    @SerializedName("docId")
    @Setter @Getter
    private String docId;

    @SerializedName("unitCoeval")
    @Setter @Getter
    private String unitCoeval;

    @SerializedName("unitPrimary")
    @Setter @Getter
    private String unitPrimary;

    @SerializedName("unitProcess")
    @Setter @Getter
    private String unitProcess;

    @SerializedName("userCoeval")
    @Setter @Getter
    private String userCoeval;

    @SerializedName("userPrimary")
    @Setter @Getter
    private String userPrimary;

    @SerializedName("userProcess")
    @Setter @Getter
    private String userProcess;

}
