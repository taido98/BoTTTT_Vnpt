package com.qlvb.vnpt.botttt.schedule.ui.fragment;



import android.app.Activity;

import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseFragment;
import com.qlvb.vnpt.botttt.schedule.app.evenBus.EvenChangeGoCome;
import com.qlvb.vnpt.botttt.schedule.app.evenBus.EventListDialogSelect;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.app.utils.AlertDialogManager;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.ExpandableTitleTypeModel;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.LuanChuyenDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SendDocumentRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.request.SignRequest;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ChuyenVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.DetailDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.LuuVanBanResponse;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.SignResponse;
import com.qlvb.vnpt.botttt.schedule.domain.repository.AppState;
import com.qlvb.vnpt.botttt.schedule.domain.repository.LoginUserCookies;
import com.qlvb.vnpt.botttt.schedule.ui.activity.ViewPDFActivity;
import com.qlvb.vnpt.botttt.schedule.ui.activity.XemDanhSachTatCaFileActivity;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.DetailExpandableListAdapter;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.UserSelectDialogAdpater;
import com.qlvb.vnpt.botttt.schedule.ui.dialog.DialogGroupClass;
import com.qlvb.vnpt.botttt.schedule.ui.dialog.DialogUnitClass;
import com.qlvb.vnpt.botttt.schedule.ui.dialog.DialogUserClass;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.UserSuggestPresenter;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.ChuyenVanBanPresenter;

import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.DetailDocumentPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.InstallFilePresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuanChuyenVanBanPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.LuuVanBanPresenter;
import com.qlvb.vnpt.botttt.schedule.ui.presenter.document.SignFilePresenter;

import com.qlvb.vnpt.botttt.schedule.ui.view.UserView;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.ChuyenVanBanView;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.DetailDocumentView;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.InstallFileView;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuanChuyenVanBanView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.LuuVanBanView;

import com.qlvb.vnpt.botttt.schedule.ui.view.document.SignDocumentView;
import com.qlvb.vnpt.botttt.schedule.ui.view.document.UserSuggestView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

import io.realm.RealmObject;

import okhttp3.ResponseBody;


/**
 */
public class DetailDocumentComingFragment extends BaseFragment
        implements DetailDocumentView, ChuyenVanBanView, LuanChuyenVanBanView, SignDocumentView, LuuVanBanView,UserSuggestView {


    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.view_loading)
    LoadingDots viewLoading;

    @BindView(R.id.btn_luu)
    ImageView btnLuu;
    @BindView(R.id.btn_chuyen)
    ImageView btnChuyen;
    @BindView(R.id.btn_huy)
    ImageView btnHuy;
    @BindView(R.id.btn_ky)
    ImageView btnKy;
    @BindView(R.id.btn_huy_luu)
    ImageView btnHuyLuu;
    @BindView(R.id.btn_xem)
    ImageView btnXem;
    @BindView(R.id.btn_luan_chuyen)
    ImageView btnLuanChuyen;

    @BindView(R.id.group_chuyen)
    LinearLayout groupChuyen;
    @BindView(R.id.btn_ac_xoa)
    ImageView btn_ac_xoa;
    @BindView(R.id.edt_nhap_don_vi)
    EditText edtNhapDonVi;
    @BindView(R.id.edt_nhap_ca_nhan)
    EditText edtNhapCaNhan;
    @BindView(R.id.edt_nhap_nhom_ca_nhan)
    EditText edtNhapNhomCaNhan;

    @BindView(R.id.txt_nhap_noi_dung)
    EditText txt_nhap_noi_dung;

    @Inject
    AppState appState;
    @BindView(R.id.ll_unit)
    LinearLayout llUnit;
    @BindView(R.id.ll_personal)
    LinearLayout llPersonal;
    @BindView(R.id.ll_group)
    LinearLayout llGroup;
    @BindView(R.id.view_unit)
    View viewUnit;
    @BindView(R.id.view_minister)
    View viewMinister;
    private Realm realm;
    private String id;
    private EditText edt_search_group;

    DetailExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    List<ExpandableTitleTypeModel> expandableTitleTypeModelList;
    HashMap<String, List<?>> expandableListDetail;
    private String typeDocs;

    private LinearLayout viewKy;
    private int typeChuyenvsLuanChuyen = -1;
    private TextView filename_1, filename_2;
    private ImageView imgFile_1, imgFile_2;
    private Integer idFile1 = 0;
    private Integer idFile2 = 0;
    private long fileDownloadSize = 0;
    private String fileDownloadDir = "";
    private int fileId = 0;
    private ProgressDialog progressDialog;

    private ImageView btn_xoa, btn_ky_2, btn_huy_ky_2;
    private TextView tv_luu, tv_mau;
    private EditText etComment;
    private Integer signType = 0;
    private boolean isOnTextChanged = false;
    List<? extends RealmObject> listDataPopup = new ArrayList<>();
    private PopupWindow changeSortPopUp;
    private enum TypeSearch{
        Selected,SelectAll
    }
    private TypeSearch typeSearch;
    private List<DetailDocumentResponse.FileDinhKem> listFileId;
    @Inject
    DetailDocumentPresenter detailDocumentPresenter;

    @Inject
    ChuyenVanBanPresenter chuyenVanBanPresenter;
    @Inject
    LuanChuyenVanBanPresenter luanChuyenVanBanPresenter;
    @Inject
    LuuVanBanPresenter luuVanBanPresenter;

    @Inject
    LoginUserCookies loginUserCookies;

    String strUnitPH = "";
    String strUnitXLC = "";
    String strUnitXEM = "";

    String strUserPH = "";
    String strUserXLC = "";
    String strUserXEM = "";


    String userPrimary = "";
    String userProcess = "";

    int statusSave = -1;
    private String typeButton;

    @Inject
    InstallFilePresenter installFilePresenter;

    @Inject
    SignFilePresenter signFilePresenter;
    @Inject
    UserSuggestPresenter userSuggestPresenter;

    private RealmController realmController;

    public DetailDocumentComingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmController = RealmController.with(getActivity());
        this.realm = realmController.getRealm();
        userSuggestPresenter.setView(this);
        userSuggestPresenter.onViewCreate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_document, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {

        detailDocumentPresenter.setView(this);
        detailDocumentPresenter.onViewCreate();
        //chuyen van ban
        chuyenVanBanPresenter.setView(this);
        chuyenVanBanPresenter.onViewCreate();
        //luan chuyen van ban
        luanChuyenVanBanPresenter.setView(this);
        luanChuyenVanBanPresenter.onViewCreate();
        //luu van ban
        luuVanBanPresenter.setView(this);
        luuVanBanPresenter.onViewCreate();

        signFilePresenter.setView(this);
        signFilePresenter.onViewCreate();
        edtNhapCaNhan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hidePoupWindow();
                }
            }
        });
        edtNhapDonVi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hidePoupWindow();
                }
            }
        });
        edtNhapCaNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("", s.toString());
