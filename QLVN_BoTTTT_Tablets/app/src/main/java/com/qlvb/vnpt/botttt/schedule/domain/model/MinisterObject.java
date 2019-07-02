package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 9/6/2018.
 */

public class MinisterObject extends RealmObject {
    @Expose
    @Getter
    @Setter
    @SerializedName("stt")
    private int stt;
    @Expose
    @Getter
    @Setter
    @SerializedName("unitName")
    private String unitName;
    @Expose
    @Getter
    @Setter
    @SerializedName("positionName")
    private String positionName;
    @Expose
    @Getter
    @Setter
    @SerializedName("fullName")
    private String fullName;
    @PrimaryKey
    @Expose
    @Getter
    @Setter
    @SerializedName("userId")
    private String userId;
    @Expose
    @Getter
    @Setter
    public String isCheckProcess;
}
