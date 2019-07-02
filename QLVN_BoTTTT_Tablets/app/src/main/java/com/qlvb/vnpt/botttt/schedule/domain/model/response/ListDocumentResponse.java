package com.qlvb.vnpt.botttt.schedule.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class ListDocumentResponse {
    @SerializedName("status")
    @Expose
    @Getter @Setter
    public Status status;

    @SerializedName("data")
    @Expose
    @Getter @Setter
    public List<Datum> data = null;

    public class Status {
        @SerializedName("code")
        @Expose
        @Getter @Setter
        public String code;

        @SerializedName("message")
        @Expose
        @Getter @Setter
        public String message;

    }

    public static class Datum {
        @SerializedName("id")
        @Expose
        @Getter @Setter
        public String id;

        @SerializedName("soKyHieu")
        @Expose
        @Getter @Setter
        public String soKyHieu;

        @SerializedName("trichYeu")
        @Expose
        @Getter @Setter
        public String trichYeu;

        @SerializedName("ngayNhan")
        @Expose
        @Getter @Setter
        public String ngayNhan;

        @SerializedName("type")
        @Expose
        @Getter @Setter
        public String type;
    }

}