//                if (!s.toString().trim().isEmpty()) {
//                    isOnTextChanged = true;
//                } else {
//                    isOnTextChanged = false;
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("", s.toString());
                if (getActivity().getCurrentFocus() == edtNhapCaNhan) {
                    // is only executed if the EditText was directly changed by the user
                    isOnTextChanged = true;
                }
//                if (isOnTextChanged) {
//                    return;
//                } else {
//                    if (!s.toString().trim().isEmpty()) {
//                        isOnTextChanged = true;
//                    } else
//                        isOnTextChanged = false;
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isOnTextChanged) {
                    if (changeSortPopUp != null && changeSortPopUp.isShowing()) {
                        ChangeDataSelect(edtNhapCaNhan,0);
                    } else {
                        showUserSuggestPopup(getActivity(), new Point(0, 0),0,edtNhapCaNhan);
                    }
                    isOnTextChanged = false;
                }

                edtNhapCaNhan.setSelectAllOnFocus(true);
            }
        });
        edtNhapDonVi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("", s.toString());
//                if (!s.toString().trim().isEmpty()) {
//                    isOnTextChanged = true;
//                } else {
//                    isOnTextChanged = false;
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("", s.toString());
                if (getActivity().getCurrentFocus() == edtNhapDonVi) {
                    // is only executed if the EditText was directly changed by the user
                    isOnTextChanged = true;
                }
