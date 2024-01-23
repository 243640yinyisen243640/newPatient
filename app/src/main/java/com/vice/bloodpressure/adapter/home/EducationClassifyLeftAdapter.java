package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.EducationInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/28 17:09
 */
public class EducationClassifyLeftAdapter extends XyBaseAdapter<EducationInfo> {


    public EducationClassifyLeftAdapter(Context context, List<EducationInfo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_education_classify_left, null);
            holder.typeTextView = convertView.findViewById(R.id.tv_classify_first_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EducationInfo educationInfo = getList().get(position);
        Log.i("yys", "position==" + position);
        Log.i("yys", "getIsCheck==" + educationInfo.getIsCheck());
        if ( "1".equals(educationInfo.getIsCheck())) {
            holder.typeTextView.setTextSize(16);
            holder.typeTextView.setTypeface(Typeface.DEFAULT_BOLD);
            holder.typeTextView.setBackground(ContextCompat.getDrawable(getContext(), R.color.text_white));
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.education_check);
        } else {
            holder.typeTextView.setTextSize(14);
            holder.typeTextView.setBackground(ContextCompat.getDrawable(getContext(), R.color.defaultBackground));
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.shape_tran_20_3_2);
        }
        holder.typeTextView.setText(educationInfo.getTypeName());
        return convertView;
    }
    private class ViewHolder {
        TextView typeTextView;
    }
}
