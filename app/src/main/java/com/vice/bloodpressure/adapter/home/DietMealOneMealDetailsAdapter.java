package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class DietMealOneMealDetailsAdapter extends XyBaseAdapter<MealExclusiveInfo> {
    public DietMealOneMealDetailsAdapter(Context context, List<MealExclusiveInfo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_diet_meal_list, null);
            viewHolder.coverImageView = convertView.findViewById(R.id.iv_meal_details_cover);
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_meal_details_name);
            viewHolder.numTextView = convertView.findViewById(R.id.tv_meal_details_num);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MealExclusiveInfo info = getList().get(position);
        //        XyImageUtils.loadRoundImage(context, R.drawable.diet_guogai_gray, info.getImg(), holder.coverImageView);
        viewHolder.nameTextView.setText(info.getRecName());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < info.getIngMap().size(); i++) {
            builder.append(info.getIngMap().get(i).getName());
            builder.append(info.getIngMap().get(i).getIngK() + "g");
            builder.append(";");
        }
        builder.deleteCharAt(builder.length() - 1);
        viewHolder.numTextView.setText(builder.toString());
        return convertView;
    }

    private static class ViewHolder {
        ImageView coverImageView;
        TextView nameTextView;
        TextView numTextView;
    }
}
