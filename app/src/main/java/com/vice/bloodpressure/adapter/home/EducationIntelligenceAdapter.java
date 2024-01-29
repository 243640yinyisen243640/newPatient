package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.decoration.GridSpaceItemDecoration;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：type 1:智能学习 显示还有多少节课未学完 学习状态显示 2搜索 不显示学习状态   3：我的收藏 显示收藏目录  学习状态不显示
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class EducationIntelligenceAdapter extends RecyclerView.Adapter<EducationIntelligenceAdapter.ViewHolder> {
    private Context context;
    private List<EducationInfo> list;
    private String type;

    private IAdapterViewClickListener clickListener;


    public EducationIntelligenceAdapter(Context context, List<EducationInfo> list, String type, IAdapterViewClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_education_intelligence_study, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        EducationInfo info = list.get(position);
        holder.expandImageView.setVisibility(View.VISIBLE);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
        holder.recyclerView.addItemDecoration(new GridSpaceItemDecoration(DensityUtils.dip2px(context, 10), false));
        holder.recyclerView.setLayoutManager(layoutManager);
        EducationIntelligenceChildAdapter adapter = new EducationIntelligenceChildAdapter(context, list.get(position).getTeachEssayAppVos(), position, type, clickListener);
        holder.recyclerView.setAdapter(adapter);

        XyImageUtils.loadRoundImage(context, R.drawable.shape_defaultbackground_5, info.getCoverUrl(), holder.bgImageView);
        holder.titleTextView.setText(info.getSname());
        holder.contentTextView.setText(info.getBrief());
        if ("1".equals(type)) {
            //0 未学习 1 学习中 2 已完成;
            holder.stateTextView.setVisibility(View.VISIBLE);
            switch (info.getStatus()) {
                case "0":
                    holder.stateTextView.setText("未学习");
                    break;
                case "1":
                    holder.stateTextView.setText("学习中");
                    break;
                case "2":
                    holder.stateTextView.setText("已完成");
                    break;
                default:
                    break;
            }
            holder.line.setVisibility(View.GONE);
            holder.subjectTextView.setTextColor(context.getResources().getColor(R.color.red_ff));
            holder.subjectTextView.setText(String.format(context.getString(R.string.education_intelligence_subject_num), info.getTeachEssayAppVos().size() + ""));
        } else if ("2".equals(type)) {
            holder.stateTextView.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
            holder.subjectTextView.setTextColor(context.getResources().getColor(R.color.red_ff));
            holder.subjectTextView.setText(String.format(context.getString(R.string.education_intelligence_subject_num), info.getTeachEssayAppVos().size() + ""));
        } else {
            holder.stateTextView.setVisibility(View.GONE);
            holder.subjectTextView.setText("收藏目录");
            holder.line.setVisibility(View.VISIBLE);
            holder.subjectTextView.setTextColor(context.getResources().getColor(R.color.gray_8a));

        }

        //那个按钮的展示状态 0展开 1收起状态
        int expandState = info.getIsExpand();
        if (expandState == 0) {
            holder.expandLinearLayout.setVisibility(View.VISIBLE);
            holder.expandImageView.setVisibility(View.VISIBLE);
            holder.expandImageView.setImageResource(R.drawable.arrow_top_gray_solid);
        } else {
            holder.expandLinearLayout.setVisibility(View.GONE);
            holder.expandImageView.setVisibility(View.VISIBLE);
            holder.expandImageView.setImageResource(R.drawable.arrow_down_gray_solid);
        }
        clickExpandOnClick expandOnClick = new clickExpandOnClick(position);
        holder.clickLinearLayout.setOnClickListener(expandOnClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView bgImageView;
        private TextView titleTextView;
        private TextView stateTextView;
        private TextView contentTextView;
        private ImageView expandImageView;
        private LinearLayout clickLinearLayout;
        private LinearLayout expandLinearLayout;
        private TextView subjectTextView;
        private RecyclerView recyclerView;
        private View line;


        public ViewHolder(View itemView) {
            super(itemView);
            bgImageView = itemView.findViewById(R.id.iv_education_study_bg);
            titleTextView = itemView.findViewById(R.id.tv_education_study_title);
            stateTextView = itemView.findViewById(R.id.tv_education_study_state);
            contentTextView = itemView.findViewById(R.id.tv_education_study_content);
            clickLinearLayout = itemView.findViewById(R.id.ll_education_study_click);
            expandImageView = itemView.findViewById(R.id.iv_education_study_spand);
            subjectTextView = itemView.findViewById(R.id.tv_education_study_subject_num);
            recyclerView = itemView.findViewById(R.id.rv_education_study_child);
            expandLinearLayout = itemView.findViewById(R.id.ll_education_study_child);
            line = itemView.findViewById(R.id.view_user_collect);
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

