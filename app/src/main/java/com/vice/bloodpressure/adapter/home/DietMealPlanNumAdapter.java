package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.ScreenUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class DietMealPlanNumAdapter extends RecyclerView.Adapter<DietMealPlanNumAdapter.ViewHolder> {
    private Context context;
    private List<MealInfo> list;


    public DietMealPlanNumAdapter(Context context, List<MealInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_diet_meal_plan_all_day, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealInfo info = list.get(position);
        int width = (ScreenUtils.screenWidth(context) - ScreenUtils.dip2px(context, 60)) / 6;
        holder.allLinearLayout.getLayoutParams().width = width;
        holder.nameTextView.setText(info.getImg());
        holder.colorTextView.setText(info.getColor());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout allLinearLayout;
        private TextView nameTextView;
        private TextView colorTextView;
        private TextView numTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_diet_plan_meal_name);
            colorTextView = itemView.findViewById(R.id.tv_diet_plan_meal_color);
            numTextView = itemView.findViewById(R.id.tv_diet_plan_meal_num);
            allLinearLayout = itemView.findViewById(R.id.ll_diet_plan_meal);
        }
    }


}

