package com.qlvb.vnpt.botttt.schedule.domain.model.request;

import com.google.gson.annotations.SerializedName;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.InstallFileView;

import lombok.Getter;
import lombok.Setter;

public class SignRequest {
    @SerializedName("comment")
    @Setter @Getter
    private String comment;

    @SerializedName("docId")
    @Setter @Getter
    private String docId;

    @SerializedName("type")
    @Setter @Getter
    private Integer type;

    public SignRequest(String comment, String docId, Integer type) {
        this.comment = comment;
        this.docId = docId;
        this.type = type;
    }
}
