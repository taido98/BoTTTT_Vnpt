package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.ui.activity.ViewPDFActivity;
import com.qlvb.vnpt.botttt.schedule.ui.activity.XemDanhSachTatCaFileActivity;

import java.io.Serializable;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import lombok.val;

public class DanhSachFileAdapter extends RecyclerView.Adapter<DanhSachFileAdapter.MyViewHolder> {
    private List<ListFileRelatedResponse.Data> datalist;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_doc_name, tv_doc_desc;
        public ImageView img_file;

        public MyViewHolder(View view) {
            super(view);
            tv_doc_name = itemView.findViewById(R.id.tv_doc_name);
            tv_doc_desc = itemView.findViewById(R.id.tv_doc_desc);
            img_file = itemView.findViewById(R.id.img_file);
            tv_doc_name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_doc_name:
                    Log.d("Thao", tv_doc_name+ "???????");
                    Intent intent = new Intent(mContext,ViewPDFActivity.class);
                    intent.putExtra("tvDocName", tv_doc_name.getText());
//                    TextView nameDoc = tv_doc_name;
//                    TextView pdfIntent = Intent(nameDoc, ViewPDFActivity::class.java);
////                    pdfIntent.putExtra();
                    mContext.startActivity(intent);
            }
        }
    }

    public DanhSachFileAdapter(Context mContext, List<ListFileRelatedResponse.Data> datalist) {
        this.mContext = mContext;
        this.datalist = datalist;
    }

    @Override
    public DanhSachFileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new DanhSachFileAdapter.MyViewHolder(inflater.inflate(R.layout.item_doc_type_1, parent, false));
    }


    @Override
    public void onBindViewHolder(final DanhSachFileAdapter.MyViewHolder holder, int position) {
        final ListFileRelatedResponse.Data newItem = datalist.get(position);
        holder.tv_doc_name.setText(newItem.getName());
        holder.tv_doc_desc.setText(newItem.getCreator());
        checkFileType(newItem.getName(), holder.img_file);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    private void checkFileType(String fileName, ImageView imageView) {
        if (fileName.trim().toUpperCase().endsWith(AppDef.DOC) || fileName.trim().toUpperCase().endsWith(AppDef.DOCX)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_doc));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.XLS) || fileName.trim().toUpperCase().endsWith(AppDef.XLSX)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_xls));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PPT) || fileName.trim().toUpperCase().endsWith(AppDef.PPTX)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_ppt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PDF)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_pdf));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.ZIP)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_zip));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.RAR)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_rar));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.TXT)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_txt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.MPP)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_mpp));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.JPG)
                || fileName.trim().toUpperCase().endsWith(AppDef.JPEG)
                || fileName.trim().toUpperCase().endsWith(AppDef.PNG)
                || fileName.trim().toUpperCase().endsWith(AppDef.GIF)
                || fileName.trim().toUpperCase().endsWith(AppDef.TIFF)
                || fileName.trim().toUpperCase().endsWith(AppDef.BMP)) {
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_image));
        }
    }
}