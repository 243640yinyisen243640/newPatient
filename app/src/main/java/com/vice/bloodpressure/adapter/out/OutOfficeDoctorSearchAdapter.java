package com.vice.bloodpressure.adapter.out;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class OutOfficeDoctorSearchAdapter extends RecyclerView.Adapter<OutOfficeDoctorSearchAdapter.ViewHolder> {
    private Context context;
    private List<DoctorInfo> list;

    private IAdapterViewClickListener clickListener;


    public OutOfficeDoctorSearchAdapter(Context context, List<DoctorInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_office_search_doctor, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorInfo info = list.get(position);
        XyImageUtils.loadCircleImage(context, R.drawable.out_doctor_default_head_img, info.getAvatar(), holder.headImageView);
        holder.nameTextView.setText(info.getDoctorName());
        if (info.getTitles() != null && info.getDeptName() != null) {
            holder.lineView.setVisibility(View.VISIBLE);
        } else {
            holder.lineView.setVisibility(View.GONE);
        }

        holder.postTextView.setText(info.getTitles());
        holder.officeTextView.setText(info.getDeptName());
        if (info.getProfile() != null) {
            SpannableStringBuilder builder = new SpannableStringBuilder();
            builder.append("简介：");
            int length1 = builder.length();
            builder.append(info.getProfile());
            builder.setSpan(new ForegroundColorSpan(Color.parseColor("#3A3939")), 0, length1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            holder.introduceTextView.setText(builder);
        }

        DoctorInfoOnClick expandOnClick = new DoctorInfoOnClick(position);
        holder.clickLinearLayout.setOnClickListener(expandOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView headImageView;
        private TextView nameTextView;
        private TextView postTextView;
        private TextView introduceTextView;
        private TextView officeTextView;
        private LinearLayout clickLinearLayout;
        private View lineView;


        public ViewHolder(View itemView) {
            super(itemView);
            headImageView = itemView.findViewById(R.id.iv_office_doctor_head_search);
            nameTextView = itemView.findViewById(R.id.tv_office_doctor_name_search);
            lineView = itemView.findViewById(R.id.view_post);
            postTextView = itemView.findViewById(R.id.tv_office_doctor_post_search);
            introduceTextView = itemView.findViewById(R.id.tv_office_doctor_introduce_search);
            officeTextView = itemView.findViewById(R.id.tv_office_doctor_office_search);
            clickLinearLayout = itemView.findViewById(R.id.ll_office_doctor_click_search);
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

