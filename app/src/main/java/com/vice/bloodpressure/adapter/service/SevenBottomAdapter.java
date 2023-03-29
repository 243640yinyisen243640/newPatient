package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.ServiceInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class SevenBottomAdapter extends RecyclerView.Adapter<SevenBottomAdapter.ViewHolder> {
    private Context context;
    private List<ServiceInfo> list;
    private IAdapterViewClickListener clickListener;


    public SevenBottomAdapter(Context context, List<ServiceInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
//        LayoutInflater.from(context).inflate(R.layout.item_service_blood_list,parent,false)
        View v = View.inflate(context, R.layout.item_service_blood_list, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceInfo info = list.get(position);

        holder.timeTextView.setText(info.getTime());
        holder.dawnTextView.setText(info.getData());
        holder.breLaterTextView.setText(info.getData());
        holder.empTextView.setText(info.getData());
        holder.lunchBeforeTextView.setText(info.getData());
        holder.lunchLaterTextView.setText(info.getData());
        holder.dinnerBeforeTextView.setText(info.getData());
        holder.dinnerLaterTextView.setText(info.getData());
        holder.breLaterTextView.setText(info.getData());
        holder.bedTimeTextView.setText(info.getData());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        private TextView dawnTextView;
        private ImageView dawnImageView;
        private TextView empTextView;
        private ImageView empImageView;
        private TextView breLaterTextView;
        private ImageView breLaterImageView;
        private TextView lunchBeforeTextView;
        private ImageView lunchBeforeImageView;
        private TextView lunchLaterTextView;
        private ImageView lunchLaterImageView;
        private TextView dinnerBeforeTextView;
        private ImageView dinnerBeforeImageView;
        private TextView dinnerLaterTextView;
        private ImageView dinnerLaterImageView;
        private TextView bedTimeTextView;
        private ImageView bedTimeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_time);

            dawnTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dawn);
            dawnImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dawn);

            empTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bre_emp);
            empImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bre_emp);

            breLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bre_later);
            breLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bre_later);

            lunchBeforeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_lunch_before);
            lunchBeforeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_lunch_before);

            lunchLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_lunch_later);
            lunchLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_lunch_later);

            dinnerBeforeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dinner_before);
            dinnerBeforeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dinner_before);

            dinnerLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dinner_later);
            dinnerLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dinner_later);

            bedTimeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bed_time);
            bedTimeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bed_time);

        }
    }

    private class DoctorInfoOnClick implements View.OnClickListener {
        private int position;

        public DoctorInfoOnClick(int position) {
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

