package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietMealOneMealDetailsAdapter extends XyBaseAdapter<MealExclusiveInfo> {
    private String type;
    private IAdapterViewClickOneListener clickOneListener;

    public DietMealOneMealDetailsAdapter(Context context, List<MealExclusiveInfo> list, String type, IAdapterViewClickOneListener clickOneListener) {
        super(context, list);
        this.type = type;
        this.clickOneListener = clickOneListener;
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
            viewHolder.clickLin = convertView.findViewById(R.id.ll_meal_details_click);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MealExclusiveInfo info = getList().get(position);
        XyImageUtils.loadRoundImage(getContext(), R.drawable.diet_guogai_gray, info.getCoverUrl(), viewHolder.coverImageView);
        viewHolder.nameTextView.setText(info.getRecName());
        StringBuilder builder = new StringBuilder();
        if ("4".equals(type)) {
            for (int i = 0; i < info.getIngData().size(); i++) {
                builder.append(info.getIngData().get(i).getName());
                builder.append(info.getIngData().get(i).getIngK() + "g");
                builder.append(";");
            }
            builder.deleteCharAt(builder.length() - 1);
        } else {
            for (int i = 0; i < info.getIngMap().size(); i++) {
                builder.append(info.getIngMap().get(i).getName());
                builder.append(info.getIngMap().get(i).getIngK() + "g");
                builder.append(";");
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        viewHolder.numTextView.setText(builder.toString());

        ClickOnClick clickOnClick =new ClickOnClick(position);
        viewHolder.clickLin.setOnClickListener(clickOnClick);
        return convertView;
    }

    private static class ViewHolder {
        ImageView coverImageView;
        TextView nameTextView;
        TextView numTextView;
        LinearLayout clickLin;
    }
    private class ClickOnClick implements View.OnClickListener {
        private int position;

        public ClickOnClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickOneListener != null) {
                clickOneListener.adapterClickListener(position, v);
            }
        }
    }

}
