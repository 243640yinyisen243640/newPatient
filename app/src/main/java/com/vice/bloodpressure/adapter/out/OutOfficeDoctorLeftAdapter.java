package com.vice.bloodpressure.adapter.out;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.DoctorInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/28 17:09
 */
public class OutOfficeDoctorLeftAdapter extends XyBaseAdapter<DoctorInfo> {
    private Context context;

    public OutOfficeDoctorLeftAdapter(Context context, List<DoctorInfo> list) {
        super(context, list);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_out_office_left, null);
            holder.typeTextView = convertView.findViewById(R.id.tv_out_office_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DoctorInfo educationInfo = getList().get(position);
        if ("1".equals(educationInfo.getIsCheck())) {
            holder.typeTextView.setTextSize(16);
            holder.typeTextView.setTypeface(Typeface.DEFAULT_BOLD);
            holder.typeTextView.setTextColor(ContextCompat.getColor(context, R.color.main_base_color));
            holder.typeTextView.setBackground(ContextCompat.getDrawable(context, R.color.text_white));
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_green_4_18_2, 0, 0, 0);
        } else {
            holder.typeTextView.setTextSize(14);
            holder.typeTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.typeTextView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.typeTextView.setTextColor(ContextCompat.getColor(context, R.color.black_24));
            holder.typeTextView.setBackground(ContextCompat.getDrawable(context, R.color.defaultBackground));
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_tran_20_3_2, 0, 0, 0);
        }
        holder.typeTextView.setText(educationInfo.getDeptName());
        return convertView;
    }

    private class ViewHolder {
        TextView typeTextView;
    }
}
