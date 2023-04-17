package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ExercisePlanIllAdapter extends RecyclerView.Adapter<ExercisePlanIllAdapter.ViewHolder> {
    private Context context;
    private List<BaseLocalDataInfo> list;
    private IAdapterViewClickListener clickListener;


    public ExercisePlanIllAdapter(Context context, List<BaseLocalDataInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象item_diet_meal_list_check
        View v = View.inflate(context, R.layout.item_exercise_plan_ill, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaseLocalDataInfo info = list.get(position);
        holder.typeTextView.setText(info.getName());
        if (info.getIsCheck()) {
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_check, 0, 0, 0);
        } else {
            holder.typeTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_uncheck, 0, 0, 0);
        }
        clickOnClick clickOnClick = new clickOnClick(position);
        holder.typeTextView.setOnClickListener(clickOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView typeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.tv_item_exercise_ill_type);
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

