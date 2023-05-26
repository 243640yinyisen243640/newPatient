package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealChildInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceChooseMealListAdapter extends XyBaseAdapter<MealChildInfo> {
    private Context context;


    public ServiceChooseMealListAdapter(Context context, List<MealChildInfo> list) {
        super(context, list);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_service_meal_list_check, null);
            holder.clickLinearLayout = convertView.findViewById(R.id.ll_service_meal_choose_click);
            holder.nameTextView = convertView.findViewById(R.id.tv_service_meal_choose_name);
            holder.checkTextView = convertView.findViewById(R.id.tv_service_meal_choose_check);
            holder.numTextView = convertView.findViewById(R.id.tv_service_meal_choose_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealChildInfo info = getList().get(position);
        holder.nameTextView.setText(info.getFoodname());
        holder.numTextView.setText(info.getKcalval() + "千卡/" + info.getFixedWeight() + "g");
        if (info.isCheck()) {
            holder.checkTextView.setBackground(ContextCompat.getDrawable(context, R.drawable.check_rectangle_check));
        } else {
            holder.checkTextView.setBackground(ContextCompat.getDrawable(context, R.drawable.check_rectangle_unchceck));
        }
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout clickLinearLayout;
        TextView nameTextView;
        TextView numTextView;
        TextView checkTextView;
    }


}

