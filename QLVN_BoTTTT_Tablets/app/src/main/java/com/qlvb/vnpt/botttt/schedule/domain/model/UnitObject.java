package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 8/28/2018.
 */

public class UnitObject extends RealmObject {

    @Expose
    @Getter
    @Setter
    @SerializedName("parentId")
    public String parentId;
    @Expose
    @SerializedName("name")
    @Getter
    @Setter
    public String name;
    @Expose
    @SerializedName("updateDate")
    @Getter
    @Setter
    public String updateDate;
    @PrimaryKey
    @Expose
    @Getter
    @Setter
    @SerializedName("id")
    public String id;

    @Expose
    @Getter
    @Setter
    public RealmList<UnitObject> listChildren;

    @Expose
    @Getter
    @Setter
    public boolean isTrace;
    @Expose
    @Getter
    @Setter
    public String isCheckProcess;
}
