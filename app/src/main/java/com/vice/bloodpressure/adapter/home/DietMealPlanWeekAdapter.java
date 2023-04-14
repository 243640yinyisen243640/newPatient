package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.MealChildInfo;

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
    private List<MealChildInfo> list;
    private IAdapterViewClickListener clickListener;


    public DietMealPlanWeekAdapter(Context context, List<MealChildInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象item_diet_meal_list_check
        View v = View.inflate(context, R.layout.item_diet_week_list, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealChildInfo info = list.get(position);
        holder.weekTextView.setText(info.getPlanDate());
        holder.monthTextView.setText(info.getPlanDate());
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
}

