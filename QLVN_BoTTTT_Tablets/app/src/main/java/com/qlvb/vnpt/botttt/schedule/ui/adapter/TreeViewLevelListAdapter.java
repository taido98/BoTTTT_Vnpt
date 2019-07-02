package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.ui.view.SecondLevelExpandableListView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class TreeViewLevelListAdapter extends BaseExpandableListAdapter {

    UnitObject dataAllLevel;
    private Activity context;
    SecondLevelExpandableListView secondLevelELV;
    public TreeViewLevelListAdapter(Activity context, UnitObject parentHeader) {
        this.context = context;
        this.dataAllLevel = parentHeader;

    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataAllLevel.getListChildren().size();

    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public Object getChild(int group, int child) {


        return dataAllLevel.getListChildren().get(child);


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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_first_unit, parent,false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.rowParentText.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.mipmap.icon_collapse : R.mipmap.icon_expand, 0, 0, 0);
        groupViewHolder.rowParentText.setText(dataAllLevel.getName());
        if (dataAllLevel.getIsCheckProcess() != null && !dataAllLevel.getIsCheckProcess().equalsIgnoreCase("")) {
            if (dataAllLevel.getIsCheckProcess().equalsIgnoreCase("XLC")) {
                groupViewHolder.radioXlc.setChecked(true);

            } else if (dataAllLevel.getIsCheckProcess().equalsIgnoreCase("PH")) {
                groupViewHolder.radioPh.setChecked(true);
            } else {
                groupViewHolder.radioXem.setChecked(true);
            }

        }
        groupViewHolder.radioXlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(groupViewHolder.radioXlc.isChecked()){
                    dataAllLevel.setIsCheckProcess("XLC");
                }else{
                    dataAllLevel.setIsCheckProcess("");
                }
            }
        });
        groupViewHolder.radioPh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(groupViewHolder.radioPh.isChecked()){
                    dataAllLevel.setIsCheckProcess("PH");
                }else{
                    dataAllLevel.setIsCheckProcess("");
                }
            }
        });
        groupViewHolder.radioXem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (groupViewHolder.radioXem.isChecked()) {
                    dataAllLevel.setIsCheckProcess("XEM");
                } else {
                    dataAllLevel.setIsCheckProcess("");
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        secondLevelELV = (SecondLevelExpandableListView) convertView;
        if (secondLevelELV == null) {
            secondLevelELV = new SecondLevelExpandableListView(context);
        }
        secondLevelELV.setAdapter(new SecondTreeViewLevelAdapter(context, dataAllLevel.getListChildren().get(childPosition)));
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

    static class GroupViewHolder {
        @BindView(R.id.rowParentText)
        TextView rowParentText;
        @BindView(R.id.radio_xlc)
        RadioButton radioXlc;
        @BindView(R.id.radio_ph)
        RadioButton radioPh;
        @BindView(R.id.radio_xem)
        RadioButton radioXem;
        @BindView(R.id.radioGroup1)
        RadioGroup radioGroup1;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
