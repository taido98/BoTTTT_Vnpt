package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 8/28/2018.
 */

public class LoginObject extends RealmObject {
    @Expose
    @SerializedName("username")
    @Setter
    @Getter
    public String username;
    @Expose
    @SerializedName("password")
    @Setter
    @Getter
    public String password;
    @Expose
    @SerializedName("agent")
    @Setter
    @Getter
    public String agent;
    @Expose
    @Setter
    @Getter
    @SerializedName("agentInfo")
    public String agentInfo;
    @Expose
    @Setter
    @Getter
    @SerializedName("fullName")
    public String fullName;
    @Expose
    @Setter
    @Getter
    @SerializedName("unitName")
    public String unitName;
    @Expose
    @Setter
    @Getter
    @SerializedName("token")
    public String token;
    @Expose
    @Setter
    @Getter
    @SerializedName("unitId")
    public String unitId;
}
