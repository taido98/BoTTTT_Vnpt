package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 9/6/2018.
 */

public class GetMinisterObject {
    @Expose
    @Getter
    @Setter
    @SerializedName("data")
    private List<MinisterObject> data;
    @Expose
    @Getter
    @Setter
    @SerializedName("status")
    private Status status;


    public static class Status {
        @Expose
        @Getter
        @Setter
        @SerializedName("message")
        private String message;
        @Expose
        @Getter
        @Setter
        @SerializedName("code")
        private String code;

    }
}
