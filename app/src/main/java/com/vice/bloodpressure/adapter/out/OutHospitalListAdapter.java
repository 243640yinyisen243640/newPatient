package com.vice.bloodpressure.adapter.out;

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
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class OutHospitalListAdapter extends RecyclerView.Adapter<OutHospitalListAdapter.ViewHolder> {
    private Context context;
    private List<HospitalInfo> list;
    private IAdapterViewClickListener clickListener;


    public OutHospitalListAdapter(Context context, List<HospitalInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_out_hospital, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HospitalInfo info = list.get(position);
        XyImageUtils.loadRoundImage(context, R.drawable.diet_guogai_gray, info.getImg(), holder.coverImageView);
        holder.nameTextView.setText(info.getName());
        holder.introduceTextView.setText(info.getIntroduce());
        holder.locationTextView.setText(info.getLocation());
        holder.levelTextView.setText(info.getLevel());
        DoctorInfoOnClick click = new DoctorInfoOnClick(position);
        holder.clickLinearLayout.setOnClickListener(click);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverImageView;
        private TextView nameTextView;
        private TextView introduceTextView;
        private TextView locationTextView;
        private TextView levelTextView;
        private LinearLayout clickLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.iv_out_hos_img);
            nameTextView = itemView.findViewById(R.id.tv_out_hos_name);
            introduceTextView = itemView.findViewById(R.id.tv_out_hos_introduce);
            locationTextView = itemView.findViewById(R.id.tv_out_hos_location);
            levelTextView = itemView.findViewById(R.id.tv_out_hos_level);
            clickLinearLayout = itemView.findViewById(R.id.ll_out_hos_click);

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

