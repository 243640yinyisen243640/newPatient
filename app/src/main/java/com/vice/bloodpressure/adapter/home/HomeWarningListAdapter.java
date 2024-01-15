package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
public class HomeWarningListAdapter extends RecyclerView.Adapter<HomeWarningListAdapter.ViewHolder> {
    private Context context;
    private List<MessageInfo> list;
    private IAdapterViewClickOneListener clickOneListener;

    public HomeWarningListAdapter(Context context, List<MessageInfo> list, IAdapterViewClickOneListener clickOneListener) {
        this.context = context;
        this.list = list;
        this.clickOneListener = clickOneListener;
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
        // 1血糖数据  2 血压数据
        if ("1".equals(info.getType())) {
            holder.titleTextView.setText("血糖");
        } else {
            holder.titleTextView.setText("血压");
        }
        holder.numTextView.setText(info.getValue());
        holder.unitTextView.setText(info.getUnit());
        holder.timeTextView.setText(info.getAddTime());
        clickOnClick onClick = new clickOnClick(position);
        holder.deleteTextView.setOnClickListener(onClick);
        holder.moreTextView.setOnClickListener(onClick);
        //1偏低 2 正常 3 偏高
        holder.tipTextView.setText(info.getPromptText());
        if ("1".equals(info.getStatus())) {
            holder.numTextView.setTextColor(ContextCompat.getColor(context, R.color.blue_4B));
            holder.unitTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.warning_low, 0);
            holder.tipTextView.setVisibility(View.VISIBLE);

        } else if ("2".equals(info.getStatus())) {
            holder.tipTextView.setVisibility(View.GONE);
            holder.numTextView.setTextColor(ContextCompat.getColor(context, R.color.black_24));
        } else {
            holder.numTextView.setTextColor(ContextCompat.getColor(context, R.color.red_E5));
            holder.unitTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.warn_high, 0);
            holder.tipTextView.setVisibility(View.VISIBLE);
        }
        //        0未读 1 已读
        if ("0".equals(info.getReadStatus())) {
            holder.readImageView.setVisibility(View.VISIBLE);
        } else {
            holder.readImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout clickLinearLayout;
        private TextView titleTextView;
        private ImageView readImageView;
        private TextView numTextView;
        private TextView unitTextView;
        private TextView timeTextView;
        private TextView deleteTextView;
        private TextView moreTextView;
        private TextView tipTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            clickLinearLayout = itemView.findViewById(R.id.ll_warning_click);
            titleTextView = itemView.findViewById(R.id.tv_warning_xt_xy);
            readImageView = itemView.findViewById(R.id.iv_warning_read);
            numTextView = itemView.findViewById(R.id.tv_warning_num);
            unitTextView = itemView.findViewById(R.id.tv_warning_unit);
            timeTextView = itemView.findViewById(R.id.tv_warning_time);
            deleteTextView = itemView.findViewById(R.id.tv_warning_delete);
            moreTextView = itemView.findViewById(R.id.tv_warning_more);
            tipTextView = itemView.findViewById(R.id.tv_warning_tip);
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

