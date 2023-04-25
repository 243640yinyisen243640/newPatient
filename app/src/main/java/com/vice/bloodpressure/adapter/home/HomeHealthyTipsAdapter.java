package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealInfo;

import java.util.List;

public class HomeHealthyTipsAdapter extends XyBaseAdapter<MealInfo> {

    public HomeHealthyTipsAdapter(Context context, List<MealInfo> list) {
        super(context, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_home_healthy_tip, null);
            holder.nameTextView = convertView.findViewById(R.id.tv_healthy_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealInfo info = getList().get(position);
        holder.nameTextView.setText(info.getTitle());
        return convertView;
    }

    private class ViewHolder {
        TextView nameTextView;
    }
}
