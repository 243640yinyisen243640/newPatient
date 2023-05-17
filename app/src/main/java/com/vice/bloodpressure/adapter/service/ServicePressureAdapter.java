package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.model.HealthyDataChildInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServicePressureAdapter extends RecyclerView.Adapter<ServicePressureAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataChildInfo> list;


    public ServicePressureAdapter(Context context, List<HealthyDataChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_pressure, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataChildInfo info = list.get(position);
        SimpleDateFormat sdf1 = new SimpleDateFormat(DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DataFormatManager.TIME_FORMAT_Y_M_D_H_M);
        Date date = null;
        try {
            date = sdf1.parse(info.getAddTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = sdf2.format(date);
        holder.timeTextView.setText(format);
        holder.valueTextView.setText(info.getSbp() + "/" + info.getDbp());
        holder.rateTextView.setText(info.getHr());
        holder.typeTextView.setText("1".equals(info.getRecordType()) ? "自动" : "手动");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView valueTextView;
        private TextView rateTextView;
        private TextView typeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_pressure_time);
            valueTextView = itemView.findViewById(R.id.tv_item_service_pressure_value);
            rateTextView = itemView.findViewById(R.id.tv_item_service_pressure_rate);
            typeTextView = itemView.findViewById(R.id.tv_item_service_pressure_type);

        }
    }


}

