package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealIngMapInfo;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietHeatProportionAdapter extends XyBaseAdapter<MealIngMapInfo> {
    private int[] intArray;

    public DietHeatProportionAdapter(Context context, List<MealIngMapInfo> list, int[] intArray) {
        super(context, list);
        this.intArray = intArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_diet_heat_proportion, null);
            holder.view = convertView.findViewById(R.id.v_diet_heat_proportion);
            holder.contentTextView = convertView.findViewById(R.id.tv_diet_heat_proportion_name);
            holder.proportionTextView = convertView.findViewById(R.id.tv_diet_heat_proportion_p);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.view.setBackgroundColor(intArray[position % intArray.length]);
        MealIngMapInfo typeInfo = getList().get(position);
        holder.contentTextView.setText(typeInfo.getName());

        holder.proportionTextView.setText(typeInfo.getCalorieRatio() + "%");
        return convertView;
    }

    private class ViewHolder {
        View view;
        TextView contentTextView;
        TextView proportionTextView;
    }

}
