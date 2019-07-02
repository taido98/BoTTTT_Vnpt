package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 8/28/2018.
 */

public class LoginRequest extends RealmObject {

    @Expose
    @Getter
    @Setter
    @SerializedName("password")
    public String password;
    @Expose
    @SerializedName("tokenFireBase")
    @Getter
    @Setter
    public String tokenFireBase;
    @Expose
    @Getter
    @Setter
    @SerializedName("username")
    public String username;

}
