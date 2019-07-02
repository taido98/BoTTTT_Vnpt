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

public class GroupObject extends RealmObject {
    @Expose
    @Getter
    @Setter
    @SerializedName("parentId")
    private String parentId;
    @Expose
    @Getter
    @Setter
    @SerializedName("name")
    private String name;
    @PrimaryKey
    @Expose
    @Getter
    @Setter
    @SerializedName("id")
    private String id;
    @Expose
    @Getter
    @Setter
    public String isCheckProcess;

}
