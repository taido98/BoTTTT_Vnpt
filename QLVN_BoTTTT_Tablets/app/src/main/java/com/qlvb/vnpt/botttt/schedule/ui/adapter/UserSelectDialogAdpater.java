package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.app.Activity;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.realm.RealmController;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;
import com.qlvb.vnpt.botttt.schedule.ui.view.ToggleableRadioButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by linhl on 8/30/2018.
 */

public class UserSelectDialogAdpater extends BaseAdapter {
    List<? extends RealmObject> userList;
    private Activity context;
    private int selectedOne = -1;
    private int selectedTwo = -1;
    private int selectedThree = -1;
    SparseIntArray mCheckStates;
    RealmController realmController;

    public UserSelectDialogAdpater(List<? extends RealmObject> userList, Activity context, RealmController realm) {
        this.userList = userList;
        this.context = context;
        mCheckStates = new SparseIntArray(userList.size());
        realmController = realm;
    }

    public List<? extends RealmObject> getUserList() {
        return userList;
    }

    public void setUserList(List<? extends RealmObject> userList) {
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList == null ? 0 : userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_select, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (userList.get(position) instanceof UserObject) {
            viewHolder.tvUserName.setText((position + 1) + "." + ((UserObject) userList.get(position)).getName());
            try {
                viewHolder.tvUserPositive.setText(realmController.getUnitById(((UserObject) userList.get(position)).getUnitId()).getName());
            } catch (Exception e) {
                e.printStackTrace();
                viewHolder.tvUserPositive.setText("");
            }

            viewHolder.radioGroup1.clearCheck();
            if (((UserObject) userList.get(position)).getIsCheckProcess() != null && !((UserObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("")) {
                if (((UserObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("XLC")) {
                    viewHolder.radioXlc.setChecked(true);

                } else if (((UserObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("PH")) {
                    viewHolder.radioPh.setChecked(true);
                } else {
                    viewHolder.radioXem.setChecked(true);
                }

            }
            viewHolder.radioXlc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXlc.isChecked()) {
                        ((UserObject) userList.get(position)).setIsCheckProcess("XLC");
                    } else {
                        ((UserObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioPh.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioPh.isChecked()) {
                        ((UserObject) userList.get(position)).setIsCheckProcess("PH");
                    } else {
                        ((UserObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioXem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXem.isChecked()) {
                        ((UserObject) userList.get(position)).setIsCheckProcess("XEM");
                    } else {
                        ((UserObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
        } else if (userList.get(position) instanceof GroupObject) {
            viewHolder.tvUserName.setText((position + 1) + "." + ((GroupObject) userList.get(position)).getName());
            viewHolder.radioGroup1.clearCheck();
            viewHolder.radioXlc.setVisibility(View.GONE);
            if (((GroupObject) userList.get(position)).getIsCheckProcess() != null &&
                    !((GroupObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("")) {
                if (((GroupObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("PH")) {
                    viewHolder.radioPh.setChecked(true);
                } else {
                    viewHolder.radioXem.setChecked(true);
                }

            }
            viewHolder.radioPh.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioPh.isChecked()) {
                        ((GroupObject) userList.get(position)).setIsCheckProcess("PH");
                    } else {
                        ((GroupObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioXem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXem.isChecked()) {
                        ((GroupObject) userList.get(position)).setIsCheckProcess("XEM");
                    } else {
                        ((GroupObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
        } else if (userList.get(position) instanceof MinisterObject) {
            viewHolder.tvUserName.setText((position + 1) + "." + ((MinisterObject) userList.get(position)).getFullName());
            viewHolder.tvUserPositive.setText(((MinisterObject) userList.get(position)).getUnitName());
            viewHolder.radioGroup1.clearCheck();
            viewHolder.radioXem.setVisibility(View.GONE);
            if (((MinisterObject) userList.get(position)).getIsCheckProcess() != null &&
                    !((MinisterObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("")) {
                if (((MinisterObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("XLC")) {
                    viewHolder.radioXlc.setChecked(true);

                } else if (((MinisterObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("PH")) {
                    viewHolder.radioPh.setChecked(true);
                }

            }
            viewHolder.radioPh.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioPh.isChecked()) {
                        ((MinisterObject) userList.get(position)).setIsCheckProcess("PH");
                    } else {
                        ((MinisterObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioXlc.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXlc.isChecked()) {
                        ((MinisterObject) userList.get(position)).setIsCheckProcess("XLC");
                    } else {
                        ((MinisterObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
        }
        else if (userList.get(position) instanceof UnitObject) {
            viewHolder.tvUserName.setText((position + 1) + "." + ((UnitObject) userList.get(position)).getName());
            try {
                viewHolder.tvUserPositive.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                viewHolder.tvUserPositive.setText("");
            }

            viewHolder.radioGroup1.clearCheck();
            if (((UnitObject) userList.get(position)).getIsCheckProcess() != null && !((UnitObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("")) {
                if (((UnitObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("XLC")) {
                    viewHolder.radioXlc.setChecked(true);

                } else if (((UnitObject) userList.get(position)).getIsCheckProcess().equalsIgnoreCase("PH")) {
                    viewHolder.radioPh.setChecked(true);
                } else {
                    viewHolder.radioXem.setChecked(true);
                }

            }
            viewHolder.radioXlc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXlc.isChecked()) {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("XLC");
                    } else {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioPh.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioPh.isChecked()) {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("PH");
                    } else {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
            viewHolder.radioXem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (viewHolder.radioXem.isChecked()) {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("XEM");
                    } else {
                        ((UnitObject) userList.get(position)).setIsCheckProcess("");
                    }
                }
            });
        }


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_positive)
        TextView tvUserPositive;
        @BindView(R.id.radio_xlc)
        ToggleableRadioButton radioXlc;
        @BindView(R.id.radio_ph)
        ToggleableRadioButton radioPh;
        @BindView(R.id.radio_xem)
        ToggleableRadioButton radioXem;
        @BindView(R.id.radioGroup1)
        RadioGroup radioGroup1;
        @BindView(R.id.ll_item_user)
        LinearLayout llTtemUser;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void updateData(List<? extends RealmObject> userListUpdate) {
        userList = userListUpdate;
        if (mCheckStates != null) {
            mCheckStates.clear();
        }
        notifyDataSetChanged();
    }
}