//                if (isOnTextChanged) {
//                    return;
//                } else {
//                    if (!s.toString().trim().isEmpty()) {
//                        isOnTextChanged = true;
//                    } else
//                        isOnTextChanged = false;
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isOnTextChanged) {
                    if (changeSortPopUp != null && changeSortPopUp.isShowing()) {
                        ChangeDataSelect(edtNhapDonVi,1);
                    } else {
                        showUserSuggestPopup(getActivity(), new Point(0, 0),1,edtNhapDonVi);
                    }
                    isOnTextChanged = false;
                }

                edtNhapCaNhan.setSelectAllOnFocus(true);
            }
        });
        tvNoData.setVisibility(View.GONE);
        viewLoading.setVisibility(View.GONE);
        listFileId = new ArrayList<>();
        try {
            id = getArguments().getString("ID_VAN_BAN");
            viewLoading.setVisibility(View.VISIBLE);
            userSuggestPresenter.getListUserSuggest();
//            if (id != null) {
//                viewLoading.setVisibility(View.VISIBLE);
//                detailDocumentPresenter.getDetailDocument(id);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            typeDocs = getArguments().getString("TYPE_VAN_BAN");
            checkTypeButton(typeDocs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try
        {
            typeButton = getArguments().getString("LOAI_NUT");
            if(typeButton.equalsIgnoreCase("chuyen")){
                typeChuyenvsLuanChuyen = 0;
                //la van ban den thi hien don vi + ca nhan + nhom ca nhan
                if (EventBus.getDefault().getStickyEvent(EvenChangeGoCome.class).getSt() == 1) {
                    edtNhapCaNhan.clearFocus();
                    edtNhapCaNhan.setText("");
                    groupChuyen.setVisibility(View.VISIBLE);
                    llUnit.setVisibility(View.VISIBLE);
                    llGroup.setVisibility(View.VISIBLE);
                    viewMinister.setVisibility(View.VISIBLE);
                    viewUnit.setVisibility(View.VISIBLE);
                }
            } else if(typeButton.equalsIgnoreCase("luanchuyen")) {
                typeChuyenvsLuanChuyen = 1;
                groupChuyen.setVisibility(View.VISIBLE);
                edtNhapCaNhan.clearFocus();
                edtNhapCaNhan.setText("");
                llUnit.setVisibility(View.GONE);
                llGroup.setVisibility(View.GONE);
                viewMinister.setVisibility(View.GONE);
                viewUnit.setVisibility(View.GONE);
            } else if(typeButton.equalsIgnoreCase("luu")) {
                luuVanBanPresenter.saveDocument(loginUserCookies.getAuthent(), id, 1);
            } else if(typeButton.equalsIgnoreCase("huyluu")){
                luuVanBanPresenter.cancelSaveDocument(loginUserCookies.getAuthent(), id, 0);
            }
        }
        catch (Exception e){
        }

    }

    private void checkTypeButton(String type) {
        btnLuu.setVisibility(View.GONE);
        btnHuyLuu.setVisibility(View.GONE);
        btnXem.setVisibility(View.GONE);
        btnHuy.setVisibility(View.GONE);
        btnChuyen.setVisibility(View.GONE);
        btnLuanChuyen.setVisibility(View.GONE);
        btnKy.setVisibility(View.GONE);

        switch (type) {
            case AppDef.VAN_BAN_DEN:
                btnLuu.setVisibility(View.VISIBLE);
                btnChuyen.setVisibility(View.VISIBLE);
                btnLuanChuyen.setVisibility(View.VISIBLE);
                btnXem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DEN_DA_XU_LY:
                btnLuu.setVisibility(View.VISIBLE);
                btnXem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DEN_DA_LUU:
                btnXem.setVisibility(View.VISIBLE);
                btnHuyLuu.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DEN_TAT_CA:
                break;
            case AppDef.VAN_BAN_DI:
                btnKy.setVisibility(View.VISIBLE);
                btnChuyen.setVisibility(View.VISIBLE);
                btnXem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_DA_KY_DUYET:
                btnXem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_TU_CHOI_KY:
                btnXem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_TAT_CA:
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_luu, R.id.btn_xem, R.id.btn_chuyen, R.id.btn_ac_xoa, R.id.btn_huy, R.id.btn_ky,
            R.id.btn_huy_luu, R.id.btn_ac_chuyen,
            R.id.btn_xoa_don_vi, R.id.btn_them_don_vi, R.id.btn_them_ca_nhan, R.id.btn_them_nhom_ca_nhan,
            R.id.btn_xoa_nhom_ca_nhan, R.id.btn_luan_chuyen,R.id.btn_xoa_ca_nhan})
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_luu:
                luuVanBanPresenter.saveDocument(loginUserCookies.getAuthent(), id, 1);
                break;
            case R.id.btn_huy_luu:
                luuVanBanPresenter.cancelSaveDocument(loginUserCookies.getAuthent(), id, 0);
                break;
            case R.id.btn_xem:
                Intent intent = new Intent(getActivity(), XemDanhSachTatCaFileActivity.class);
                intent.putExtra( "DOC_ID", id);
                startActivity(intent);
                break;

            case R.id.btn_chuyen:
                typeChuyenvsLuanChuyen = 0;
                //la van ban den thi hien don vi + ca nhan + nhom ca nhan
                if (EventBus.getDefault().getStickyEvent(EvenChangeGoCome.class).getSt() == 1) {
                    edtNhapCaNhan.clearFocus();
                    edtNhapCaNhan.setText("");
                    groupChuyen.setVisibility(View.VISIBLE);
                    llUnit.setVisibility(View.VISIBLE);
                    llGroup.setVisibility(View.VISIBLE);
                    viewMinister.setVisibility(View.VISIBLE);
                    viewUnit.setVisibility(View.VISIBLE);
                }
                // la van ban di thi hien nhap text
                else {

                }

                break;
            case R.id.btn_luan_chuyen:
                typeChuyenvsLuanChuyen = 1;
                groupChuyen.setVisibility(View.VISIBLE);
                edtNhapCaNhan.clearFocus();
                edtNhapCaNhan.setText("");
                llUnit.setVisibility(View.GONE);
                llGroup.setVisibility(View.GONE);
                viewMinister.setVisibility(View.GONE);
                viewUnit.setVisibility(View.GONE);
                break;
            case R.id.btn_ac_xoa:
                groupChuyen.setVisibility(View.GONE);
                hideKeyboard(getActivity());
                break;

            case R.id.btn_ac_chuyen:
                if(typeChuyenvsLuanChuyen == 1 ){
                    LuanChuyenDocumentRequest luanChuyenDocumentRequest = new LuanChuyenDocumentRequest();
                    if(txt_nhap_noi_dung.getText().toString().trim().isEmpty()){
                        luanChuyenDocumentRequest.setComment("");
                    } else {
                        luanChuyenDocumentRequest.setComment(txt_nhap_noi_dung.getText().toString().trim());
                    }
                    luanChuyenDocumentRequest.setDocId(id);
                    luanChuyenDocumentRequest.setUserPrimary(userPrimary);
                    luanChuyenDocumentRequest.setUserProcess(userProcess);
                    luanChuyenVanBanPresenter.luanChuyenDocument(loginUserCookies.getAuthent(), luanChuyenDocumentRequest);
                } else {
                    SendDocumentRequest sendDocumentRequest = new SendDocumentRequest();
                    if(txt_nhap_noi_dung.getText().toString().trim().isEmpty()){
                        sendDocumentRequest.setComment("");
                    } else {
                        sendDocumentRequest.setComment(txt_nhap_noi_dung.getText().toString().trim());
                    }
                    sendDocumentRequest.setDocId(id);
                    sendDocumentRequest.setUnitCoeval(strUnitXEM);
                    sendDocumentRequest.setUnitPrimary(strUnitXLC);
                    sendDocumentRequest.setUnitProcess(strUnitPH);
                    sendDocumentRequest.setUserCoeval(strUserXEM);
                    sendDocumentRequest.setUserPrimary(strUserXLC);
                    sendDocumentRequest.setUserProcess(strUserPH);
                    chuyenVanBanPresenter.sendDocument(loginUserCookies.getAuthent(), sendDocumentRequest);
                }

                break;

            case R.id.btn_huy:
                break;
            case R.id.btn_ky:
                if (viewKy != null) {
                    viewKy.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_xoa_don_vi:
                edtNhapCaNhan.clearFocus();
                edtNhapDonVi.setText("");
                break;
            case R.id.btn_them_don_vi:
                showDialogSelectUnit(0);
                break;
            case R.id.btn_them_ca_nhan:
                showDialogSelectUser(appState.getState(AppState.PREF_KEY_UNIT_ID_LOGIN_USER, ""));
                break;
            case R.id.btn_xoa_ca_nhan:
                edtNhapCaNhan.clearFocus();
                edtNhapCaNhan.setText("");
                break;
            case R.id.btn_them_nhom_ca_nhan:
                showDialogSelectGroup();
                break;
            case R.id.btn_xoa_nhom_ca_nhan:
                edtNhapNhomCaNhan.setText("");
                break;
        }

    }

    private void initData(final DetailDocumentResponse.Data dataResponse) {
//        expandableListDetail = ExpandableListDataPump.getData();
        expandableListDetail = new LinkedHashMap<>();
        expandableListTitle = new ArrayList<String>();

        // setup group title & type
        expandableTitleTypeModelList = new ArrayList<>();
        // setup detail
        // file dinh kem
        String fileDinhKem = getString(R.string.FILE_DINH_KEM);

        if (dataResponse.fileDinhKem == null) {
            dataResponse.fileDinhKem = new ArrayList<>();
        }
        if (dataResponse.fileDinhKem.size() > 0) {
            fileDinhKem += "(" + String.valueOf(dataResponse.fileDinhKem.size()) + ")";
        }
        expandableTitleTypeModelList.add(0, new ExpandableTitleTypeModel(fileDinhKem, 1));
        expandableListDetail.put(fileDinhKem, dataResponse.fileDinhKem);

        // van ban lien quan
        if (dataResponse.vanBanLienQuan == null) {
            dataResponse.vanBanLienQuan = new ArrayList<>();
        }
        String titleFileLienQuan = getString(R.string.VAN_BAN_LIEN_QUAN);
        if (dataResponse.vanBanLienQuan.size() > 0) {
            titleFileLienQuan += "(" + String.valueOf(dataResponse.vanBanLienQuan.size()) + ")";
        }

        expandableTitleTypeModelList.add(1, new ExpandableTitleTypeModel(titleFileLienQuan, 2));

        expandableListDetail.put(titleFileLienQuan, dataResponse.vanBanLienQuan);

        // add header view
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // type = 0 - van ban den
        // type = 1 - van ban di
        if (typeDocs != null) {
            if (checkTypeVanBan(typeDocs) == 0) {
                View view = inflater.inflate(R.layout.layout_trich_yeu, null);
                expandableListView.addHeaderView(view);
                // set data header
                TextView tvSoKyHieu = view.findViewById(R.id.tv_so_ky_hieu);
                TextView tvNoiDung = view.findViewById(R.id.tv_noi_dung);
                TextView tvNguoiKy = view.findViewById(R.id.tv_nguoi_ky);
                TextView tvNgayBanHanh = view.findViewById(R.id.tv_ngay_ban_hanh);

                tvSoKyHieu.setText(dataResponse.getThongTinVanBan().getSoKyHieu());
                tvNoiDung.setText(dataResponse.getThongTinVanBan().getTrichYeu());
                tvNguoiKy.setText(dataResponse.getThongTinVanBan().getNgayTrinhKy());
                tvNgayBanHanh.setText(dataResponse.getThongTinVanBan().getNgaySoanThao());
                // end
                // thong tin van ban
                expandableTitleTypeModelList.add(2, new ExpandableTitleTypeModel(getString(R.string.NOI_DUNG_TRINH_KY), 5));
                List<DetailDocumentResponse.ThongTinVanBan> listThongTinVanBan = new ArrayList<>();
                listThongTinVanBan.add(dataResponse.thongTinVanBan);
                expandableListDetail.put(getString(R.string.NOI_DUNG_TRINH_KY), listThongTinVanBan);
                // open default list


            } else {
                View view = inflater.inflate(R.layout.header_expend_type_1, null);
                expandableListView.addHeaderView(view);
                // set data header
                TextView tvTrichYeu = view.findViewById(R.id.tv_trich_yeu);
                TextView tvNgayTrinh = view.findViewById(R.id.tv_ngay_trinh);
                filename_1 = view.findViewById(R.id.filename_1);
                filename_2 = view.findViewById(R.id.filename_2);
                imgFile_1 = view.findViewById(R.id.img_file_1);
                imgFile_2 = view.findViewById(R.id.img_file_2);
                // controls view Ky
                viewKy = view.findViewById(R.id.view_ky);
                btn_xoa = view.findViewById(R.id.btn_xoa);
                btn_ky_2 = view.findViewById(R.id.btn_ky_2);
                btn_huy_ky_2 = view.findViewById(R.id.btn_huy_ky_2);
                tv_luu = view.findViewById(R.id.tv_luu);
                tv_mau = view.findViewById(R.id.tv_mau);
                etComment = view.findViewById(R.id.et_comment);
                String eventVanBan = getArguments().getString("EVENT_VAN_BAN");
                if(eventVanBan != null){
                    if(eventVanBan.equals(AppDef.EVENT_KY)){
                        viewKy.setVisibility(View.VISIBLE);
                    }
                }
                //event view ky
                setEventKy(viewKy, btn_xoa, btn_ky_2, btn_huy_ky_2, tv_luu, tv_mau, etComment);
                // end
                tvTrichYeu.setText(dataResponse.getThongTinVanBan().getTrichYeu());
                tvNgayTrinh.setText(dataResponse.getThongTinVanBan().getNgayTrinhKy());
                if (dataResponse.getFileDinhKem() != null && dataResponse.getFileDinhKem().size() > 0) {
                    filename_1.setVisibility(View.GONE);
                    filename_2.setVisibility(View.GONE);
                    imgFile_1.setVisibility(View.GONE);
                    imgFile_2.setVisibility(View.GONE);
                    for (int i = 0; i < dataResponse.getFileDinhKem().size(); i++) {
                        if (i == 0) {
                            filename_1.setVisibility(View.VISIBLE);
                            imgFile_1.setVisibility(View.VISIBLE);
                            filename_1.setText(dataResponse.getFileDinhKem().get(i).getName());
                            checkFileType(dataResponse.getFileDinhKem().get(i).getName(), imgFile_1);
                            idFile1 = dataResponse.getFileDinhKem().get(i).getId();
                            listFileId.add(dataResponse.getFileDinhKem().get(i));
                        }
                        if (i == 1) {
                            filename_2.setVisibility(View.VISIBLE);
                            imgFile_2.setVisibility(View.VISIBLE);
                            filename_2.setText(dataResponse.getFileDinhKem().get(i).getName());
                            checkFileType(dataResponse.getFileDinhKem().get(i).getName(), imgFile_2);
                            idFile2 = dataResponse.getFileDinhKem().get(i).getId();
                            listFileId.add(dataResponse.getFileDinhKem().get(i));
                        }
                    }
                } else {
                    filename_1.setVisibility(View.GONE);
                    filename_2.setVisibility(View.GONE);
                    imgFile_1.setVisibility(View.GONE);
                    imgFile_2.setVisibility(View.GONE);
                }
                filename_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), ViewPDFActivity.class);
                        intent.putExtra("FILE_NAME", filename_1.getText().toString().trim());
                        intent.putExtra("FILE_ID", idFile1);
                        intent.putExtra("LIST_FILE_ID", (Serializable) listFileId);

                        startActivity(intent);
                    }
                });
                filename_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), ViewPDFActivity.class);
                        intent.putExtra("FILE_NAME", filename_2.getText().toString().trim());
                        intent.putExtra("FILE_ID", idFile2);
                        intent.putExtra("LIST_FILE_ID", (Serializable) listFileId);
                        startActivity(intent);
                    }
                });
                // end
                // thong tin van ban
                expandableTitleTypeModelList.add(2, new ExpandableTitleTypeModel(getString(R.string.NOI_DUNG_TRINH_KY), 3));
                List<DetailDocumentResponse.ThongTinVanBan> listThongTinVanBan = new ArrayList<>();
                listThongTinVanBan.add(dataResponse.thongTinVanBan);
                expandableListDetail.put(getString(R.string.NOI_DUNG_TRINH_KY), listThongTinVanBan);

                // tong hop xu ly
                if (dataResponse.tongHopXuLy == null) {
                    dataResponse.tongHopXuLy = new ArrayList<>();

                }
                expandableTitleTypeModelList.add(3, new ExpandableTitleTypeModel(getString(R.string.TONG_HOP_XU_LY), 4));
                expandableListDetail.put(getString(R.string.TONG_HOP_XU_LY), dataResponse.tongHopXuLy);
                // open default list

            }
        }

        // end


        // end
        expandableListAdapter = new DetailExpandableListAdapter(getContext(), expandableTitleTypeModelList, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.expandGroup(2);
        if (dataResponse.tongHopXuLy != null && dataResponse.tongHopXuLy.size() > 0 && expandableListDetail.size() > 3) {
            expandableListView.expandGroup(3);
        }

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition == 1) {
                    if (dataResponse.vanBanLienQuan.size() > 0) {
                        DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("ID_VAN_BAN", dataResponse.vanBanLienQuan.get(childPosition).getId());
                        bundle.putString("TYPE_VAN_BAN", typeDocs);
                        detailDocumentComingFragment.setArguments(bundle);
                        replaceFramgment(detailDocumentComingFragment);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onDetailDocumentSuccess(DetailDocumentResponse.Data dataResponse) {
        viewLoading.setVisibility(View.GONE);
        initData(dataResponse);
    }

    @Override
    public void onDetailDocumentFailed(String message) {
        viewLoading.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
        showToast(message);
    }

    @Override
    public void onDetailDocumentError(Throwable e) {
        viewLoading.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
        showToast(e.toString());
    }


    // van ban den return type 0, van ban di return type 1
    private Integer checkTypeVanBan(String type) {
        switch (type) {
            case AppDef.VAN_BAN_DEN:
                return 0;
            case AppDef.VAN_BAN_DEN_DA_XU_LY:
                return 0;
            case AppDef.VAN_BAN_DEN_DA_LUU:
                return 0;
            case AppDef.VAN_BAN_DEN_TAT_CA:
                return 0;
            case AppDef.VAN_BAN_DI:
                return 1;
            case AppDef.VAN_BAN_DI_DA_KY_DUYET:
                return 1;
            case AppDef.VAN_BAN_DI_TU_CHOI_KY:
                return 1;
            case AppDef.VAN_BAN_DI_TAT_CA:
                return 1;
            default:
                return 0;
        }
    }

    private void showDialogSelectUnit(int typeShow) {
        DialogUnitClass dialogUnitClass = new DialogUnitClass(getActivity(), typeShow);
        dialogUnitClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUnitClass.show();
    }

    private void showDialogSelectUser(String unitUser) {
        DialogUserClass dialogUserClass = new DialogUserClass(getActivity(), unitUser, typeChuyenvsLuanChuyen);
        dialogUserClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUserClass.show();
    }

    private void showDialogSelectGroup() {
        DialogGroupClass dialogUserClass = new DialogGroupClass(getActivity());
        dialogUserClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUserClass.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detailDocumentPresenter.onViewDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void replaceFramgment(Fragment fragment) {
        FragmentTransaction transList = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transList.replace(R.id.rightFragmentContainer, fragment);
        transList.commit();
    }

    private void checkFileType(String fileName, ImageView imageView) {
        if (fileName.trim().toUpperCase().endsWith(AppDef.DOC) || fileName.trim().toUpperCase().endsWith(AppDef.DOCX)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_doc));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.XLS) || fileName.trim().toUpperCase().endsWith(AppDef.XLSX)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_xls));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PPT) || fileName.trim().toUpperCase().endsWith(AppDef.PPTX)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_ppt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.PDF)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_pdf));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.ZIP)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_zip));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.RAR)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_rar));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.TXT)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_txt));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.MPP)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_mpp));
        }
        if (fileName.trim().toUpperCase().endsWith(AppDef.JPG)
                || fileName.trim().toUpperCase().endsWith(AppDef.JPEG)
                || fileName.trim().toUpperCase().endsWith(AppDef.PNG)
                || fileName.trim().toUpperCase().endsWith(AppDef.GIF)
                || fileName.trim().toUpperCase().endsWith(AppDef.TIFF)
                || fileName.trim().toUpperCase().endsWith(AppDef.BMP)) {
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_image));
        }
    }

    private void setEventKy(final LinearLayout viewKy, ImageView btn_xoa, ImageView btn_ky_2, ImageView btn_huy_ky_2, TextView tv_luu, TextView tv_mau, final EditText etComment) {
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewKy.setVisibility(View.GONE);
            }
        });

        btn_ky_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signType = 1;
                SignRequest request = new SignRequest(etComment.getText().toString().trim(), id, 1);
                signFilePresenter.signFile(request);
            }
        });

        btn_huy_ky_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signType = 0;
                SignRequest request = new SignRequest(etComment.getText().toString().trim(), id, 0);
                signFilePresenter.signFile(request);
            }
        });
    }

    @Override
    public void onSignDocumentSuccess(SignResponse.Data response) {
        showToast("Ký thành công");
        if (signType == 1) {
            AlertDialogManager.showAlertDialog(getActivity(), "KÝ THÀNH CÔNG", "ký file thành công", true, 0);
        } else {
            AlertDialogManager.showAlertDialog(getActivity(), "HỦY KÝ THÀNH CÔNG", "hủy ký thành công", true, 0);
        }

    }

    @Override
    public void onSignDocumentFailed(String message) {
        showToast(message);
    }

    @Override
    public void onSignDocumentError(Throwable e) {
        showToast(e.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventListDialogSelect event) {
        /* Do something */
        if (event != null) {
            if (event.getResultsUnitChecked() != null
                    && event.getResultsUnitChecked().size() > 0) {
                String strUnit = "";
                for (UnitObject unit : event.getResultsUnitChecked()) {
                    strUnit += unit.getName() + "(" + unit.getIsCheckProcess() + ")" + ", ";
                    if (unit.getIsCheckProcess().equalsIgnoreCase("XLC")) {
                        strUnitXLC += unit.getId();
                    } else if (unit.getIsCheckProcess().equalsIgnoreCase("PH")) {
                        strUnitPH += unit.getId();
                    } else if (unit.getIsCheckProcess().equalsIgnoreCase("XEM")) {
                        strUnitXEM += unit.getId();
                    }
                }
                edtNhapCaNhan.clearFocus();
                edtNhapDonVi.setText(strUnit);
            }
            if (event.getResultsUserChecked() != null
                    && event.getResultsUserChecked().size() > 0) {
                String strUser = "";
                for (UserObject unit : event.getResultsUserChecked()) {
                    strUser += unit.getName() + "(" + unit.getIsCheckProcess() + ")" + ", ";

                    if (unit.getIsCheckProcess().equalsIgnoreCase("XLC")) {
                        strUserXLC += unit.getUserId();
                    } else if (unit.getIsCheckProcess().equalsIgnoreCase("PH")) {
                        strUserPH += unit.getUserId();
                    } else if (unit.getIsCheckProcess().equalsIgnoreCase("XEM")) {
                        strUserXEM += unit.getUserId();
                    }
                }
                edtNhapCaNhan.clearFocus();
                edtNhapCaNhan.setText(strUser);
            } else if (event.getResultsMinisterChecked() != null
                    && event.getResultsMinisterChecked().size() > 0) {
                String strMinister = "";
                for (MinisterObject unit : event.getResultsMinisterChecked()) {
                    strMinister += unit.getFullName() + "(" + unit.getIsCheckProcess() + ")" + ", ";

                    if (unit.getIsCheckProcess().equalsIgnoreCase("XLC")) {
                        userPrimary += unit.getUserId();
                    } else if (unit.getIsCheckProcess().equalsIgnoreCase("PH")) {
                        userProcess += unit.getUserId();
                    }
                }
                edtNhapCaNhan.setText(strMinister);
            }
            if (event.getResultsGroupChecked() != null
                    && event.getResultsGroupChecked().size() > 0) {
                String strGroup = "";
                for (GroupObject unit : event.getResultsGroupChecked()) {
                    strGroup += unit.getName() + "(" + unit.getIsCheckProcess() + ")" + ", ";
                }
                edtNhapNhomCaNhan.setText(strGroup);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeStickyEvent(EventListDialogSelect.class);
    }


    @Override
    public void onGetListUserSuggestSuccess(final List<UserObject> dataResponse) {
        if (dataResponse != null && dataResponse.size() > 0) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (UserObject user : dataResponse) {
                        final UserObject realmDB_class = realm.where(UserObject.class).equalTo("userId", user.getUserId()).findFirst();
                        realmDB_class.setUpdateDate(user.getUpdateDate());
                    }
                }
            });
        }
        userSuggestPresenter.getListUnitSuggest();
    }

    @Override
    public void onGetListFailed(String message) {
        viewLoading.setVisibility(View.GONE);
    }

    @Override
    public void onGetListUnitSuggestSuccess(final List<UnitObject> dataResponse) {
        if (dataResponse != null && dataResponse.size() > 0) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (UnitObject unit : dataResponse) {
                        final UnitObject realmDB_class = realm.where(UnitObject.class).equalTo("id", unit.getId()).findFirst();
                        realmDB_class.setUpdateDate(unit.getUpdateDate());
                    }
                }
            });
        }
        if (id != null) {
//                viewLoading.setVisibility(View.VISIBLE);
            detailDocumentPresenter.getDetailDocument(id);
        }
    }

    @Override
    public void onGetListError(Throwable e) {
        viewLoading.setVisibility(View.GONE);
    }

    UserSelectDialogAdpater adapter;

    private void showUserSuggestPopup(final Activity context, Point p, final int typeSuggest, final EditText editText) {
        // Inflate the popup_layout.xml
        typeSearch = TypeSearch.Selected;
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.ll_unit);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.layout_poupup_select, viewGroup, false);

        // Creating the PopupWindow
        changeSortPopUp = new PopupWindow(context);
        changeSortPopUp.setContentView(layout);
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//        changeSortPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = -20;
        int OFFSET_Y = 95;

        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeSortPopUp.showAsDropDown(editText);


        // Getting a reference to Close button, and close the popup when clicked.
        ListView listData = (ListView) layout.findViewById(R.id.listview_data);
        listDataPopup = getUserData(editText.getText().toString().trim(),typeSuggest);
        adapter = new UserSelectDialogAdpater(listDataPopup, context, realmController);
        listData.setAdapter(adapter);
        TextView close = (TextView) layout.findViewById(R.id.tv_cancel);
        final TextView tvBott = (TextView) layout.findViewById(R.id.tv_bott);
        tvBott.setPaintFlags(tvBott.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        final TextView tv_ok = (TextView) layout.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
                if (typeSuggest == 0) {
                    if (getUserSelect(adapter.getUserList()) != null && getUserSelect(adapter.getUserList()).size() > 0) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setResultsUserChecked(getUserSelect(adapter.getUserList()));
                        EventBus.getDefault().postSticky(event);
                    }
                } else {
                    if (getUnitSelect(adapter.getUserList()) != null && getUnitSelect(adapter.getUserList()).size() > 0) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setResultsUnitChecked(getUnitSelect(adapter.getUserList()));
                        EventBus.getDefault().postSticky(event);
                    }
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });
        tvBott.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (typeSearch) {
                    case Selected:
                        typeSearch = TypeSearch.SelectAll;
                        tvBott.setText(getString(R.string.str_underline_user_da_tim_kiem));
                        break;
                    case SelectAll:
                        typeSearch = TypeSearch.Selected;
                        tvBott.setText(getString(R.string.str_underline_botttt));
                        break;
                }
                ChangeDataSelect(editText,typeSuggest);
            }
        });

    }

    private List<? extends RealmObject> getUserData(String username,int typePopup) {
        List<? extends RealmObject> listData = new ArrayList<>();
        if (typePopup == 0) {
            switch (typeSearch) {
                case Selected:
                    listData = realm.copyFromRealm(realmController.getUserSuggest(username));
                    break;
                case SelectAll:
                    listData = realm.copyFromRealm(realmController
                            .getUserByName(username));
                    break;
            }
        } else {
            switch (typeSearch) {
                case Selected:
                    listData = realm.copyFromRealm(realmController.getUnitSuggest(username));
                    break;
                case SelectAll:
                    listData = realm.copyFromRealm(realmController
                            .getUnitByName(username));
                    break;
            }
        }

        return listData;
    }


    private void ChangeDataSelect(EditText editText,int typeSuggets) {
        adapter.updateData(getUserData(editText.getText().toString().trim(),typeSuggets));
    }
    private List<UserObject> getUserSelect(List<? extends RealmObject> listUser) {

            List<UserObject> dataUserSelect = new ArrayList<>();
        for (RealmObject user : listUser) {
            if (((UserObject)user).getIsCheckProcess() != null && !((UserObject)user).getIsCheckProcess().equalsIgnoreCase("")) {
                dataUserSelect.add(((UserObject)user));
            }
        }
        return dataUserSelect;

    }
    private List<UnitObject> getUnitSelect(List<? extends RealmObject> listUser) {

        List<UnitObject> dataUnitSelect = new ArrayList<>();

        for (RealmObject unit : listUser) {
            if (((UnitObject) unit).getIsCheckProcess() != null && !((UnitObject) unit).getIsCheckProcess().equalsIgnoreCase("")) {
                dataUnitSelect.add(((UnitObject) unit));
            }
        }
        return dataUnitSelect;

    }

    private void hidePoupWindow() {
        if (changeSortPopUp != null && changeSortPopUp.isShowing()) {
            changeSortPopUp.dismiss();
        }
    }

    @Override
    public void onSendDocumnetSuccess(ChuyenVanBanResponse dataResponse) {
        Log.d("Thao", "onSendDocumnetSuccess");
        showDiaLog(getActivity(), "Chuyển văn bản thành công.");
    }

    @Override
    public void onSendDocumentFailed(String message) {
        Log.d("Thao", "onSendDocumentFailed" + message);
    }

    @Override
    public void onSendDocumentError(Throwable e) {
        Toast.makeText(getActivity(),
                "Lỗi dữ liệu server",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLuanChuyenDocumnetSuccess(ChuyenVanBanResponse dataResponse) {
        showDiaLog(getActivity(), "Luân Chuyển văn bản thành công.");
    }

    @Override
    public void onLuanChuyenDocumentFailed(String message) {

    }

    @Override
    public void onLuanChuyenDocumentError(Throwable e) {
        Toast.makeText(getActivity(),
                "Lỗi dữ liệu server",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveDocumnetSuccess(LuuVanBanResponse dataResponse){
        showDiaLog(getActivity(), "Lưu văn bản thành công.");
        btnLuu.setVisibility(View.GONE);
        btnHuyLuu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveDocumentFailed(String message) {

    }

    @Override
    public void onSaveDocumentError(Throwable e) {
        Toast.makeText(getActivity(),
                "Lỗi dữ liệu server",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelSaveDocumnetSuccess(LuuVanBanResponse dataResponse) {
        showDiaLog(getActivity(), "Hủy lưu văn bản thành công.");
        btnLuu.setVisibility(View.VISIBLE);
        btnHuyLuu.setVisibility(View.GONE);
    }

    @Override
    public void onCancelSaveDocumentFailed(String message) {
        Toast.makeText(getActivity(),
                "Lỗi kết nối với server",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelSaveDocumentError(Throwable e) {
        Toast.makeText(getActivity(),
                "Lỗi kết nối với server",
                Toast.LENGTH_SHORT).show();
    }

    private void showDiaLog(Context context, String title){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(title)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
//                        restartActivity();
                    }
                })
                .show();
    }

    private void restartActivity(){
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);

        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }
}
