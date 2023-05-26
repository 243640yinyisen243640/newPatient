package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.UserInfo;

import java.util.List;

public class UserFilesPlusAdapter extends XyBaseAdapter<UserInfo> {
    private List<UserInfo> list;
    private Context context;

    public UserFilesPlusAdapter(Context context, List<UserInfo> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.include_user_files_ill, null);
            holder.typeTextView = convertView.findViewById(R.id.tv_user_files_ill_name);
            holder.timeTextView = convertView.findViewById(R.id.tv_user_files_ill_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        UserInfo info = getList().get(position);

        holder.typeTextView.setText("糖尿病");
        holder.timeTextView.setText("2023-10-27");
        return convertView;
    }

    private class ViewHolder {
        TextView typeTextView;
        TextView timeTextView;
    }
}
