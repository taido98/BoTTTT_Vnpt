package com.qlvb.vnpt.botttt.schedule.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by linhl on 9/3/2018.
 */

public class UnitRecursiveObject {
    @Expose
    @Getter
    @Setter
    public String parentId;
    @Expose
    @Getter
    @Setter
    public String name;
    @Expose
    @Getter
    @Setter
    public String id;

    @Expose
    @Getter
    @Setter
    public List<UnitRecursiveObject> listChildren;

    @Expose
    @Getter
    @Setter
    public boolean isTrace;
}

