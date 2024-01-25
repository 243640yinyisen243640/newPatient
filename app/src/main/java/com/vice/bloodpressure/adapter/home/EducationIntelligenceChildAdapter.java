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
public class EducationIntelligenceChildAdapter extends RecyclerView.Adapter<EducationIntelligenceChildAdapter.ViewHolder> {
    private Context context;
    private List<EducationInfo> list;

    private IAdapterViewClickListener clickListener;
    private int parentPosition;

    public EducationIntelligenceChildAdapter(Context context, List<EducationInfo> list, int position, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
        this.parentPosition = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_education_intelligence_study_child, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EducationInfo info = list.get(position);

        holder.titleTextView.setText(info.getEssayName());
        holder.stateTextView.setText(info.getStatus());

        clickExpandOnClick expandOnClick = new clickExpandOnClick(position);
        holder.clickLinearLayout.setOnClickListener(expandOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView stateTextView;
        private LinearLayout clickLinearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_education_study_child_title);
            stateTextView = itemView.findViewById(R.id.tv_education_study_child_state);
            clickLinearLayout = itemView.findViewById(R.id.ll_education_study_child_click);
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
                clickListener.adapterClickListener(parentPosition, position, v);
            }
        }
    }
}

