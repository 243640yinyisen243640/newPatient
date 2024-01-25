package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.MessageInfo;

import java.util.List;

public class HomeHealthyTipsAdapter extends XyBaseAdapter<MessageInfo> {
    private IAdapterViewClickOneListener clickOneListener;

    public HomeHealthyTipsAdapter(Context context, List<MessageInfo> list, IAdapterViewClickOneListener clickOneListener) {
        super(context, list);
        this.clickOneListener = clickOneListener;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_home_healthy_tip, null);
            holder.nameTextView = convertView.findViewById(R.id.tv_healthy_content);
            holder.clickInfoTextView = convertView.findViewById(R.id.tv_healthy_content_click);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MessageInfo info = getList().get(position);
        //  血糖目标：1
        //   血压目标：2
        //     用药提醒：3
        //    运动目标：4
        if ("1".equals(info.getTagType())) {
            holder.nameTextView.setText("血糖目标:" + info.getTagData());
        } else if ("2".equals(info.getTagType())) {
            holder.nameTextView.setText("血压目标:" + info.getTagData());
        } else if ("3".equals(info.getTagType())) {
            holder.nameTextView.setText("用药提醒:" + info.getTagData());
        } else {
            holder.nameTextView.setText("运动目标:" + info.getTagData());
        }
        ClickOnClick clickOnClick = new ClickOnClick(position);
        holder.clickInfoTextView.setOnClickListener(clickOnClick);
        return convertView;
    }

    private class ViewHolder {
        TextView nameTextView;
        TextView clickInfoTextView;
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
