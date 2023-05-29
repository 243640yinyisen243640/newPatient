package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.List;

public class UserFilesFamilyHistoryAdapter extends XyBaseAdapter<BaseLocalDataInfo> {
    private List<BaseLocalDataInfo> list;
    private Context context;

    public UserFilesFamilyHistoryAdapter(Context context, List<BaseLocalDataInfo> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.include_user_files_fami_history, null);
            holder.typeTextView = convertView.findViewById(R.id.tv_user_files_family_hostory);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BaseLocalDataInfo info = getList().get(position);

        holder.typeTextView.setText("子女 无");
        return convertView;
    }

    private class ViewHolder {
        TextView typeTextView;
    }
}
