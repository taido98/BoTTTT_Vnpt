package com.qlvb.vnpt.botttt.schedule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

class TrinhKyModel {
    @Getter
    @Setter
    private String stt;

    @Getter @Setter
    private String donVi;

    @Getter @Setter
    private String nguoiGui;

    @Getter @Setter
    private String trangThai;

    @Getter @Setter
    private String ngayKy;

    @Getter @Setter
    private String ngayGui;

    public TrinhKyModel(String stt, String donVi, String nguoiGui, String trangThai, String ngayKy, String ngayGui) {
        this.stt = stt;
        this.donVi = donVi;
        this.nguoiGui = nguoiGui;
        this.trangThai = trangThai;
        this.ngayKy = ngayKy;
        this.ngayGui = ngayGui;
    }
}
