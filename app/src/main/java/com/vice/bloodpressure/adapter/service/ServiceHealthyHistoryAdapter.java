package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.HealthyDataAllInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceHealthyHistoryAdapter extends RecyclerView.Adapter<ServiceHealthyHistoryAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataAllInfo> list;
    private IAdapterViewClickListener clickListener;


    public ServiceHealthyHistoryAdapter(Context context, List<HealthyDataAllInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_test_history, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataAllInfo typeInfo = list.get(position);
//        holder.titleTextView.setText(typeInfo.getType());
        //        holder.timeTextView.setText(typeInfo.getTime());
        //        holder.numTextView.setText(typeInfo.getData());
        //        holder.resultTextView.setText(typeInfo.getRate());
        ClickOnClick clickOnClick = new ClickOnClick(position);
        holder.lookResultTextView.setOnClickListener(clickOnClick);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView timeTextView;
        private TextView numTextView;
        private TextView resultTextView;
        private TextView lookResultTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_service_history_title);
            timeTextView = itemView.findViewById(R.id.tv_service_history_time);
            numTextView = itemView.findViewById(R.id.tv_service_history_num);
            resultTextView = itemView.findViewById(R.id.tv_service_history_result);
            lookResultTextView = itemView.findViewById(R.id.tv_service_history_look_result);
        }
    }

    private class ClickOnClick implements View.OnClickListener {
        private int position;

        public ClickOnClick(int position) {
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

