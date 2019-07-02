package com.qlvb.vnpt.botttt.schedule.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 9/6/2018.
 */
public class ChuyenVanBanResponse {
    @Expose
    @Getter
    @Setter
    @SerializedName("status")
    private Status status;

    public static class Status {
        @Expose
        @Getter @Setter
        @SerializedName("message")
        private String message;
        @Expose
        @Getter @Setter
        @SerializedName("code")
        private String code;
    }

    @Expose
    @Getter
    @Setter
    @SerializedName("data")
    private String data;
}
