package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
public class ServiceMealChildListAdapter extends RecyclerView.Adapter<ServiceMealChildListAdapter.ViewHolder> {
    private Context context;
    private List<ServiceInfo> list;


    public ServiceMealChildListAdapter(Context context, List<ServiceInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_service_meal_child, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceInfo info = list.get(position);
        setTextStyle(holder.fireTextView, context.getResources().getColor(R.color.red_E5), info.getData(), "千卡");
        holder.titleTextView.setText(info.getTime());
        holder.numTextView.setText(info.getType() + "g");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView numTextView;
        private TextView fireTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_service_meal_child_title);
            numTextView = itemView.findViewById(R.id.tv_service_meal_child_num);
            fireTextView = itemView.findViewById(R.id.tv_service_meal_child_fire);
        }
    }

    /**
     * @param textView
     * @param startColor
     * @param text1
     * @param text2
     */
    private void setTextStyle(TextView textView, int startColor, String text1, String text2) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

}

