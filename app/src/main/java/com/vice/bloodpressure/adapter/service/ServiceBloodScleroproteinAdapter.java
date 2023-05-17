package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.HealthyDataChildInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceBloodScleroproteinAdapter extends RecyclerView.Adapter<ServiceBloodScleroproteinAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataChildInfo> list;


    public ServiceBloodScleroproteinAdapter(Context context, List<HealthyDataChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_blood_scleroprotein, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataChildInfo info = list.get(position);
        holder.timeTextView.setText(info.getAddTime());
        holder.valueTextView.setText(info.getBgValue() + "%");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView valueTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_blood_scleroprotein_time);
            valueTextView = itemView.findViewById(R.id.tv_item_service_blood_scleroprotein_value);

        }
    }


}

