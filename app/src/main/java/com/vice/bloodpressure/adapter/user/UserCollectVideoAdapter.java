package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.VideoInfo;
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
public class UserCollectVideoAdapter extends RecyclerView.Adapter<UserCollectVideoAdapter.ViewHolder> {
    private Context context;
    private List<VideoInfo> list;


    public UserCollectVideoAdapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_user_collect_video, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoInfo info = list.get(position);
        int width = (ScreenUtils.screenWidth(context) - DensityUtils.dip2px(context, 55)) / 2;
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(width,  width / 2);
        holder.coverImageView.setLayoutParams(ll);
        XyImageUtils.loadRoundImage(context, R.drawable.diet_guogai_gray, info.getImg(), holder.coverImageView);
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
            coverImageView = itemView.findViewById(R.id.i_user_collect_bg);
            nameTextView = itemView.findViewById(R.id.tv_user_collect_title);
        }
    }


}

