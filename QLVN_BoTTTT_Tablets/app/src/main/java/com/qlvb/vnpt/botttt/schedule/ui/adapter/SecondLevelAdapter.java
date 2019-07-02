package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;

import java.util.List;

import io.realm.RealmResults;


public class SecondLevelAdapter extends BaseExpandableListAdapter {

    private Context context;


    List<RealmResults<UnitObject>> data;

    RealmResults<UnitObject> headers;


    public SecondLevelAdapter(Context context, RealmResults<UnitObject> headers, List<RealmResults<UnitObject>> data) {
        this.context = context;
        this.data = data;
        this.headers = headers;
    }

    @Override
    public Object getGroup(int groupPosition) {

        return headers.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return headers.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_row_second_unit, null);
        TextView text = (TextView) convertView.findViewById(R.id.rowSecondText);
        String groupText = ((UnitObject)getGroup(groupPosition)).getName();
        text.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.mipmap.icon_collapse : R.mipmap.icon_expand, 0, 0, 0);
        text.setText(groupText);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        RealmResults<UnitObject> childData;

        childData = data.get(groupPosition);


        return childData.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_row_third_unit, null);

        TextView textView = (TextView) convertView.findViewById(R.id.rowThirdText);

        RealmResults<UnitObject> childArray = data.get(groupPosition);

        String text = childArray.get(childPosition).getName();

        textView.setText(text);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        RealmResults<UnitObject> children = data.get(groupPosition);


        return children.size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}