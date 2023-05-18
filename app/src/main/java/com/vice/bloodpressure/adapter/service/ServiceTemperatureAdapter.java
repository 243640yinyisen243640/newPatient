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
public class ServiceTemperatureAdapter extends RecyclerView.Adapter<ServiceTemperatureAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataChildInfo> list;


    public ServiceTemperatureAdapter(Context context, List<HealthyDataChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_temperature, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataChildInfo info = list.get(position);
        holder.timeTextView.setText(info.getAddTime());
        holder.valueTextView.setText(info.getTemp());
        holder.typeTextView.setText("1".equals(info.getRecordType()) ? "自动上传" : "手动上传");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView valueTextView;
        private TextView typeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_temperature_time);
            valueTextView = itemView.findViewById(R.id.tv_item_service_temperature_value);
            typeTextView = itemView.findViewById(R.id.tv_item_service_temperature_type);

        }
    }


}

