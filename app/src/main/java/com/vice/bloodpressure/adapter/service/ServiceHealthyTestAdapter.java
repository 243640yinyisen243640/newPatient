package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.model.AdvertInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceHealthyTestAdapter extends RecyclerView.Adapter<ServiceHealthyTestAdapter.ViewHolder> {
    private Context context;
    private List<AdvertInfo> list;
    private CallBack callBack;


    public ServiceHealthyTestAdapter(Context context, List<AdvertInfo> list, CallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_healthy_test, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdvertInfo typeInfo = list.get(position);
        holder.sureTextView.setOnClickListener(v -> callBack.callBack(position));
        holder.nameTextView.setText(typeInfo.getText());
        holder.bgTextView.setCompoundDrawablesWithIntrinsicBounds(0, typeInfo.getImgType(), 0, 0);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView sureTextView;
        private TextView bgTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            bgTextView = itemView.findViewById(R.id.tv_service_healthy_test_img);
            nameTextView = itemView.findViewById(R.id.tv_service_healthy_test_name);
            sureTextView = itemView.findViewById(R.id.tv_service_healthy_test_sure);
        }
    }


}

