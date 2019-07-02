package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.ui.view.ToggleableRadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class SecondTreeViewLevelAdapter extends BaseExpandableListAdapter {

    private Activity context;
    UnitObject data;

    public SecondTreeViewLevelAdapter(Activity context, UnitObject data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data;
    }

    @Override
    public int getGroupCount() {

        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupHolder groupHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_second_unit, parent,false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        String groupText = ((UnitObject) getGroup(groupPosition)).getName();
        groupHolder.rowSecondText.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.mipmap.icon_collapse : R.mipmap.icon_expand, 0, 0, 0);
        groupHolder.rowSecondText.setText(groupText);
        groupHolder.radioGroup1.clearCheck();
        if (data.getIsCheckProcess() != null && !data.getIsCheckProcess().equalsIgnoreCase("")) {
            if (data.getIsCheckProcess().equalsIgnoreCase("XLC")) {
                groupHolder.radioXlc.setChecked(true);

            } else if (data.getIsCheckProcess().equalsIgnoreCase("PH")) {
                groupHolder.radioPh.setChecked(true);
            } else {
                groupHolder.radioXem.setChecked(true);
            }

        }
        groupHolder.radioXlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(groupHolder.radioXlc.isChecked()){
                    data.setIsCheckProcess("XLC");
                }else{
                    data.setIsCheckProcess("");
                }
            }
        });
        groupHolder.radioPh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(groupHolder.radioPh.isChecked()){
                    data.setIsCheckProcess("PH");
                }else{
                    data.setIsCheckProcess("");
                }
            }
        });
        groupHolder.radioXem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (groupHolder.radioXem.isChecked()) {
                    data.setIsCheckProcess("XEM");
                } else {
                    data.setIsCheckProcess("");
                }
            }
        });
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.getListChildren().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildHolder childHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_third_unit, parent,false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder)convertView.getTag();
        }
        String text = data.getListChildren().get(childPosition).getName();
        childHolder.rowThirdText.setText(text);
        if (data.getListChildren().get(childPosition).getIsCheckProcess() != null
                && !data.getListChildren().get(childPosition).getIsCheckProcess().equalsIgnoreCase("")) {
            if (data.getListChildren().get(childPosition).getIsCheckProcess().equalsIgnoreCase("XLC")) {
                childHolder.radioXlc.setChecked(true);

            } else if (data.getListChildren().get(childPosition).getIsCheckProcess().equalsIgnoreCase("PH")) {
                childHolder.radioPh.setChecked(true);
            } else {
                childHolder.radioXem.setChecked(true);
            }

        }
        childHolder.radioXlc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(childHolder.radioXlc.isChecked()){
                    data.getListChildren().get(childPosition).setIsCheckProcess("XLC");
                }else{
                    data.getListChildren().get(childPosition).setIsCheckProcess("");
                }
            }
        });
        childHolder.radioPh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(childHolder.radioPh.isChecked()){
                    data.getListChildren().get(childPosition).setIsCheckProcess("PH");
                }else{
                    data.getListChildren().get(childPosition).setIsCheckProcess("");
                }
            }
        });
        childHolder.radioXem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (childHolder.radioXem.isChecked()) {
                    data.getListChildren().get(childPosition).setIsCheckProcess("XEM");
                } else {
                    data.getListChildren().get(childPosition).setIsCheckProcess("");
                }
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.getListChildren() != null ? data.getListChildren().size() : 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupHolder {
        @BindView(R.id.rowSecondText)
        TextView rowSecondText;
        @BindView(R.id.radio_xlc)
        ToggleableRadioButton radioXlc;
        @BindView(R.id.radio_ph)
        ToggleableRadioButton radioPh;
        @BindView(R.id.radio_xem)
        ToggleableRadioButton radioXem;
        @BindView(R.id.radioGroup1)
        RadioGroup radioGroup1;

        GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.rowThirdText)
        TextView rowThirdText;
        @BindView(R.id.radio_xlc)
        ToggleableRadioButton radioXlc;
        @BindView(R.id.radio_ph)
        ToggleableRadioButton radioPh;
        @BindView(R.id.radio_xem)
        ToggleableRadioButton radioXem;
        @BindView(R.id.radioGroup1)
        RadioGroup radioGroup1;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
    }

    }
}