package com.qlvb.vnpt.botttt.schedule.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.BaseFragment;
import com.qlvb.vnpt.botttt.schedule.domain.model.ExpandableListDataPump;
import com.qlvb.vnpt.botttt.schedule.domain.model.ExpandableTitleTypeModel;
import com.qlvb.vnpt.botttt.schedule.ui.adapter.DetailExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentDocumentDetail extends BaseFragment {
    Unbinder unbinder;

    DetailExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    List<ExpandableTitleTypeModel> expandableTitleTypeModelList;
    HashMap<String, List<?>> expandableListDetail;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableTitleTypeModelList = new ArrayList<>();
        for(int i = 0; i < expandableListTitle.size(); i++){
            switch (i){
                case 0:
                    expandableTitleTypeModelList.add(new ExpandableTitleTypeModel(expandableListTitle.get(i), 2));
                    break;
                case 1:
                    expandableTitleTypeModelList.add(new ExpandableTitleTypeModel(expandableListTitle.get(i), 1));
                    break;
                case 2:
                    expandableTitleTypeModelList.add(new ExpandableTitleTypeModel(expandableListTitle.get(i), 3));
                    break;
                default:
                    expandableTitleTypeModelList.add(new ExpandableTitleTypeModel(expandableListTitle.get(i), 2));
                    break;
            }
        }
        expandableListAdapter = new DetailExpandableListAdapter(getActivity(), expandableTitleTypeModelList, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        // add header view
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.header_expend_type_1, null);
        expandableListView.addHeaderView(view);
        // end
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
