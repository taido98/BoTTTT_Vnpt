package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 8/28/2018.
 */

public class UserObject extends RealmObject {
    @Expose
    @SerializedName("unitId")
    @Getter
    @Setter
    public String unitId;
    @Expose
    @SerializedName("jobPosition")
    @Getter
    @Setter
    public String jobPosition;
    @Expose
    @SerializedName("name")
    @Getter
    @Setter
    public String name;
    @PrimaryKey
    @Expose
    @SerializedName("userId")
    @Getter
    @Setter
    public String userId;
    @SerializedName("updateDate")
    @Getter
    @Setter
    public String updateDate;
    @Expose
    @Getter
    @Setter
    public String isCheckProcess;
}
