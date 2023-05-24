package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ServiceCheckAdapter extends RecyclerView.Adapter<ServiceCheckAdapter.ViewHolder> {
    private Context context;
    private List<HealthyDataChildInfo> list;
    private IAdapterViewClickListener clickListener;

    public ServiceCheckAdapter(Context context, List<HealthyDataChildInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_check, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthyDataChildInfo info = list.get(position);
        XyImageUtils.loadRoundImage(context, R.drawable.choose_pic_default, info.getFileItem(), holder.imgImageView);
        holder.timeTextView.setText(info.getAddTime());
        holder.nameTextView.setText(info.getProjectName());
        clickOnClick clickOnClick = new clickOnClick(position);
        holder.clickLinearLayout.setOnClickListener(clickOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout clickLinearLayout;
        private TextView timeTextView;
        private ImageView imgImageView;
        private TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            clickLinearLayout = itemView.findViewById(R.id.ll_item_service_check_click);
            timeTextView = itemView.findViewById(R.id.tv_item_service_check_time);
            imgImageView = itemView.findViewById(R.id.iv_item_service_check_img);
            nameTextView = itemView.findViewById(R.id.tv_item_service_check_name);
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

