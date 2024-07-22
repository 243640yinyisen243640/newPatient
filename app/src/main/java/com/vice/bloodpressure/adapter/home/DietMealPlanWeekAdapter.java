package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.MealSecondInfo;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class DietMealPlanWeekAdapter extends RecyclerView.Adapter<DietMealPlanWeekAdapter.ViewHolder> {
    private Context context;
    private List<MealSecondInfo> list;
    private IAdapterViewClickOneListener clickListener;
    private int clickPosition = 0;

    public DietMealPlanWeekAdapter(Context context, List<MealSecondInfo> list, IAdapterViewClickOneListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_diet_week_list, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealSecondInfo info = list.get(position);
        String times[] = info.getPlanDate().split("-");

        String a = info.getPlanDate();
        String end = "";
        String[] split = a.split("-");
        if (split.length == 3) {
//            start = split[0];
            end = split[1] + "-" + split[2];
        }

        holder.weekTextView.setText(XyTimeUtils.Week(info.getPlanDate()));
        holder.monthTextView.setText(end);
        if (clickPosition == position) {
            holder.clickLinearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_green_3));
            holder.monthTextView.setTextColor(ContextCompat.getColor(context, R.color.text_white));
            holder.weekTextView.setTextColor(ContextCompat.getColor(context, R.color.text_white));
            holder.weekTextView.setTypeface(Typeface.DEFAULT_BOLD);
            holder.monthTextView.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            holder.clickLinearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
            holder.monthTextView.setTextColor(ContextCompat.getColor(context, R.color.gray_8a));
            holder.weekTextView.setTextColor(ContextCompat.getColor(context, R.color.gray_8a));
            holder.weekTextView.setTypeface(Typeface.DEFAULT);
            holder.monthTextView.setTypeface(Typeface.DEFAULT);
        }
        clickOnClick clickOnClick = new clickOnClick(position);
        holder.clickLinearLayout.setOnClickListener(clickOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView weekTextView;
        private TextView monthTextView;
        private LinearLayout clickLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            weekTextView = itemView.findViewById(R.id.tv_diet_week);
            monthTextView = itemView.findViewById(R.id.tv_diet_month);
            clickLinearLayout = itemView.findViewById(R.id.ll_diet_week_click);
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

    public void setClickPosition(int position) {
        this.clickPosition = position;
        notifyDataSetChanged();
    }

    public int getClickPosition() {
        return clickPosition;
    }
}

