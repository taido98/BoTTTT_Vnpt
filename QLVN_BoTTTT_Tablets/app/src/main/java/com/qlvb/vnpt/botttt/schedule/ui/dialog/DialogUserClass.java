package com.qlvb.vnpt.botttt.schedule.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.evenBus.EventListDialogSelect;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.UserSelectDialogAdpater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by linhl on 8/30/2018.
 */

public class DialogUserClass extends Dialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_search_user)
    EditText edtSearchUser;
    @BindView(R.id.ll_unit_from_user)
    LinearLayout llUnitFromUser;
    @BindView(R.id.listview_user)
    ListView listviewUser;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_unit_by_user)
    TextView tvUnitByUser;
    private Activity activity;
    private RealmController realmController;
    UserSelectDialogAdpater adapter;
    private String unitUser;
    private String unitName;
    private int typeInput = -1;

    public DialogUserClass(Activity context, String unitUser, int typeInput) {
        super(context);
        activity = context;
        this.unitUser = unitUser;
        this.typeInput = typeInput;
    }

    private Realm realm;
    List<? extends RealmObject> listUser = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_user_select);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        //get realm instance
        ButterKnife.bind(this);
        realmController = RealmController.with(activity);
        this.realm = realmController.getRealm();
        getInforData(unitUser);
    }


    private void getInforData(String unitUser) {
        if (typeInput == 0) {
            listUser = realm.copyFromRealm(realmController.getUserByUnitandName("", unitUser));
            unitName = realmController.getUnitById(unitUser).getName();
        } else if (typeInput == 1) {
            listUser = realm.copyFromRealm(realmController.getMinniterByName(""));
        }
        initView();
    }


    private void initView() {
        if (typeInput == 0) {
            llUnitFromUser.setVisibility(View.VISIBLE);
            tvUnitByUser.setText(unitName);
        } else {
            llUnitFromUser.setVisibility(View.GONE);
        }
        adapter = new UserSelectDialogAdpater(listUser, activity, realmController);
        listviewUser.setAdapter(adapter);
        edtSearchUser.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        edtSearchUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().equalsIgnoreCase("")) {
                    RealmResults<?extends RealmObject> listUserSearch = null;
                    if (typeInput == 0) {
                        listUserSearch = realmController.getListUsers();
                    } else {
                        listUserSearch = realmController.getMinniterByName("");
                    }

                    if (listUserSearch != null && listUserSearch.size() > 0) {
                        listUser = realm.copyFromRealm(listUserSearch);
                        adapter.updateData(listUser);

                    }
                }
            }
        });
    }

    private void performSearch() {
        edtSearchUser.clearFocus();
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edtSearchUser.getWindowToken(), 0);
        if (!edtSearchUser.getText().toString().trim().isEmpty()) {
            RealmResults<? extends RealmObject> listUserSearch = null;
            if (typeInput == 0) {
                listUserSearch = realmController.getUserByUnitandName(edtSearchUser.getText()
                        .toString().trim(), unitUser);
            } else if (typeInput == 1) {
                listUserSearch = realmController.getMinniterByName(edtSearchUser.getText()
                        .toString().trim());
            }

            if (listUserSearch != null && listUserSearch.size() > 0) {
                listUser = realm.copyFromRealm(listUserSearch);
                adapter.updateData(listUser);

            }
        }
    }

    private List<UserObject> getUserSelect() {
        List<UserObject> dataSelect = new ArrayList<>();
        for (RealmObject user : listUser) {
            if (((UserObject)user).getIsCheckProcess() != null && !((UserObject)user).getIsCheckProcess().equalsIgnoreCase("")) {
                dataSelect.add(((UserObject)user));
            }
        }
        return dataSelect;
    }

    private List<MinisterObject> getMinisterSelect() {
        List<MinisterObject> dataSelect = new ArrayList<>();
        for (RealmObject user : listUser) {
            if (((MinisterObject) user).getIsCheckProcess() != null && !((MinisterObject) user).getIsCheckProcess().equalsIgnoreCase("")) {
                dataSelect.add(((MinisterObject) user));
            }
        }
        return dataSelect;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok, R.id.ll_unit_from_user})
    public void OnClickListener(View v) {
        switch (v.getId()) {
            case R.id.ll_unit_from_user:
                showDialogSelectUnit(1);
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:
                dismiss();
                if (typeInput == 0) {
                    if (getUserSelect() != null && getUserSelect().size() > 0) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setResultsUserChecked(getUserSelect());
                        EventBus.getDefault().postSticky(event);
                    }
                } else {
                    if (getMinisterSelect() != null && getMinisterSelect().size() > 0) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setResultsMinisterChecked(getMinisterSelect());
                        EventBus.getDefault().postSticky(event);
                    }
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    private void showDialogSelectUnit(int typeShow) {
        DialogUnitClass dialogUnitClass = new DialogUnitClass(activity, typeShow);
        dialogUnitClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUnitClass.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventListDialogSelect event) {
        /* Do something */
        if (event != null && event.getIdSelect() != null) {
            if (!event.getIdSelect().equalsIgnoreCase("")) {
                unitUser = event.getIdSelect();
                getInforData(event.getIdSelect());
            }
        }
    }
}
