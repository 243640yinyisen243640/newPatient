package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.model.BloodChildInfo;

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
public class SevenBottomAdapter extends RecyclerView.Adapter<SevenBottomAdapter.ViewHolder> {
    private Context context;
    private List<BloodChildInfo> list;
    private IAdapterViewClickListener clickListener;


    public SevenBottomAdapter(Context context, List<BloodChildInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 8; j++) {
                int v = (int) (Math.random() * 100);
                list.get(i).getValue().get(j).setBgValue(v + "");
            }
        }


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

        SimpleDateFormat sdf1 = new SimpleDateFormat(DataFormatManager.TIME_FORMAT_Y_M_D);
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd");
        Date date = null;
        try {
            date = sdf1.parse(info.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String format = sdf2.format(date);

        holder.timeTextView.setText(format);

        //        String paraStr = info.getDate().
        //        substring(info.getDate().indexOf("-") + 1);
        //        String[] paraStrs = paraStr.split("-");
        //
        //        if (paraStrs.length == 2) {
        //            holder.timeTextView.setText(paraStrs[0] + "/" + paraStrs[1]);
        //        }
        OnClick click = new OnClick(position);
        holder.dawnFrameLayout.setTag(0);
        holder.empFrameLayout.setTag(1);
        holder.breLaterFrameLayout.setTag(2);
        holder.lunchBeforeFrameLayout.setTag(3);
        holder.lunchLaterFrameLayout.setTag(4);
        holder.dinnerBeforeFrameLayout.setTag(5);
        holder.dinnerLaterFrameLayout.setTag(6);
        holder.bedTimeFrameLayout.setTag(7);
        holder.dawnFrameLayout.setOnClickListener(click);
        holder.empFrameLayout.setOnClickListener(click);
        holder.breLaterFrameLayout.setOnClickListener(click);
        holder.lunchBeforeFrameLayout.setOnClickListener(click);
        holder.lunchLaterFrameLayout.setOnClickListener(click);
        holder.dinnerBeforeFrameLayout.setOnClickListener(click);
        holder.dinnerLaterFrameLayout.setOnClickListener(click);
        holder.bedTimeFrameLayout.setOnClickListener(click);
        //血糖状态 1偏低 2正常 3偏高
        setStatus(info.getValue().get(0).getBgStatus(), holder);
        setStatus(info.getValue().get(1).getBgStatus(), holder);
        setStatus(info.getValue().get(2).getBgStatus(), holder);
        setStatus(info.getValue().get(3).getBgStatus(), holder);
        setStatus(info.getValue().get(4).getBgStatus(), holder);
        setStatus(info.getValue().get(5).getBgStatus(), holder);
        setStatus(info.getValue().get(6).getBgStatus(), holder);
        setStatus(info.getValue().get(7).getBgStatus(), holder);

        setMoreData(info.getValue().get(0).getBgCount(), holder.dawnImageView);
        setMoreData(info.getValue().get(1).getBgCount(), holder.empImageView);
        setMoreData(info.getValue().get(2).getBgCount(), holder.breLaterImageView);
        setMoreData(info.getValue().get(3).getBgCount(), holder.lunchBeforeImageView);
        setMoreData(info.getValue().get(4).getBgCount(), holder.lunchLaterImageView);
        setMoreData(info.getValue().get(5).getBgCount(), holder.dinnerBeforeImageView);
        setMoreData(info.getValue().get(6).getBgCount(), holder.dinnerLaterImageView);
        setMoreData(info.getValue().get(7).getBgCount(), holder.bedTimeImageView);

        holder.dawnTextView.setText(info.getValue().get(0).getBgValue());
        holder.empTextView.setText(info.getValue().get(1).getBgValue());
        holder.breLaterTextView.setText(info.getValue().get(2).getBgValue());
        holder.lunchBeforeTextView.setText(info.getValue().get(3).getBgValue());
        holder.lunchLaterTextView.setText(info.getValue().get(4).getBgValue());
        holder.dinnerBeforeTextView.setText(info.getValue().get(5).getBgValue());
        holder.dinnerLaterTextView.setText(info.getValue().get(6).getBgValue());
        holder.bedTimeTextView.setText(info.getValue().get(7).getBgValue());

    }

    /**
     * @param status 血糖状态 1偏低 2正常 3偏高
     * @param holder
     */
    private void setStatus(String status, ViewHolder holder) {
        if ("1".equals(status)) {
            holder.dawnTextView.setTextColor(context.getResources().getColor(R.color.blue_4B));
        } else if ("2".equals(status)) {
            holder.dawnTextView.setTextColor(context.getResources().getColor(R.color.main_base_color));
        } else {
            holder.dawnTextView.setTextColor(context.getResources().getColor(R.color.red_E5));
        }
    }

    /**
     * @param size
     * @param imageView
     */
    private void setMoreData(String size, ImageView imageView) {
        if (Integer.parseInt(size) > 1) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
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
        private FrameLayout dawnFrameLayout;
        private TextView dawnTextView;
        private ImageView dawnImageView;
        /**
         * 早餐空腹
         */
        private FrameLayout empFrameLayout;
        private TextView empTextView;
        private ImageView empImageView;
        /**
         * 早餐后
         */
        private FrameLayout breLaterFrameLayout;
        private TextView breLaterTextView;
        private ImageView breLaterImageView;
        /**
         * 午餐前
         */
        private FrameLayout lunchBeforeFrameLayout;
        private TextView lunchBeforeTextView;
        private ImageView lunchBeforeImageView;
        /**
         * 午餐后
         */
        private FrameLayout lunchLaterFrameLayout;
        private TextView lunchLaterTextView;
        private ImageView lunchLaterImageView;
        /**
         * 晚餐前
         */
        private FrameLayout dinnerBeforeFrameLayout;
        private TextView dinnerBeforeTextView;
        private ImageView dinnerBeforeImageView;
        /**
         * 晚餐后
         */
        private FrameLayout dinnerLaterFrameLayout;
        private TextView dinnerLaterTextView;
        private ImageView dinnerLaterImageView;
        /**
         * 睡前
         */
        private FrameLayout bedTimeFrameLayout;
        private TextView bedTimeTextView;
        private ImageView bedTimeImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_time);

            dawnFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_dawn);
            dawnTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dawn);
            dawnImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dawn);

            empFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_bre_emp);
            empTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bre_emp);
            empImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bre_emp);

            breLaterFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_bre_later);
            breLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bre_later);
            breLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bre_later);

            lunchBeforeFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_lunch_before);
            lunchBeforeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_lunch_before);
            lunchBeforeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_lunch_before);

            lunchLaterFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_lunch_later);
            lunchLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_lunch_later);
            lunchLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_lunch_later);

            dinnerBeforeFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_dinner_before);
            dinnerBeforeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dinner_before);
            dinnerBeforeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dinner_before);

            dinnerLaterFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_dinner_later);
            dinnerLaterTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_dinner_later);
            dinnerLaterImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_dinner_later);

            bedTimeFrameLayout = itemView.findViewById(R.id.fl_item_service_blood_seven_bed_time);
            bedTimeTextView = itemView.findViewById(R.id.tv_item_service_blood_seven_bed_time);
            bedTimeImageView = itemView.findViewById(R.id.iv_item_service_blood_seven_bed_time);

        }
    }

    class OnClick implements View.OnClickListener {
        private int pos;

        public OnClick(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            clickListener.adapterClickListener(pos, (Integer) v.getTag(), v);
        }
    }
}

