package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class UserEquipmentListAdapter extends RecyclerView.Adapter<UserEquipmentListAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo> list;
    private IAdapterViewClickListener clickListener;

    public UserEquipmentListAdapter(Context context, List<UserInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_user_equipment, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo info = list.get(position);
        holder.titleTextView.setText(info.getDiseaseName());
        holder.timeTextView.setText(info.getTime());
        holder.typeTextView.setText(info.getType());
        XyImageUtils.loadRoundImage(context, R.drawable.guogai_img, info.getImg(), holder.imgImageView);
        clickBreakOnClick clickBreakOnClick = new clickBreakOnClick(position);
        holder.breakTextView.setOnClickListener(clickBreakOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImageView;
        private TextView titleTextView;
        private TextView timeTextView;
        private TextView typeTextView;
        private TextView breakTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            imgImageView = itemView.findViewById(R.id.iv_user_equipment_bg);
            titleTextView = itemView.findViewById(R.id.tv_user_equipment_name);
            typeTextView = itemView.findViewById(R.id.tv_user_equipment_type);
            timeTextView = itemView.findViewById(R.id.tv_user_equipment_time);
            breakTextView = itemView.findViewById(R.id.tv_user_equipment_break);
        }
    }

    private class clickBreakOnClick implements View.OnClickListener {
        private int position;

        public clickBreakOnClick(int position) {
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

