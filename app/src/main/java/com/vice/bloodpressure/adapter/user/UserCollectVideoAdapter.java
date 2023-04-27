package com.vice.bloodpressure.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.IClassInfo;
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
    private List<? extends IClassInfo> list;
    private IAdapterViewClickListener clickListener;

    public UserCollectVideoAdapter(Context context, List<? extends IClassInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
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
        IClassInfo info = list.get(position);
        int width = (ScreenUtils.screenWidth(context) - DensityUtils.dip2px(context, 55)) / 2;
        FrameLayout.LayoutParams ll = new FrameLayout.LayoutParams(width, width / 2);
        holder.coverImageView.setLayoutParams(ll);
        XyImageUtils.loadRoundImage(context, R.drawable.diet_guogai_gray, info.getImplClassImg(), holder.coverImageView);
        holder.nameTextView.setText(info.getImplClassName());
        clickOnClick clickListener = new clickOnClick(position);
        holder.clickLinearLayout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImageView;
        private TextView nameTextView;
        private LinearLayout clickLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            clickLinearLayout = itemView.findViewById(R.id.ll_user_collect_video_click);
            coverImageView = itemView.findViewById(R.id.iv_user_collect_bg);
            nameTextView = itemView.findViewById(R.id.tv_user_collect_title);
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

