package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.ui.view.SecondLevelExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.realm.RealmResults;

public class ThreeLevelListAdapter extends BaseExpandableListAdapter {

    RealmResults<UnitObject> parentHeaders;
    List<RealmResults<UnitObject>> secondLevel;
    private Context context;
    List<LinkedHashMap<UnitObject, RealmResults<UnitObject>>> data;

    public ThreeLevelListAdapter(Context context, RealmResults<UnitObject> parentHeader,
                                 List<RealmResults<UnitObject>> secondLevel, List<LinkedHashMap<UnitObject, RealmResults<UnitObject>>> data) {
        this.context = context;

        this.parentHeaders = parentHeader;

        this.secondLevel = secondLevel;

        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return parentHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {


        // no idea why this code is working

        return 1;

    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {


        return child;


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_row_first_unit, null);
        TextView text = (TextView) convertView.findViewById(R.id.rowParentText);
        text.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.mipmap.icon_collapse : R.mipmap.icon_expand, 0, 0, 0);
        text.setText(this.parentHeaders.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final SecondLevelExpandableListView secondLevelELV = new SecondLevelExpandableListView(context);

        RealmResults<UnitObject> headers = secondLevel.get(groupPosition);


        List<RealmResults<UnitObject>> childData = new ArrayList<>();
        LinkedHashMap<UnitObject, RealmResults<UnitObject>> secondLevelData = data.get(groupPosition);

        for (UnitObject key : secondLevelData.keySet()) {

            childData.add(secondLevelData.get(key));

        }


        secondLevelELV.setAdapter(new SecondLevelAdapter(context, headers, childData));

        secondLevelELV.setGroupIndicator(null);


        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    secondLevelELV.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });


        return secondLevelELV;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
