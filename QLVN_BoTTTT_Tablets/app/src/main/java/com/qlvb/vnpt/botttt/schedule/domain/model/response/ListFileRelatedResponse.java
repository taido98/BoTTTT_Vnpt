package com.qlvb.vnpt.botttt.schedule.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ListFileRelatedResponse {
    @SerializedName("status")
    @Expose
    @Setter @Getter
    public Status status;

    @SerializedName("data")
    @Expose
    @Setter @Getter
    public List<Data> data = null;

    public class Status {

        @SerializedName("code")
        @Expose
        @Setter @Getter
        public String code;

        @SerializedName("message")
        @Expose
        @Setter @Getter
        public String message;

    }

    public class Data {
        @SerializedName("id")
        @Expose
        @Setter @Getter
        public Integer id;

        @SerializedName("name")
        @Expose
        @Setter @Getter
        public String name;

        @SerializedName("attachmentId")
        @Expose
        @Setter @Getter
        public Integer attachmentId;

        @SerializedName("creator")
        @Expose
        @Setter @Getter
        public String creator;
    }
}
