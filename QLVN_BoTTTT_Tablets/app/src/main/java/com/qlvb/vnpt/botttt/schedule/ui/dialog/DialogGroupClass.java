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
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.UserSelectDialogAdpater;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by linhl on 8/30/2018.
 */

public class DialogGroupClass extends Dialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview_group)
    ListView listviewGroup;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_unit_by_user)
    TextView tvUnitByUser;
    @BindView(R.id.edt_search_group)
    EditText edtSearchGroup;
    @BindView(R.id.ll_unit_from_group)
    LinearLayout llUnitFromGroup;
    private Activity activity;
    private RealmController realmController;
    UserSelectDialogAdpater adapter;

    public DialogGroupClass(Activity context) {
        super(context);
        activity = context;
    }

    private Realm realm;
    List<GroupObject> listGroup = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_group_select);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        //get realm instance
        ButterKnife.bind(this);
        realmController = RealmController.with(activity);
        this.realm = realmController.getRealm();
        getInforData();
    }


    private void getInforData() {
        listGroup = realm.copyFromRealm(realmController.getListGroups(""));
        initView();
    }


    private void initView() {
        adapter = new UserSelectDialogAdpater(listGroup, activity, realmController);
        listviewGroup.setAdapter(adapter);
        edtSearchGroup.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        edtSearchGroup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().equalsIgnoreCase("")) {
                    RealmResults<GroupObject> listUserSearch = realmController.getListGroups("");
                    if (listUserSearch != null && listUserSearch.size() > 0) {
                        listGroup = realm.copyFromRealm(listUserSearch);
                        adapter.updateData(listGroup);

                    }
                }
            }
        });
    }

    private void performSearch() {
        edtSearchGroup.clearFocus();
        InputMethodManager in = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(edtSearchGroup.getWindowToken(), 0);
        if (!edtSearchGroup.getText().toString().trim().isEmpty()) {
            RealmResults<GroupObject> listUserSearch = realmController.getListGroups(edtSearchGroup.getText().toString().trim());
            if (listUserSearch != null && listUserSearch.size() > 0) {
                listGroup = realm.copyFromRealm(listUserSearch);
                adapter.updateData(listGroup);

            }
        }
    }

    private List<GroupObject> getGroupSelect() {
        List<GroupObject> dataSelect = new ArrayList<>();
        for (GroupObject user : listGroup) {
            if (user.getIsCheckProcess() != null && !user.getIsCheckProcess().equalsIgnoreCase("")) {
                dataSelect.add(user);
            }
        }
        return dataSelect;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void OnClickListener(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:
                dismiss();
                if (getGroupSelect() != null && getGroupSelect().size() > 0) {
                    EventListDialogSelect event = new EventListDialogSelect();
                    event.setResultsGroupChecked(getGroupSelect());
                    EventBus.getDefault().postSticky(event);
                }
                break;
        }
    }


    private void showDialogSelectUnit(int typeShow) {
        DialogUnitClass dialogUnitClass = new DialogUnitClass(activity, typeShow);
        dialogUnitClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogUnitClass.show();
    }
}
