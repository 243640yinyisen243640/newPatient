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
import com.vice.bloodpressure.model.BloodChildInfo;

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
    private List<BloodChildInfo> list;
    private IAdapterViewClickListener clickListener;


    public SevenBottomAdapter(Context context, List<BloodChildInfo> list, IAdapterViewClickListener clickListener) {
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

        BloodChildInfo info = list.get(position);
        String paraStr = info.getDate().substring(info.getDate().indexOf("-") + 1);
        String[] paraStrs = paraStr.split("-");

        if (paraStrs.length == 2) {
            holder.timeTextView.setText(paraStrs[0] + "/" + paraStrs[1]);
        }

        holder.dawnTextView.setText(info.getValue().get(0).getBgValue());
        holder.empTextView.setText(info.getValue().get(2).getBgValue());
        holder.breLaterTextView.setText(info.getValue().get(1).getBgValue());
        holder.lunchBeforeTextView.setText(info.getValue().get(3).getBgValue());
        holder.lunchLaterTextView.setText(info.getValue().get(4).getBgValue());
        holder.dinnerBeforeTextView.setText(info.getValue().get(5).getBgValue());
        holder.dinnerLaterTextView.setText(info.getValue().get(6).getBgValue());
        holder.bedTimeTextView.setText(info.getValue().get(7).getBgValue());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTextView;
        /**
         * 凌晨
         */
        private TextView dawnTextView;
        private ImageView dawnImageView;
        /**
         * 早餐空腹
         */
        private TextView empTextView;
        private ImageView empImageView;
        /**
         * 早餐后
         */
        private TextView breLaterTextView;
        private ImageView breLaterImageView;
        /**
         * 午餐前
         */
        private TextView lunchBeforeTextView;
        private ImageView lunchBeforeImageView;
        /**
         * 午餐后
         */
        private TextView lunchLaterTextView;
        private ImageView lunchLaterImageView;
        /**
         * 晚餐前
         */
        private TextView dinnerBeforeTextView;
        private ImageView dinnerBeforeImageView;
        /**
         * 晚餐后
         */
        private TextView dinnerLaterTextView;
        private ImageView dinnerLaterImageView;
        /**
         * 睡前
         */
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

