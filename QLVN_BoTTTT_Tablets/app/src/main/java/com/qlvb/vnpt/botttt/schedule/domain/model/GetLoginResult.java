package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by linhl on 8/28/2018.
 */

public class GetLoginResult {

    @Expose
    @SerializedName("data")
    private LoginObject data;
    @Expose
    @SerializedName("status")
    private Status status;

    public LoginObject getData() {
        return data;
    }

    public void setData(LoginObject data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Status {
        @Expose
        @SerializedName("message")
        private String message;
        @Expose
        @SerializedName("code")
        private String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
