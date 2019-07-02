package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.ExpandableTitleTypeModel;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;

import java.util.HashMap;
import java.util.List;

public class DetailExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ExpandableTitleTypeModel> expandableListTitle;
    private HashMap<String, List<?>> expandableListDetail;



    // 4 Child types
    private static final int CHILD_TYPE_1 = 1;
    private static final int CHILD_TYPE_2 = 2;
    private static final int CHILD_TYPE_3 = 3;
    private static final int CHILD_TYPE_4 = 4;
    private static final int CHILD_TYPE_5 = 5;
    private static final int CHILD_TYPE_UNDEFINED = 0;

    public DetailExpandableListAdapter(Context context, List<ExpandableTitleTypeModel> expandableListTitle,
                                       HashMap<String, List<?>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getTitle())
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderType1 holderType1 = null;
        ViewHolderType2 holderType2 = null;
        ViewHolderType3 holderType3 = null;
        ViewHolderType4 holderType4 = null;
        ViewHolderType5 holderType5 = null;

        if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                switch (expandableListTitle.get(groupPosition).getType()){

                    case CHILD_TYPE_UNDEFINED:
                        break;
                    case CHILD_TYPE_1:
                        holderType1 = new ViewHolderType1();
                        convertView = layoutInflater.inflate(R.layout.item_doc_type_1, null);
                        convertView.setTag(holderType1);
                        holderType1.tv_doc_name = convertView.findViewById(R.id.tv_doc_name);
                        holderType1.tv_doc_desc = convertView.findViewById(R.id.tv_doc_desc);
                        holderType1.img_file = convertView.findViewById(R.id.img_file);
                        break;
                    case CHILD_TYPE_2:
                        holderType2 = new ViewHolderType2();
                        convertView = layoutInflater.inflate(R.layout.item_doc_type_2, null);
                        convertView.setTag(holderType2);
                        holderType2.tv_noi_dung = convertView.findViewById(R.id.tv_noi_dung);
                        holderType2.tv_date = convertView.findViewById(R.id.tv_date);

                        break;
                    case CHILD_TYPE_3:
                        holderType3 = new ViewHolderType3();
                        convertView = layoutInflater.inflate(R.layout.item_doc_type_3, null);
                        convertView.setTag(holderType3);

                        holderType3.tv_hinh_thuc = convertView.findViewById(R.id.tv_hinh_thuc);
                        holderType3.tv_do_mat = convertView.findViewById(R.id.tv_do_mat);
                        holderType3.tv_do_khan = convertView.findViewById(R.id.tv_do_khan);
                        holderType3.tv_linh_vuc = convertView.findViewById(R.id.tv_linh_vuc);
                        holderType3.tv_time_soan_thao = convertView.findViewById(R.id.tv_time_soan_thao);
                        holderType3.tv_phong_trinh_ky = convertView.findViewById(R.id.tv_phong_trinh_ky);
                        holderType3.tv_don_vi = convertView.findViewById(R.id.tv_don_vi);
                        holderType3.tv_noi_nhan = convertView.findViewById(R.id.tv_noi_nhan);
                        break;
                    case CHILD_TYPE_4:
                        holderType4 = new ViewHolderType4();
                        convertView = layoutInflater.inflate(R.layout.item_doc_type_4, null);
                        convertView.setTag(holderType4);

                        holderType4.tv_don_vi = convertView.findViewById(R.id.tv_don_vi);
                        holderType4.tv_nguoi_gui = convertView.findViewById(R.id.tv_nguoi_gui);
                        holderType4.tv_noi_dung_xu_ly = convertView.findViewById(R.id.tv_noi_dung_xu_ly);
                        holderType4.tv_nguoi_nhan = convertView.findViewById(R.id.tv_nguoi_nhan);
                        holderType4.tv_ngay_xu_ly = convertView.findViewById(R.id.tv_ngay_xu_ly);
                        break;
                    case CHILD_TYPE_5:
                        holderType5 = new ViewHolderType5();
                        convertView = layoutInflater.inflate(R.layout.layout_thongtin_congvan, null);
                        convertView.setTag(holderType5);

                        holderType5.tv_so_ky_hieu = convertView.findViewById(R.id.tv_so_ky_hieu);
                        holderType5.tv_co_quan_ban_hanh = convertView.findViewById(R.id.tv_co_quan_ban_hanh);
                        holderType5.tv_hinh_thuc = convertView.findViewById(R.id.tv_hinh_thuc);
                        holderType5.tv_do_mat = convertView.findViewById(R.id.tv_do_mat);
                        holderType5.tv_do_khan = convertView.findViewById(R.id.tv_do_khan);
                        holderType5.tv_linh_vuc = convertView.findViewById(R.id.tv_linh_vuc);
                        holderType5.tv_ngay_van_ban = convertView.findViewById(R.id.tv_ngay_van_ban);
                        holderType5.tv_ngay_den = convertView.findViewById(R.id.tv_ngay_den);
                        holderType5.tv_hinh_thuc_gui = convertView.findViewById(R.id.tv_hinh_thuc_gui);

                        break;
                    default:
                        break;
                }

        }else {
            switch (expandableListTitle.get(groupPosition).getType()){
                case CHILD_TYPE_UNDEFINED:
                    break;
                case CHILD_TYPE_1:
                    holderType1 = (ViewHolderType1) convertView.getTag();
                    break;
                case CHILD_TYPE_2:
                    holderType2 = (ViewHolderType2) convertView.getTag();
                    break;
                case CHILD_TYPE_3:
                    holderType3 = (ViewHolderType3) convertView.getTag();
                    break;
                case CHILD_TYPE_4:
                    holderType4 = (ViewHolderType4) convertView.getTag();
                    break;
                case CHILD_TYPE_5:
                    holderType5 = (ViewHolderType5) convertView.getTag();
                    break;
                default:
                    break;
            }

        }

        // fill data
        switch (expandableListTitle.get(groupPosition).getType()){
            case CHILD_TYPE_UNDEFINED:
                break;
            case CHILD_TYPE_1:
                try {
                    DetailDocumentResponse.FileDinhKem expandedFileDinhKem = (DetailDocumentResponse.FileDinhKem) getChild(groupPosition, childPosition);
                    if(holderType1 != null){
                        holderType1.tv_doc_name.setText(expandedFileDinhKem.getName());
                        holderType1.tv_doc_desc.setText(expandedFileDinhKem.getCreator());
                        checkFileType(expandedFileDinhKem.getName(), holderType1.img_file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case CHILD_TYPE_2:
                try {
                    DetailDocumentResponse.VanBanLienQuan expandedFileLienQuan = (DetailDocumentResponse.VanBanLienQuan) getChild(groupPosition, childPosition);
                    if(holderType2 != null){
                        holderType2.tv_noi_dung.setText(expandedFileLienQuan.getName());
                        holderType2.tv_date.setText(expandedFileLienQuan.getSoKyHieu());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CHILD_TYPE_3:
                try {
                    DetailDocumentResponse.ThongTinVanBan expandedThongTinVanBan = (DetailDocumentResponse.ThongTinVanBan) getChild(groupPosition, childPosition);
                    if(holderType3 != null){

                        if(expandedThongTinVanBan.getHinhThuc() != null){
                            holderType3.tv_hinh_thuc.setText(expandedThongTinVanBan.getHinhThuc());
                        }
                        if(expandedThongTinVanBan.getDoMat() != null){
                            holderType3.tv_do_mat.setText(expandedThongTinVanBan.getDoMat());
                        }
                        if(expandedThongTinVanBan.getDoKhan() != null){
                            holderType3.tv_do_khan.setText(expandedThongTinVanBan.getDoKhan());
                        }
                        if(expandedThongTinVanBan.getLinhVuc() != null){
                            holderType3.tv_linh_vuc.setText(expandedThongTinVanBan.getLinhVuc());
                        }
                        if(expandedThongTinVanBan.getNgaySoanThao() != null){
                            holderType3.tv_time_soan_thao.setText(expandedThongTinVanBan.getNgaySoanThao());
                        }
                        if(expandedThongTinVanBan.getPbTrinhKy() != null){
                            holderType3.tv_phong_trinh_ky.setText(expandedThongTinVanBan.getPbTrinhKy());
                        }
                        if(expandedThongTinVanBan.getDonVi() != null){
                            holderType3.tv_don_vi.setText(expandedThongTinVanBan.getDonVi());
                        }
                        if(expandedThongTinVanBan.getNoiNhan() != null){
                            holderType3.tv_noi_nhan.setText(expandedThongTinVanBan.getNoiNhan());
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case CHILD_TYPE_4:
                try {
                    DetailDocumentResponse.TongHopXuLy expandedTongHopXuLy = (DetailDocumentResponse.TongHopXuLy) getChild(groupPosition, childPosition);
                    if(holderType4 != null) {

                        holderType4.tv_don_vi.setText(expandedTongHopXuLy.getDonVi());
                        holderType4.tv_nguoi_gui.setText(expandedTongHopXuLy.getNguoiGui());
                        holderType4.tv_noi_dung_xu_ly.setText(expandedTongHopXuLy.getNoiDungXuLy());
                        holderType4.tv_nguoi_nhan.setText(expandedTongHopXuLy.getNguoiNhan());
                        holderType4.tv_ngay_xu_ly.setText(expandedTongHopXuLy.getThoiGianXuLy());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case CHILD_TYPE_5:
                try {
                    DetailDocumentResponse.ThongTinVanBan expandedThongTinCongVan = (DetailDocumentResponse.ThongTinVanBan) getChild(groupPosition, childPosition);
                    if(holderType5 != null){

                        if(expandedThongTinCongVan.getSoKyHieu() != null){
                            holderType5.tv_so_ky_hieu.setText(expandedThongTinCongVan.getSoKyHieu());
                        }
                        if(expandedThongTinCongVan.getCoQuanBanHanh() != null){
                            holderType5.tv_co_quan_ban_hanh.setText(expandedThongTinCongVan.getCoQuanBanHanh());
                        }
                        if(expandedThongTinCongVan.getHinhThuc() != null){
                            holderType5.tv_hinh_thuc.setText(expandedThongTinCongVan.getHinhThuc());
                        }
                        if(expandedThongTinCongVan.getDoMat() != null){
                            holderType5.tv_do_mat.setText(expandedThongTinCongVan.getDoMat());
                        }
                        if(expandedThongTinCongVan.getDoKhan() != null){
                            holderType5.tv_do_khan.setText(expandedThongTinCongVan.getDoKhan());
                        }
                        if(expandedThongTinCongVan.getLinhVuc() != null){
                            holderType5.tv_linh_vuc.setText(expandedThongTinCongVan.getLinhVuc());
                        }
                        if(expandedThongTinCongVan.getNgayVanBan() != null){
                            holderType5.tv_ngay_van_ban.setText(expandedThongTinCongVan.getNgayVanBan());
                        }
                        if(expandedThongTinCongVan.getNgayDen() != null){
                            holderType5.tv_ngay_den.setText(expandedThongTinCongVan.getNgayDen());
                        }
                        if(expandedThongTinCongVan.getHinhThucGui() != null){
                            holderType5.tv_hinh_thuc_gui.setText(expandedThongTinCongVan.getHinhThucGui());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition).getTitle())
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition).toString();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expend_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public int getChildTypeCount() {
        return 6;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        switch (expandableListTitle.get(groupPosition).getType()){
            case CHILD_TYPE_UNDEFINED:
                return CHILD_TYPE_UNDEFINED;
            case CHILD_TYPE_1:
                return CHILD_TYPE_1;
            case CHILD_TYPE_2:
                return CHILD_TYPE_2;
            case CHILD_TYPE_3:
                return CHILD_TYPE_3;
            case CHILD_TYPE_4:
                return CHILD_TYPE_4;
            case CHILD_TYPE_5:
                return CHILD_TYPE_5;
            default:
                return CHILD_TYPE_UNDEFINED;
        }


    }

    static class ViewHolderType1 {
        TextView tv_doc_name, tv_doc_desc;
        ImageView img_file;

    }

    static class ViewHolderType2 {
        TextView tv_noi_dung, tv_date;
    }

    static class ViewHolderType3 {
        TextView tv_hinh_thuc, tv_do_mat, tv_do_khan, tv_linh_vuc;
        TextView tv_time_soan_thao, tv_phong_trinh_ky, tv_don_vi, tv_noi_nhan;
    }

    static class ViewHolderType4 {
        TextView tv_don_vi, tv_nguoi_gui, tv_noi_dung_xu_ly, tv_nguoi_nhan, tv_ngay_xu_ly;
    }

    static class ViewHolderType5 {
        TextView tv_so_ky_hieu, tv_co_quan_ban_hanh, tv_hinh_thuc, tv_do_mat, tv_do_khan;
        TextView tv_linh_vuc, tv_ngay_van_ban, tv_ngay_den, tv_hinh_thuc_gui;
    }

    private void checkFileType(String fileName, ImageView imageView){
        if (fileName.trim().toUpperCase().endsWith(AppDef.DOC) || fileName.trim().toUpperCase().endsWith(AppDef.DOCX)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_doc));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.XLS) || fileName.trim().toUpperCase().endsWith(AppDef.XLSX)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_xls));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PPT) || fileName.trim().toUpperCase().endsWith(AppDef.PPTX)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_ppt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PDF)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_pdf));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.ZIP)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_zip));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.RAR)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_rar));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.TXT)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_txt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.MPP)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_mpp));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.JPG)
                || fileName.trim().toUpperCase().endsWith(AppDef.JPEG)
                || fileName.trim().toUpperCase().endsWith(AppDef.PNG)
                || fileName.trim().toUpperCase().endsWith(AppDef.GIF)
                || fileName.trim().toUpperCase().endsWith(AppDef.TIFF)
                || fileName.trim().toUpperCase().endsWith(AppDef.BMP)) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_image));
        }
    }
}
