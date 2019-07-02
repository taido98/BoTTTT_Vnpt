package com.qlvb.vnpt.botttt.schedule.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseFragment;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.ListDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.ListMailAdapter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ListDocumentPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.ListDocumentView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentRightContentComming extends BaseFragment implements ListDocumentView {
    Unbinder unbinder;
    @BindView(R.id.list_mail)
    ListView listMail;
    @BindView(R.id.rightFragmentContainer)
    FrameLayout rightFragmentContainer;
    @Inject
    ListDocumentPresenter listDocumentPresenter;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.list_loading)
    LoadingDots listLoading;
    @BindView(R.id.et_list_search)
    EditText etListSearch;
    @BindView(R.id.tv_list_name)
    TextView tvListName;

    private ListMailAdapter listMailAdapter;
    private String typeDocument = "";
    private Integer pageNo = 1;
    private Integer pageRec = 30;
    private String param = "";
    private String type = "";
    private List<ListDocumentResponse.Datum> listDocument;
    private ProgressBar progressBar;
    private Boolean isLoadMore = false;
    private Boolean isFirstLoading = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_comming, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        etListSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearchList();
                    return true;
                }
                return false;
            }
        });

        listMail.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if(scrollState == SCROLL_STATE_IDLE
                        && listMail.getLastVisiblePosition() >= (listDocument.size())
                        && isLoadMore){
                    progressBar.setVisibility(View.VISIBLE);
                    pageNo += 1;
                    ListDocumentRequest listDocumentRequest = new ListDocumentRequest(pageNo.toString(), pageRec.toString(), param, type);
                    listDocumentPresenter.getListDocument(listDocumentRequest);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    private void performSearchList() {
        resetLoading();
        hideKeyboard(getActivity());
        param = etListSearch.getText().toString().trim();
        ListDocumentRequest listDocumentRequest = new ListDocumentRequest(pageNo.toString(), pageRec.toString(), param, type);
        listDocumentPresenter.getListDocument(listDocumentRequest);
    }

    private void initView() {
        listDocumentPresenter.setView(this);
        listDocumentPresenter.onViewCreate();
        resetLoading();

    }

    private void initControls() {

        initFragment();
        listLoading.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        type = getArguments().getString("TYPE_VAN_BAN");
        String nameList = getArguments().getString("NAME_VAN_BAN");
        if(nameList != null){
            tvListName.setText(nameList);
        }
        setupListMail();
        ListDocumentRequest listDocumentRequest = new ListDocumentRequest(pageNo.toString(), pageRec.toString(), param, type);
        listDocumentPresenter.getListDocument(listDocumentRequest);
    }

    private void setupListMail() {
        if(type == null || type.equals("")){
            type = AppDef.VAN_BAN_DI;
        }
        listDocument = new ArrayList<>();
        listMailAdapter = new ListMailAdapter(getContext(), listDocument, type, this);
        listMail.setAdapter(listMailAdapter);
        setListViewFooter();
    }

    private void initFragment() {
        DetailDocumentComingFragment detailDocumentCommingFragment = new DetailDocumentComingFragment();
        replaceFramgment(detailDocumentCommingFragment);
    }


    private void setListViewFooter(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.footer_listview_progressbar, null);
        progressBar = view.findViewById(R.id.progressBar);
        listMail.addFooterView(progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private void resetLoading(){
        pageNo = 1;
        pageRec = 30;
        isFirstLoading = true;
        isLoadMore = false;
        if( listDocument != null && listDocument.size() > 0){
            listDocument.clear();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void replaceFramgment(Fragment fragment) {
        FragmentTransaction transList = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transList.replace(R.id.rightFragmentContainer, fragment);
        transList.commit();
    }

    @Override
    public void onGetListDocumentSuccess(List<ListDocumentResponse.Datum> dataResponse) {
        listLoading.setVisibility(View.GONE);
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        if (dataResponse != null && dataResponse.size() > 0) {
            listDocument.addAll(dataResponse);
            listMailAdapter.notifyDataSetChanged();
            isLoadMore = true;
            if(isFirstLoading){
                isFirstLoading = false;

                listMail.performItemClick(listMail.getChildAt(0), 0, listMailAdapter.getItemId(0));
                DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                Bundle bundle = new Bundle();
                bundle.putString("ID_VAN_BAN", listMailAdapter.getItem(0).getId());
                if(type == null || type.equals("")){
                    type = AppDef.VAN_BAN_DI;
                }
                bundle.putString("TYPE_VAN_BAN", type);
                detailDocumentComingFragment.setArguments(bundle);
                replaceFramgment(detailDocumentComingFragment);

            }

            listMail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("Info", "abc");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }else {
            isLoadMore = false;
        }
        if (listDocument.size() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetListDocumentFailed(String message) {
        listLoading.setVisibility(View.GONE);
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        showToast(message);

    }

    @Override
    public void onGetListDocumentError(Throwable e) {
        listLoading.setVisibility(View.GONE);
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
        showToast(e.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listDocumentPresenter.onViewDestroy();
    }


}
