package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.model.AdvertInfo;

import java.util.List;

/**
 * 类名：
 * 传参：type  1:服务首页  2：健康数据
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/3/23 11:29
 */
public class SerciveDataShowAdapter extends XyBaseAdapter<AdvertInfo> {

    private CallBack callBack;
    private String type;

    public SerciveDataShowAdapter(Context context, List<AdvertInfo> list, String type, CallBack callBack) {
        super(context, list);
        this.callBack = callBack;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_sercice_data, null);
            holder.contentTextView = convertView.findViewById(R.id.tv_service_data_show);
            holder.contentTextView.setOnClickListener(v -> {
                callBack.callBack(position);
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AdvertInfo typeInfo = getList().get(position);
        if ("1".equals(type)) {
            holder.contentTextView.setTextColor(Color.parseColor("#4A4A4A"));
        } else {
            holder.contentTextView.setTextColor(Color.parseColor("#555555"));
        }
        holder.contentTextView.setText(typeInfo.getText());
        holder.contentTextView.setCompoundDrawablesWithIntrinsicBounds(0, typeInfo.getImgType(), 0, 0);
        return convertView;
    }

    private class ViewHolder {
        TextView contentTextView;
    }

}
