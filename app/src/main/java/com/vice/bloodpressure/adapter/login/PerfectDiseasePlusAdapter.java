package com.vice.bloodpressure.adapter.login;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/4/10 17:11
 */
public class PerfectDiseasePlusAdapter extends XyBaseAdapter<BaseLocalDataInfo> {
    private IAdapterViewClickListener clickListener;

    public PerfectDiseasePlusAdapter(Context context, List<BaseLocalDataInfo> list, IAdapterViewClickListener clickListener) {
        super(context, list);
        this.clickListener = clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_ferfect_check, null);
            holder.contentTextView = convertView.findViewById(R.id.tv_perfect);
            clickOnClick clickOnClick = new clickOnClick(position);
            holder.contentTextView.setOnClickListener(clickOnClick);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BaseLocalDataInfo typeInfo = getList().get(position);
        holder.contentTextView.setText(typeInfo.getName());
        if (typeInfo.getIsCheck()) {
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

    private class clickOnClick implements View.OnClickListener {
        private int position;

        public clickOnClick(int position) {
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
