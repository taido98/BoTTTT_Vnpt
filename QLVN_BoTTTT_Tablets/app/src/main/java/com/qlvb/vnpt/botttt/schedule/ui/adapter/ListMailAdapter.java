package com.qlvb.vnpt.botttt.schedule.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.qlvb.vnpt.botttt.schedule.R;
import com.qlvb.vnpt.botttt.schedule.app.utils.AppDef;
import com.qlvb.vnpt.botttt.schedule.domain.model.response.ListDocumentResponse;
import com.qlvb.vnpt.botttt.schedule.ui.activity.XemDanhSachTatCaFileActivity;
import com.qlvb.vnpt.botttt.schedule.ui.fragment.DetailDocumentComingFragment;
import com.qlvb.vnpt.botttt.schedule.ui.fragment.FragmentRightContentComming;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMailAdapter extends ArrayAdapter<ListDocumentResponse.Datum>{
    private final LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper;

    private String type;
    private Context context;
    private FragmentRightContentComming fragmentRightContentComming;

    public ListMailAdapter(Context context, List<ListDocumentResponse.Datum> objects, String type, FragmentRightContentComming fragmentRightContentComming) {
        super(context, R.layout.item_list_mail, objects);
        mInflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
        this.type = type;
        this.context = context;
        this.fragmentRightContentComming = fragmentRightContentComming;
        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_mail, parent, false);

            holder = new ViewHolder(convertView);
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);
            holder.frontView = convertView.findViewById(R.id.front_layout);
            holder.tvNoiDung = (TextView) convertView.findViewById(R.id.tv_noi_dung);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
            holder.btn_luu = convertView.findViewById(R.id.btn_luu);
            holder.btn_huy_luu = convertView.findViewById(R.id.btn_huy_luu);
            holder.btn_xem = convertView.findViewById(R.id.btn_xem);
            holder.btn_huy = convertView.findViewById(R.id.btn_huy);
            holder.btn_chuyen = convertView.findViewById(R.id.btn_chuyen);
            holder.btn_luan_chuyen = convertView.findViewById(R.id.btn_luan_chuyen);
            holder.btn_ky = convertView.findViewById(R.id.btn_ky);

            holder.frontView.setBackgroundColor(getContext().getResources().getColor(R.color.md_white));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final ListDocumentResponse.Datum item = getItem(position);
        if (item != null) {
            binderHelper.bind(holder.swipeLayout, item.getId());
            binderHelper.setOpenOnlyOne(true);

            holder.btn_luu.setVisibility(View.GONE);
            holder.btn_huy_luu.setVisibility(View.GONE);
            holder.btn_xem.setVisibility(View.GONE);
            holder.btn_huy.setVisibility(View.GONE);
            holder.btn_chuyen.setVisibility(View.GONE);
            holder.btn_luan_chuyen.setVisibility(View.GONE);
            holder.btn_ky.setVisibility(View.GONE);

            // end
            if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                checkTypeVanBan(item.getType(), holder);
            } else {
                checkTypeVanBan(type, holder);
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                holder.tvNoiDung.setText(Objects.requireNonNull(getItem(position)).getTrichYeu());
                holder.tvDate.setText(Objects.requireNonNull(getItem(position)).getNgayNhan());
            } else {
                holder.tvNoiDung.setText(getItem(position).getTrichYeu());
                holder.tvDate.setText(getItem(position).getNgayNhan());

            }

            holder.btn_luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }
                    bundle.putString("LOAI_NUT", "luu");
                    detailDocumentComingFragment.setArguments(bundle);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });

            holder.btn_huy_luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }
                    bundle.putString("LOAI_NUT", "huyluu");
                    detailDocumentComingFragment.setArguments(bundle);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });

            holder.btn_chuyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }
                    bundle.putString("LOAI_NUT", "chuyen");
                    detailDocumentComingFragment.setArguments(bundle);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });

            holder.btn_luan_chuyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }
                    bundle.putString("LOAI_NUT", "luanchuyen");
                    detailDocumentComingFragment.setArguments(bundle);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });

            holder.frontView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    String displayText = "" + item.getId() + " clicked";
//                    Toast.makeText(getContext(), displayText, Toast.LENGTH_SHORT).show();

                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }

                    detailDocumentComingFragment.setArguments(bundle);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });

            holder.btn_xem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, XemDanhSachTatCaFileActivity.class);
                    intent.putExtra( "DOC_ID", item.getId());
                    context.startActivity(intent);
                }
            });

            holder.btn_ky.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailDocumentComingFragment detailDocumentComingFragment = new DetailDocumentComingFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("ID_VAN_BAN", item.getId());
                    if (type.equals(AppDef.VAN_BAN_DEN_TAT_CA) || type.equals(AppDef.VAN_BAN_DI_TAT_CA)) {
                        bundle.putString("TYPE_VAN_BAN", item.getType());
                    } else {
                        bundle.putString("TYPE_VAN_BAN", type);
                    }

                    detailDocumentComingFragment.setArguments(bundle);
                    bundle.putString("EVENT_VAN_BAN", AppDef.EVENT_KY);
                    fragmentRightContentComming.replaceFramgment(detailDocumentComingFragment);
                }
            });
        }

        return convertView;
    }






    private class ViewHolder {
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        SwipeRevealLayout swipeLayout;
        View frontView;
        ImageView btn_luu, btn_huy_luu, btn_xem, btn_huy, btn_chuyen, btn_luan_chuyen, btn_ky;

        TextView tvNoiDung, tvDate;
    }

    private void checkTypeVanBan(String type, ViewHolder viewHolder) {
        switch (type) {
            case AppDef.VAN_BAN_DEN:
                viewHolder.btn_luu.setVisibility(View.VISIBLE);
                viewHolder.btn_chuyen.setVisibility(View.VISIBLE);
                viewHolder.btn_luan_chuyen.setVisibility(View.VISIBLE);
                viewHolder.btn_xem.setVisibility(View.VISIBLE);

                break;
            case AppDef.VAN_BAN_DEN_DA_XU_LY:
                viewHolder.btn_luu.setVisibility(View.VISIBLE);
                viewHolder.btn_xem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DEN_DA_LUU:
                viewHolder.btn_huy_luu.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DEN_TAT_CA:

                break;
            case AppDef.VAN_BAN_DI:
                viewHolder.btn_ky.setVisibility(View.VISIBLE);
                viewHolder.btn_chuyen.setVisibility(View.VISIBLE);
                viewHolder.btn_xem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_DA_KY_DUYET:
                viewHolder.btn_xem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_TU_CHOI_KY:
                viewHolder.btn_xem.setVisibility(View.VISIBLE);
                break;
            case AppDef.VAN_BAN_DI_TAT_CA:

                break;
        }
    }


}
