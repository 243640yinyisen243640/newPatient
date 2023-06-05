package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.DiseaseInfo;

import java.util.List;

public class UserFilesFamilyHistoryAdapter extends XyBaseAdapter<DiseaseInfo> {
    private List<DiseaseInfo> list;
    private Context context;

    public UserFilesFamilyHistoryAdapter(Context context, List<DiseaseInfo> list) {
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
        DiseaseInfo info = getList().get(position);

        holder.typeTextView.setText(info.getFamilyDec() + " " + info.getIsContain());
        return convertView;
    }

    private class ViewHolder {
        TextView typeTextView;
    }
}
