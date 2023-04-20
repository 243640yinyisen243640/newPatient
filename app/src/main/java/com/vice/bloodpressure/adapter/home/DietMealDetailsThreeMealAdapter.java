package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealExclusiveInfo;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietMealDetailsThreeMealAdapter extends XyBaseAdapter<MealExclusiveInfo> {
    public DietMealDetailsThreeMealAdapter(Context context, List<MealExclusiveInfo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_diet_details_three, null);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_item_diet_details_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MealExclusiveInfo info = getList().get(position);
        viewHolder.nameTextView.setText(info.getRecName());
        return convertView;
    }

    private static class ViewHolder {
        TextView nameTextView;
    }
}
