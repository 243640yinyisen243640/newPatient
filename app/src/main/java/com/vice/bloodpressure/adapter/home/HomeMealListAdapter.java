package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class HomeMealListAdapter extends RecyclerView.Adapter<HomeMealListAdapter.ViewHolder> {
    private Context context;
    private List<MealInfo> list;


    public HomeMealListAdapter(Context context, List<MealInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_home_meal_list, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealInfo info = list.get(position);
        int width = (ScreenUtils.screenWidth(context) - DensityUtils.dip2px(context, 25))/3;

        holder.coverImageView.getLayoutParams().width = width;


        int[] radius = new int[]{5, 5, 5, 5};
        XyImageUtils.loadCustomuRoundImage(context, R.drawable.guogai_img, info.getImg(), holder.coverImageView, radius);
        holder.nameTextView.setText(info.getTitle());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImageView;
        private TextView nameTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.im_meal_bg);
            nameTextView = itemView.findViewById(R.id.tv_meal_name);
        }
    }


}

