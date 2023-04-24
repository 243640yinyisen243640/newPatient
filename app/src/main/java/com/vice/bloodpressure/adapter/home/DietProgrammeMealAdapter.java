package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealInfo;

import java.util.List;

public class DietProgrammeMealAdapter extends XyBaseAdapter<MealInfo> {
    private List<MealInfo> list;
    private Context context;

    public DietProgrammeMealAdapter(Context context, List<MealInfo> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_diet_meal_list_with_line, null);
            holder.coverImageView = convertView.findViewById(R.id.iv_meal_details_cover);
            holder.nameTextView = convertView.findViewById(R.id.tv_meal_details_name);
            holder.numTextView = convertView.findViewById(R.id.tv_meal_details_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealInfo info = getList().get(position);
        holder.nameTextView.setText(info.getTitle());
        holder.numTextView.setText(info.getTitle());
        return convertView;
    }

    private class ViewHolder {
        ImageView coverImageView;
        TextView nameTextView;
        TextView numTextView;
    }
}
