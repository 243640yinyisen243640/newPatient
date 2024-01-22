package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealIngMapInfo;

import java.util.List;

public class ServiceOneMealDetailsAdapter extends XyBaseAdapter<MealIngMapInfo> {
    private List<MealIngMapInfo> list;
    private Context context;

    public ServiceOneMealDetailsAdapter(Context context, List<MealIngMapInfo> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_diet_one_meal_details, null);
            holder.nameTextView = convertView.findViewById(R.id.tv_meal_details_ingredients_name);
            holder.numTextView = convertView.findViewById(R.id.tv_meal_details_ingredients_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealIngMapInfo info = getList().get(position);
        holder.nameTextView.setText(info.getName());
        holder.numTextView.setText(info.getIngK() + "g");
        return convertView;
    }

    private class ViewHolder {
        TextView nameTextView;
        TextView numTextView;
    }
}
