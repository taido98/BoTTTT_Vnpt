package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 9/5/2018.
 */
public class GetCountResponse  {
    @Expose
    @Getter @Setter
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
    @SerializedName("data")
    @Getter @Setter
    private Data data;

    public static class Data {
        @Expose
        @Getter
        @Setter
        @SerializedName("vanBanDi")
        private String vanBanDi;
        @Expose
        @Getter
        @Setter
        @SerializedName("diDaKyDuyet")
        private String diDaKyDuyet;
        @Expose
        @Getter
        @Setter
        @SerializedName("diTuChoiKy")
        private String diTuChoiKy;
        @Expose
        @Getter
        @Setter
        @SerializedName("diTatCa")
        private String diTatCa;

        @Expose
        @Getter
        @Setter
        @SerializedName("vanBanDen")
        private String vanBanDen;
        @Expose
        @Getter
        @Setter
        @SerializedName("denDaLuu")
        private String denDaLuu;
        @Expose
        @Getter
        @Setter
        @SerializedName("denDaXuLy")
        private String denDaXuLy;
        @Expose
        @Getter
        @Setter
        @SerializedName("denTatCa")
        private String denTatCa;

    }
}
