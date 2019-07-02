package com.qlvb.vnpt.botttt.schedule.domain.model.response;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DetailDocumentResponse implements Serializable {
    @SerializedName("status")
    @Expose
    @Getter @Setter
    public Status status;

    @SerializedName("data")
    @Expose
    @Getter @Setter
    public Data data;

    public class Status {
        @SerializedName("code")
        @Expose
        @Getter @Setter
        public String code;

        @SerializedName("message")
        @Expose
        @Getter @Setter
        public String message;
    }

    public class Data {
        @SerializedName("thongTinVanBan")
        @Expose
        @Getter @Setter
        public ThongTinVanBan thongTinVanBan;

        @SerializedName("vanBanLienQuan")
        @Expose
        @Getter @Setter
        public List<VanBanLienQuan> vanBanLienQuan = null;

        @SerializedName("fileDinhKem")
        @Expose
        @Getter @Setter
        public List<FileDinhKem> fileDinhKem = null;

        @SerializedName("fileLienQuan")
        @Expose
        @Getter @Setter
        public List<FileLienQuan> fileLienQuan = null;

        @SerializedName("tongHopXuLy")
        @Expose
        @Getter @Setter
        public List<TongHopXuLy> tongHopXuLy = null;

    }

    public class FileDinhKem implements Serializable {
        @SerializedName("id")
        @Expose
        @Getter @Setter
        public Integer id;

        @SerializedName("name")
        @Expose
        @Getter @Setter
        public String name;

        @SerializedName("attachmentId")
        @Expose
        @Getter @Setter
        public Integer attachmentId;

        @SerializedName("creator")
        @Expose
        @Getter @Setter
        public String creator;
    }

    public class FileLienQuan {
        @SerializedName("id")
        @Expose
        @Getter @Setter
        public Integer id;

        @SerializedName("name")
        @Expose
        @Getter @Setter
        public String name;

        @SerializedName("attachmentId")
        @Expose
        @Getter @Setter
        public Integer attachmentId;

        @SerializedName("creator")
        @Expose
        @Getter @Setter
        public String creator;
    }

    public class Parameter {

        @SerializedName("fullName")
        @Expose
        @Getter @Setter
        public String fullName;

        @SerializedName("updateBy")
        @Expose
        @Getter @Setter
        public String updateBy;

        @SerializedName("updateDate")
        @Expose
        @Getter @Setter
        public String updateDate;

        @SerializedName("status")
        @Expose
        @Getter @Setter
        public String status;

        @SerializedName("comment")
        @Expose
        @Getter @Setter
        public String comment;

        @SerializedName("chuyenToi")
        @Expose
        @Getter @Setter
        public String chuyenToi;

        @SerializedName("action")
        @Expose
        @Getter @Setter
        public String action;

    }

    public class ThongTinVanBan {

        @SerializedName("id")
        @Expose
        @Getter @Setter
        public String id;

        @SerializedName("trichYeu")
        @Expose
        @Getter @Setter
        public String trichYeu;

        @SerializedName("ngayTrinhKy")
        @Expose
        @Getter @Setter
        public String ngayTrinhKy;

        @SerializedName("hinhThuc")
        @Expose
        @Getter @Setter
        public String hinhThuc;

        @SerializedName("ngaySoanThao")
        @Expose
        @Getter @Setter
        public String ngaySoanThao;

        @SerializedName("doMat")
        @Expose
        @Getter @Setter
        public String doMat;

        @SerializedName("pbTrinhKy")
        @Expose
        @Getter @Setter
        public String pbTrinhKy;

        @SerializedName("doKhan")
        @Expose
        @Getter @Setter
        public String doKhan;

        @SerializedName("donVi")
        @Expose
        @Getter @Setter
        public String donVi;

        @SerializedName("linhVuc")
        @Expose
        @Getter @Setter
        public String linhVuc;

        @SerializedName("noiNhan")
        @Expose
        @Getter @Setter
        public String noiNhan;

        @SerializedName("soKyHieu")
        @Expose
        @Getter @Setter
        public String soKyHieu;

        @SerializedName("coQuanBanHanh")
        @Expose
        @Getter @Setter
        public String coQuanBanHanh;

        @SerializedName("ngayVanBan")
        @Expose
        @Getter @Setter
        public String ngayVanBan;

        @SerializedName("ngayDen")
        @Expose
        @Getter @Setter
        public String ngayDen;

        @SerializedName("hinhThucGui")
        @Expose
        @Getter @Setter
        public String hinhThucGui;

    }

    public static class TongHopXuLy {

        @SerializedName("donVi")
        @Expose
        @Getter @Setter
        public String donVi;

        @SerializedName("nguoiGui")
        @Expose
        @Getter @Setter
        public String nguoiGui;

        @SerializedName("noiDungXuLy")
        @Expose
        @Getter @Setter
        public String noiDungXuLy;

        @SerializedName("nguoiNhan")
        @Expose
        @Getter @Setter
        public String nguoiNhan;

        @SerializedName("thoiGianXuLy")
        @Expose
        @Getter @Setter
        public String thoiGianXuLy;
    }

    public class VanBanLienQuan {

        @SerializedName("id")
        @Expose
        @Getter @Setter
        public String id;

        @SerializedName("name")
        @Expose
        @Getter @Setter
        public String name;

        @SerializedName("soDenDi")
        @Getter @Setter
        @Expose
        public String soDenDi;

        @SerializedName("soKyHieu")
        @Getter @Setter
        @Expose
        public String soKyHieu;

    }
}
