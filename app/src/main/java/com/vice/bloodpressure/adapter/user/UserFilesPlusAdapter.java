package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.DiseaseInfo;

import java.util.List;

public class UserFilesPlusAdapter extends XyBaseAdapter<DiseaseInfo> {
    private IAdapterViewClickOneListener clickListener;
    /**
     * 1：主要诊断  2其他诊断  3：合并症
     */
    private String type;

    public UserFilesPlusAdapter(Context context, List<DiseaseInfo> list, String type, IAdapterViewClickOneListener clickListener) {
        super(context, list);
        this.clickListener = clickListener;
        this.type = type;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.include_user_files_ill, null);
            holder.typeTextView = convertView.findViewById(R.id.tv_user_files_ill_name);
            holder.timeTextView = convertView.findViewById(R.id.tv_user_files_ill_time);
            holder.clickLinearLayout = convertView.findViewById(R.id.ll_disease_click);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        DiseaseInfo info = getList().get(position);
        if ("1".equals(type)) {
            holder.timeTextView.setText(info.getComplicationDate());
            holder.typeTextView.setText(info.getComplicationName());
        } else {
            setTextName(holder, info);
        }
        ClickOnClick clickOnClick = new ClickOnClick(position);
        holder.clickLinearLayout.setOnClickListener(clickOnClick);

        return convertView;
    }

    private void setTextName(ViewHolder holder, DiseaseInfo info) {
        switch (info.getDiseaseType()) {
            case "1":
                holder.typeTextView.setText("糖尿病");
                break;
            case "2":
                holder.typeTextView.setText("高血压");
                break;
            case "3":
                holder.typeTextView.setText("糖尿病前期");
                break;
            case "4":
                holder.typeTextView.setText("冠心病");
                break;
            case "5":
                holder.typeTextView.setText("脑卒中");
                break;
            case "6":
                holder.typeTextView.setText("慢性阻塞性肺疾病");
                break;
            default:
                break;
        }
        holder.timeTextView.setText(info.getDiagnoseDate());
    }

    private class ViewHolder {
        TextView typeTextView;
        TextView timeTextView;
        LinearLayout clickLinearLayout;
    }

    private class ClickOnClick implements View.OnClickListener {
        private int position;

        public ClickOnClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.adapterClickListener(position, v);
            }
        }
    }


}
