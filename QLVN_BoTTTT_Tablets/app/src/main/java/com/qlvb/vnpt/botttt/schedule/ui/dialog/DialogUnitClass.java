package com.qlvb.vnpt.botttt.schedule.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.evenBus.EventListDialogSelect;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.TreeViewLevelCheckBoxAdapter;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.TreeViewLevelListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by linhl on 8/28/2018.
 */

public class DialogUnitClass extends Dialog {

    @BindView(R.id.expandible_listview)
    ExpandableListView expandibleListview;

    RealmResults<UnitObject> allDataUnit;
    List<UnitObject> allDataTreeUnit = new ArrayList<>();
    RealmList<UnitObject> allListTreeUnit = new RealmList<>();
    private Realm realm;
    private Activity activity;
    private RealmController realmController;
    final RealmList<UnitObject> resultsChecked = new RealmList<>();
    static TreeViewLevelCheckBoxAdapter threeLevelListAdapterAdapter;
    public static HashMap<Integer, String>childMap;
    private int paramType = -1;

    public DialogUnitClass(Activity context, int paramType) {
        super(context);
        activity = context;
        this.paramType = paramType;
        childMap = new HashMap<>();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_unit_select);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 330/*height value*/, getResources().getDisplayMetrics()); for custom height value
        getWindow().setAttributes(lp);
        //get realm instance
        ButterKnife.bind(this);
        realmController = RealmController.with(activity);
        this.realm = realmController.getRealm();
        getAllDataUnit();

    }

    private void getAllDataUnit() {
        allDataUnit = realmController.getListUnits();
        allDataTreeUnit = realm.copyFromRealm(allDataUnit);
        allListTreeUnit = buildUnitTree(null);
//        initEpandListNewView();
        switch (paramType) {
            case 0:
                initEpandListNewView();
                break;
            case 1:
                initEpandListCheckBoxView();
                break;
        }

    }

    private RealmList<UnitObject> buildUnitTree(final String id) {

        final RealmList<UnitObject> results = new RealmList<>();
        if (allDataTreeUnit == null) {
            return null;
        }
        for (UnitObject unit : allDataTreeUnit) {
            if (!unit.isTrace() && (unit.getParentId() == id || unit.getParentId().equalsIgnoreCase(id))) {
                unit.setTrace(true);
                UnitObject dicItem = new UnitObject();
                dicItem.setId(unit.getId());
                dicItem.setName(unit.getName());
                dicItem.setParentId(unit.getParentId());
                dicItem.setListChildren(buildUnitTree(unit.getId()));
                results.add(dicItem);
            }
        }
        return results;
    }

    private void initEpandListNewView() {
        // parent adapter
        TreeViewLevelListAdapter threeLevelListAdapterAdapter = new TreeViewLevelListAdapter(activity, allListTreeUnit.get(0));
        // set adapter
        expandibleListview.setAdapter(threeLevelListAdapterAdapter);

        // OPTIONAL : Show one list at a time
        expandibleListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandibleListview.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    private void initEpandListCheckBoxView() {
        // parent adapter
        threeLevelListAdapterAdapter = new TreeViewLevelCheckBoxAdapter(activity, allListTreeUnit.get(0), 50);
        // set adapter
        expandibleListview.setAdapter(threeLevelListAdapterAdapter);

        // OPTIONAL : Show one list at a time
        expandibleListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandibleListview.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

    private void getDataSelectTree(UnitObject unitData) {

        if (unitData == null) {
            return;
        }
        if (unitData.getIsCheckProcess() != null && !unitData.getIsCheckProcess().equalsIgnoreCase("")) {
            resultsChecked.add(unitData);
            return;
        }
        if (unitData.getListChildren() != null && unitData.getListChildren().size() > 0) {
            for (UnitObject unitChildren : unitData.getListChildren()) {
                getDataSelectTree(unitChildren);
            }
        }
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void submit(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ok:
                dismiss();
                if (paramType == 0) {
                    getDataSelectTree(allListTreeUnit.get(0));
                    if (resultsChecked != null && resultsChecked.size() >0) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setResultsUnitChecked(resultsChecked);
                        EventBus.getDefault().postSticky(event);
                    }
                } else if (paramType == 1) {
                    if (childMap != null && !childMap.get(0).equalsIgnoreCase("")) {
                        EventListDialogSelect event = new EventListDialogSelect();
                        event.setIdSelect(childMap.get(0));
                        EventBus.getDefault().postSticky(event);
                    }
                }
                break;
        }
    }

    public static void restartAdapter() {
        threeLevelListAdapterAdapter.notifyDataSetChanged();
    }
}
