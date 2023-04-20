package com.vice.bloodpressure.adapter.login;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.UserInfo;

import java.util.List;

/**
 * Author: LYD
 * Date: 2021/8/12 14:49
 * Description: 疾病类型
 */
public class PerfectDiseaseAdapter extends XyBaseAdapter<UserInfo> {

    private int clickPosition = 0;

    public PerfectDiseaseAdapter(Context context, List<UserInfo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_ferfect_check, null);
            holder.contentTextView = convertView.findViewById(R.id.tv_perfect);
            holder.contentTextView.setOnClickListener(v -> {
                this.clickPosition = position;
                this.notifyDataSetChanged();
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserInfo typeInfo = getList().get(position);
        holder.contentTextView.setText(typeInfo.getUserId());


        if (clickPosition == position) {
            holder.contentTextView.setTextColor(getContext().getResources().getColor(R.color.text_white));
            holder.contentTextView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_bg_main_gra_90));
        } else {
            holder.contentTextView.setTextColor(getContext().getResources().getColor(R.color.black_24));
            holder.contentTextView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_bg_white_black_90_1));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView contentTextView;
    }

    public void setClickPosition(int position) {
        this.clickPosition = position;
    }

    public int getClickPosition() {
        return clickPosition;
    }
}
