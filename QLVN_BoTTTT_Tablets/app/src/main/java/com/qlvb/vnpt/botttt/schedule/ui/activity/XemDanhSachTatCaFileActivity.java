package com.qlvb.vnpt.botttt.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseActivity;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListFileRelatedResponse;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.DanhSachFileAdapter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListFileRelatedPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListFileRelatedView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XemDanhSachTatCaFileActivity extends BaseActivity implements ListFileRelatedView {
    @BindView(R.id.rcvDanhSach)
    RecyclerView rcvDanhSach;
    @BindView(R.id.btn_xoa)
    ImageView btn_xoa;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;

    private List<ListFileRelatedResponse.Data> dataList;
    private DanhSachFileAdapter danhSachFileAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Inject
    ListFileRelatedPresenter listFileRelatedPresenter;
    private String docID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_danh_sach_tat_ca_file);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        listFileRelatedPresenter.setView(this);
        listFileRelatedPresenter.onViewCreate();
        // init recyleview
        dataList = new ArrayList<>();
        danhSachFileAdapter = new DanhSachFileAdapter(this, dataList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvDanhSach.setLayoutManager(mLayoutManager);
        rcvDanhSach.setItemAnimator(new DefaultItemAnimator());
        rcvDanhSach.setAdapter(danhSachFileAdapter);
        // end
        docID = getIntent().getStringExtra("DOC_ID");
        if (docID != null) {
            listFileRelatedPresenter.getListDocument(docID);
            showProgressBar();

        }else {
//            tvNoData.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.btn_xoa})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_xoa:
                this.finish();
                break;
//
        }
    }


    @Override
    public void onListFileRelatedSuccess(List<ListFileRelatedResponse.Data> response) {
        hideProgressBar();
        if (response != null && response.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            dataList.addAll(response);
            danhSachFileAdapter.notifyDataSetChanged();

        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListFileRelatedFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onListFileRelatedError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @OnClick({R.id.btn_xoa})
    public void onViewClicked() {
    }
}
