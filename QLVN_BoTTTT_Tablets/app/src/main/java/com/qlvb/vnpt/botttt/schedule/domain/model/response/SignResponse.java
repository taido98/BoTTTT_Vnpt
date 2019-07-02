package com.qlvb.vnpt.botttt.schedule.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class SignResponse {
    @SerializedName("status")
    @Expose
    @Getter @Setter
    public Status status;

    @SerializedName("data")
    @Expose
    @Getter @Setter
    public Data data;

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

    public class Data {
        @SerializedName("code")
        @Expose
        @Getter @Setter
        public String code;

        @SerializedName("message")
        @Expose
        @Getter @Setter
        public String message;
    }
}
