package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.MessageInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class HomeMessageListAdapter extends RecyclerView.Adapter<HomeMessageListAdapter.ViewHolder> {
    private Context context;
    private List<MessageInfo> list;
    private IAdapterViewClickOneListener clickOneListener;


    public HomeMessageListAdapter(Context context, List<MessageInfo> list, IAdapterViewClickOneListener clickOneListener) {
        this.context = context;
        this.list = list;
        this.clickOneListener = clickOneListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_home_message, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageInfo info = list.get(position);
        holder.titleTextView.setText(info.getTitle());
        holder.timeTextView.setText(info.getCreateTime());
        holder.contentTextView.setText(info.getDetail());
        clickOnClick onClick = new clickOnClick(position);
        holder.clickFrameLayout.setOnClickListener(onClick);
        //0 未读 1已读
        if ("1".equals(info.getStatus())) {
            holder.readImageView.setVisibility(View.GONE);
        } else {
            holder.readImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImageView;
        private TextView titleTextView;
        private TextView timeTextView;
        private TextView contentTextView;
        private ImageView readImageView;
        private FrameLayout clickFrameLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            clickFrameLayout = itemView.findViewById(R.id.fl_message_click);
            imgImageView = itemView.findViewById(R.id.iv_message_img);
            titleTextView = itemView.findViewById(R.id.tv_message_title);
            timeTextView = itemView.findViewById(R.id.tv_message_time);
            contentTextView = itemView.findViewById(R.id.tv_message_content);
            readImageView = itemView.findViewById(R.id.iv_message_read);
        }
    }

    private class clickOnClick implements View.OnClickListener {
        private int position;

        public clickOnClick(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickOneListener != null) {
                clickOneListener.adapterClickListener(position, v);
            }
        }
    }

}

