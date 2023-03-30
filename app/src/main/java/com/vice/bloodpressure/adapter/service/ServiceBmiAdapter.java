package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.ServiceInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceBmiAdapter extends RecyclerView.Adapter<ServiceBmiAdapter.ViewHolder> {
    private Context context;
    private List<ServiceInfo> list;


    public ServiceBmiAdapter(Context context, List<ServiceInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_bmi, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceInfo info = list.get(position);
        holder.timeTextView.setText(info.getTime());
        holder.heightTextView.setText(info.getRate()+"cm");
        holder.weightTextView.setText(info.getType()+"kg");
        holder.valueTextView.setText(info.getData());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView heightTextView;
        private TextView weightTextView;
        private TextView valueTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_bmi_time);
            heightTextView = itemView.findViewById(R.id.tv_item_service_bmi_height);
            weightTextView = itemView.findViewById(R.id.tv_item_service_bmi_weight);
            valueTextView = itemView.findViewById(R.id.tv_item_service_bmi_value);

        }
    }


}

