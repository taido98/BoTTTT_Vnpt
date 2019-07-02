package com.qlvb.vnpt.botttt.schedule.domain.model.request;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ListDocumentRequest {
    @SerializedName("pageNo")
    @Setter @Getter
    private String pageNo;

    @SerializedName("pageRec")
    @Setter @Getter
    private String pageRec;

    @SerializedName("Param")
    @Setter @Getter
    private String param;

    @SerializedName("type")
    @Setter @Getter
    private String type;

    public ListDocumentRequest(String pageNo, String pageRec, String param, String type) {
        this.pageNo = pageNo;
        this.pageRec = pageRec;
        this.param = param;
        this.type = type;
    }
}
