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
public class OutDoctorEducationListAdapter extends RecyclerView.Adapter<OutDoctorEducationListAdapter.ViewHolder> {
    private Context context;
    private List<MessageInfo> list;


    public OutDoctorEducationListAdapter(Context context, List<MessageInfo> list) {
        this.context = context;
        this.list = list;
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
        holder.timeTextView.setText(info.getTime());
        holder.contentTextView.setText(info.getContent());
       holder.imgImageView.setImageResource(R.drawable.out_doctor_education_audio);
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


        public ViewHolder(View itemView) {
            super(itemView);
            imgImageView = itemView.findViewById(R.id.iv_doctor_education_img);
            titleTextView = itemView.findViewById(R.id.tv_doctor_education_title);
            timeTextView = itemView.findViewById(R.id.tv_doctor_education_time);
            contentTextView = itemView.findViewById(R.id.tv_doctor_education_content);
            readImageView = itemView.findViewById(R.id.iv_doctor_education_read);
        }
    }


}

