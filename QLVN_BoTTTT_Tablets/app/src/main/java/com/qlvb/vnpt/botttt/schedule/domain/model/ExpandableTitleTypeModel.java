package com.qlvb.vnpt.botttt.schedule.domain.model;

import lombok.Getter;
import lombok.Setter;

public class ExpandableTitleTypeModel {
    @Getter
    @Setter
    private String title;

    @Getter @Setter
    private Integer type;

    public ExpandableTitleTypeModel(String title, Integer type) {
        this.title = title;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
