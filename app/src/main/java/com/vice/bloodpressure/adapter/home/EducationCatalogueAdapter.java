package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.model.EducationInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class EducationCatalogueAdapter extends RecyclerView.Adapter<EducationCatalogueAdapter.ViewHolder> {
    private Context context;
    private List<EducationInfo> list;

    private IAdapterViewClickListener clickListener;


    public EducationCatalogueAdapter(Context context, List<EducationInfo> list, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_education_catalogue, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EducationInfo info = list.get(position);

        holder.titleTextView.setText(info.getEssayName());
        if ("学习中".equals(info.getStatus())) {
            holder.titleTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.audio_star, 0, 0, 0);
            holder.titleTextView.setTextColor(context.getResources().getColor(R.color.black_24));
        } else {
            holder.titleTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.audio_pause, 0, 0, 0);
            holder.titleTextView.setTextColor(context.getResources().getColor(R.color.gray_8a));
        }

        clickExpandOnClick expandOnClick = new clickExpandOnClick(position);
        holder.clickLinearLayout.setOnClickListener(expandOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private LinearLayout clickLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_education_catalogue_title);
            clickLinearLayout = itemView.findViewById(R.id.ll_education_catalogue_click);
        }
    }


    private class clickExpandOnClick implements View.OnClickListener {
        private int position;

        public clickExpandOnClick(int position) {
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

