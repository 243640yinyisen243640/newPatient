package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.ExerciseInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ExerciseRecordListAdapter extends RecyclerView.Adapter<ExerciseRecordListAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseInfo> list;


    public ExerciseRecordListAdapter(Context context, List<ExerciseInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_exercise_woxygen, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseInfo info = list.get(position);
//        setTextStyle(holder.needTextView, Color.parseColor("#00C27F"), "今日所需消耗 ", info.getNeedConsumeCalories(), " 千卡");
        setTextStyle(holder.haveTextView, Color.parseColor("#00C27F"), "已消耗 ", info.getCaloriesConsumed(), " 千卡");
        holder.timeTextView.setText(info.getSportDate());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        ExerciseRecordChildListAdapter childListAdapter = new ExerciseRecordChildListAdapter(context, list.get(position).getArds());
        holder.childRecyclerView.setAdapter(childListAdapter);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView timeTextView;
        private TextView needTextView;
        private RecyclerView childRecyclerView;
        private TextView haveTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_exercise_oxygen_time);
            needTextView = itemView.findViewById(R.id.tv_exercise_oxygen_need);
            childRecyclerView = itemView.findViewById(R.id.rv_exercise_oxygen_child);
            haveTextView = itemView.findViewById(R.id.tv_exercise_oxygen_have);
        }
    }

    /**
     * @param textView
     * @param startColor
     * @param text1
     * @param text2
     * @param text3
     */
    private void setTextStyle(TextView textView, int startColor, String text1, String text2, String text3) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        builder.setSpan(new ForegroundColorSpan(startColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(builder);
    }


}

