package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.ExerciseChildInfo;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class ExerciseRecordChildListAdapter extends RecyclerView.Adapter<ExerciseRecordChildListAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseChildInfo> list;


    public ExerciseRecordChildListAdapter(Context context, List<ExerciseChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_exercise_woxygen_child, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseChildInfo info = list.get(position);
        setTextStyle(holder.fireTextView, Color.parseColor("#00C27F"), " 3360 ", "千卡");
        holder.typeTextView.setText(info.getType());
        holder.timeTextView.setText(info.getExerciseTime());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTextView;
        private TextView timeTextView;
        private TextView fireTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.tv_exercise_child_type);
            timeTextView = itemView.findViewById(R.id.tv_exercise_child_time);
            fireTextView = itemView.findViewById(R.id.tv_exercise_child_fire);
        }
    }

    /**
     * @param textView
     * @param startColor
     * @param text1
     * @param text2
     */
    private void setTextStyle(TextView textView, int startColor, String text1, String text2) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(builder);
    }

}

