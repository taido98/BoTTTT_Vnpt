package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.ui.dialog.DialogUnitClass;
import com.qlvb.vnpt.botttt.schedule.ui.view.SecondLevelExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TreeViewLevelCheckBoxAdapter extends BaseExpandableListAdapter {

    UnitObject dataAllLevel;
    private Activity context;
    SecondLevelExpandableListView secondLevelELV;
    private int paddingLeft = 0;

    public TreeViewLevelCheckBoxAdapter(Activity context, UnitObject parentHeader, int paddingLeft) {
        this.context = context;
        this.dataAllLevel = parentHeader;
        this.paddingLeft = paddingLeft;

    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataAllLevel.getListChildren() != null ? dataAllLevel.getListChildren().size() : 0;

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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_checkbox_unit, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (dataAllLevel.getListChildren() != null && dataAllLevel.getListChildren().size() > 0) {
            groupViewHolder.rowParentText.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.mipmap.icon_collapse : R.mipmap.icon_expand, 0, 0, 0);
        }
        groupViewHolder.rowParentText.setPadding(paddingLeft, 0, 0, 0);
        groupViewHolder.rowParentText.setText(dataAllLevel.getName());
        if (DialogUnitClass.childMap.get(0) != null && !DialogUnitClass.childMap.get(0).equalsIgnoreCase("")) {
            if (DialogUnitClass.childMap.get(0).equalsIgnoreCase(dataAllLevel.getId())) {
                groupViewHolder.checkboxUnit.setChecked(true);
            }
            else {
                groupViewHolder.checkboxUnit.setChecked(false);
            }
        } else {
            groupViewHolder.checkboxUnit.setChecked(false);
        }
//        groupViewHolder.checkboxUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    DialogUnitClass.childMap.put(0, dataAllLevel.getId());
//                } else {
//                    DialogUnitClass.childMap.put(0, "");
//                }
//                DialogUnitClass.restartAdapter();
//            }
//        });
        groupViewHolder.checkboxUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    DialogUnitClass.childMap.put(0, dataAllLevel.getId());
                } else {
                    DialogUnitClass.childMap.put(0, "");
                }
                DialogUnitClass.restartAdapter();
            }
        });
        if (DialogUnitClass.childMap.get(1) != null && !DialogUnitClass.childMap.get(1).equalsIgnoreCase("")) {
            if (DialogUnitClass.childMap.get(1).equalsIgnoreCase(dataAllLevel.getId())) {
                ExpandableListView eLV = (ExpandableListView) parent;
                eLV.expandGroup(0);
            }
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_unit_select_expand, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
//            TreeViewLevelCheckBoxAdapter customExpandAdapter = (TreeViewLevelCheckBoxAdapter) childViewHolder.expandibleListview.getExpandableListAdapter();
//            customExpandAdapter.updateData(dataAllLevel.getListChildren().get(childPosition));
//            if (DialogUnitClass.childMap.get(1) != null &&
//                    !DialogUnitClass.childMap.get(1).equalsIgnoreCase("")) {
//                if (!DialogUnitClass.childMap.get(1).equalsIgnoreCase(dataAllLevel.getListChildren().get(childPosition).getId())) {
//                    childViewHolder.expandibleListview.collapseGroup(0);
//                } else {
//                    childViewHolder.expandibleListview.expandGroup(0);
//                }
//            }
        }
        childViewHolder.expandibleListview.setAdapter(new TreeViewLevelCheckBoxAdapter(context, dataAllLevel.getListChildren().get(childPosition), paddingLeft + 50));
        childViewHolder.expandibleListview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                DialogUnitClass.childMap.put(1, dataAllLevel.getListChildren().get(childPosition).getId());
            }
        });

//        if (secondLevelELV == null) {
//            convertView = new SecondLevelExpandableListView(context);
//            TreeViewLevelCheckBoxAdapter adapter = new TreeViewLevelCheckBoxAdapter(context, dataAllLevel.getListChildren().get(childPosition), paddingLeft + 50);
//            convertView.setAdapter(adapter);
//            convertView.setGroupIndicator(null);
//        } else {
//            TreeViewLevelCheckBoxAdapter customExpandAdapter = (TreeViewLevelCheckBoxAdapter) secondLevelELV.getExpandableListAdapter();
//            customExpandAdapter.updateData(dataAllLevel.getListChildren().get(childPosition));
//        }
//
//        secondLevelELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            int previousGroup = -1;
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (groupPosition != previousGroup)
//                    secondLevelELV.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
//            }
//        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @BindView(R.id.rowParentText)
        TextView rowParentText;
        @BindView(R.id.checkbox_unit)
        CheckBox checkboxUnit;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private void updateData(UnitObject parentHeader) {
        this.dataAllLevel = parentHeader;
        notifyDataSetChanged();
    }

    static class ChildViewHolder {
        @BindView(R.id.expandible_listview)
        SecondLevelExpandableListView expandibleListview;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }
}
