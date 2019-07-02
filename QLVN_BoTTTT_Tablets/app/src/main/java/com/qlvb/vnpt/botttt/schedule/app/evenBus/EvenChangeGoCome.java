package com.qlvb.vnpt.botttt.schedule.app.evenBus;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by THAOPX on 9/4/2018.
 */
public class EvenChangeGoCome {
    @Getter
    @Setter
    private Integer st;
    public EvenChangeGoCome(Integer st){
        this.st = st;
    }
}
