package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DataUtils;

import java.util.List;

/**
 * 类名：
 * 传参：type  1:用药记录
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceMedicineAdapter extends RecyclerView.Adapter<ServiceMedicineAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataChildInfo> list;
    private IAdapterViewClickListener clickListener;
    private String type;


    public ServiceMedicineAdapter(Context context, List<HealthyDataChildInfo> list, String type, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_medicine, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataChildInfo info = list.get(position);

        holder.timeTextView.setText(DataUtils.changeDataFormat(DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, DataFormatManager.TIME_FORMAT_Y_M_D, info.getAddTime()));
        holder.nameTextView.setText(info.getDrugName());
        holder.rateTextView.setText(info.getDrugTimes());
        holder.valueTextView.setText(info.getDrugDose());
        if ("1".equals(type)) {
            holder.editTextView.setText("复制");
        } else {
            holder.editTextView.setText("编辑");
        }
        clickOnClick clickOnClick = new clickOnClick(position);
        holder.deleteTextView.setOnClickListener(clickOnClick);
        holder.editTextView.setOnClickListener(clickOnClick);
        holder.lookTextView.setOnClickListener(clickOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView nameTextView;
        private TextView rateTextView;
        private TextView valueTextView;
        private TextView deleteTextView;
        private TextView editTextView;
        private TextView lookTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_item_service_medicine_name);
            timeTextView = itemView.findViewById(R.id.tv_item_service_medicine_time);
            rateTextView = itemView.findViewById(R.id.tv_item_service_medicine_rate);
            valueTextView = itemView.findViewById(R.id.tv_item_service_medicine_value);
            deleteTextView = itemView.findViewById(R.id.tv_item_service_medicine_delete);
            editTextView = itemView.findViewById(R.id.tv_item_service_medicine_edit);
            lookTextView = itemView.findViewById(R.id.tv_item_service_medicine_look);
        }
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

