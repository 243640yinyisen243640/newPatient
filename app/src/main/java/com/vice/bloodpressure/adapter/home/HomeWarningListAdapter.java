package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.MessageInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class HomeWarningListAdapter extends RecyclerView.Adapter<HomeWarningListAdapter.ViewHolder> {
    private Context context;
    private List<MessageInfo> list;


    public HomeWarningListAdapter(Context context, List<MessageInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_home_warning, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageInfo info = list.get(position);
        holder.titleTextView.setText(info.getTitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private ImageView readImageView;
        private TextView numTextView;
        private TextView timeTextView;
        private TextView deleteTextView;
        private TextView moreTextView;
        private TextView tipTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_warning_xt_xy);
            readImageView = itemView.findViewById(R.id.iv_warning_read);
            numTextView = itemView.findViewById(R.id.tv_warning_num);
            timeTextView = itemView.findViewById(R.id.tv_warning_time);
            deleteTextView = itemView.findViewById(R.id.tv_warning_delete);
            moreTextView = itemView.findViewById(R.id.tv_warning_more);
            tipTextView = itemView.findViewById(R.id.tv_warning_tip);
        }
    }


}

