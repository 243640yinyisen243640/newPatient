package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.MealExclusiveInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class DietMealDetailsListAdapter extends RecyclerView.Adapter<DietMealDetailsListAdapter.ViewHolder> {
    private Context context;
    private List<MealExclusiveInfo> list;


    public DietMealDetailsListAdapter(Context context, List<MealExclusiveInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_diet_meal_list, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealExclusiveInfo info = list.get(position);
        //        XyImageUtils.loadRoundImage(context, R.drawable.diet_guogai_gray, info.getImg(), holder.coverImageView);
        holder.nameTextView.setText(info.getRecName());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < info.getIngMap().size(); i++) {
            builder.append(info.getIngMap().get(i).getName());
            builder.append(info.getIngMap().get(i).getIngK() + "g");
            builder.append(";");
        }
        builder.deleteCharAt(builder.length() - 1);
        holder.numTextView.setText(builder.toString());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImageView;
        private TextView nameTextView;
        private TextView numTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.iv_meal_details_cover);
            nameTextView = itemView.findViewById(R.id.tv_meal_details_name);
            numTextView = itemView.findViewById(R.id.tv_meal_details_num);
        }
    }


}

