package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.MessageInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class OutDoctorEducationListAdapter extends RecyclerView.Adapter<OutDoctorEducationListAdapter.ViewHolder> {
    private Context context;
    private List<MessageInfo> list;
    private IAdapterViewClickListener clickListener;


    public OutDoctorEducationListAdapter(Context context, List<MessageInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_doctor_education, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageInfo info = list.get(position);
        holder.titleTextView.setText(info.getTitle());
        holder.timeTextView.setText(info.getSendTime());
        holder.contentTextView.setText(info.getBi());
        //宣教类型:1->图文;2->音频;3->视频;
        if ("1".equals(info.getType())) {
            holder.imgImageView.setImageResource(R.drawable.out_doctor_education_text);
        } else if ("2".equals(info.getType())) {
            holder.imgImageView.setImageResource(R.drawable.out_doctor_education_audio);
        } else {
            holder.imgImageView.setImageResource(R.drawable.out_doctor_education_video);
        }
        //0 未阅读 1 已阅读
        if ("0".equals(info.getStatus())) {
            holder.readImageView.setVisibility(View.VISIBLE);
        } else {
            holder.readImageView.setVisibility(View.GONE);
        }

        DoctorInfoOnClick click = new DoctorInfoOnClick(position);
        holder.clickLinearLayout.setOnClickListener(click);
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
        private LinearLayout clickLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            imgImageView = itemView.findViewById(R.id.iv_doctor_education_img);
            titleTextView = itemView.findViewById(R.id.tv_doctor_education_title);
            timeTextView = itemView.findViewById(R.id.tv_doctor_education_time);
            contentTextView = itemView.findViewById(R.id.tv_doctor_education_content);
            readImageView = itemView.findViewById(R.id.iv_doctor_education_read);
            clickLinearLayout = itemView.findViewById(R.id.ll_doctor_education_click);
        }
    }

    private class DoctorInfoOnClick implements View.OnClickListener {
        private int position;

        public DoctorInfoOnClick(int position) {
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

