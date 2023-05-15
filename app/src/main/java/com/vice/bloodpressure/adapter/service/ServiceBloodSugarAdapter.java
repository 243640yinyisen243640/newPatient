package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.model.BloodThirdInfo;

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
public class ServiceBloodSugarAdapter extends RecyclerView.Adapter<ServiceBloodSugarAdapter.ViewHolder> {
    private Context context;
    private List<BloodThirdInfo> list;


    public ServiceBloodSugarAdapter(Context context, List<BloodThirdInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_blood_sugar_more, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodThirdInfo info = list.get(position);
        SimpleDateFormat sdf1 = new SimpleDateFormat(DataFormatManager.TIME_FORMAT_Y_M_D);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DataFormatManager.TIME_FORMAT_M_D);
        Date date = null;
        try {
            date = sdf1.parse(info.getAddTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String format = sdf2.format(date);
        holder.timeTextView.setText(format);
        holder.valueTextView.setText(info.getBgValue());
        //记录方式:1->自动记录;2->手动记录
        if ("1".equals(info.getRecordType())) {
            holder.typeTextView.setText("自动上传");
        } else {
            holder.typeTextView.setText("手动上传");
        }
        // 血糖状态 1偏低 2正常 3偏高
        if ("1".equals(info.getBgStatus())) {
            holder.valueTextView.setTextColor(context.getResources().getColor(R.color.blue_4B));
        }else if ("2".equals(info.getBgStatus())){
            holder.valueTextView.setTextColor(context.getResources().getColor(R.color.main_base_color));
        }else {
            holder.valueTextView.setTextColor(context.getResources().getColor(R.color.red_E5));
        }
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
            timeTextView = itemView.findViewById(R.id.tv_service_sugar_more_time);
            valueTextView = itemView.findViewById(R.id.tv_service_sugar_more_value);
            typeTextView = itemView.findViewById(R.id.tv_service_sugar_more_type);
        }
    }


}

