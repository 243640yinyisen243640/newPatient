package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class ServiceMealAdapter extends RecyclerView.Adapter<ServiceMealAdapter.ViewHolder> {
    private Context context;
    private List<ServiceInfo> list;


    public ServiceMealAdapter(Context context, List<ServiceInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_meal, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceInfo info = list.get(position);
        holder.timeTextView.setText(info.getTime());
        holder.valueTextView.setText(info.getData());
        holder.fireTextView.setText(info.getType());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        ServiceMealChildListAdapter childListAdapter = new ServiceMealChildListAdapter(context, list.get(position).getList());
        holder.childRecyclerView.setAdapter(childListAdapter);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView valueTextView;
        private TextView fireTextView;
        private RecyclerView childRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_meal_time);
            valueTextView = itemView.findViewById(R.id.tv_item_service_meal);
            fireTextView = itemView.findViewById(R.id.tv_item_service_meal_fire);
            childRecyclerView = itemView.findViewById(R.id.rv_item_service_meal_child);
        }
    }


}
