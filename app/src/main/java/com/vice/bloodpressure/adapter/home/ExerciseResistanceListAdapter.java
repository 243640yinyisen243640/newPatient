package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Color;
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
public class ExerciseResistanceListAdapter extends RecyclerView.Adapter<ExerciseResistanceListAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseChildInfo> list;


    public ExerciseResistanceListAdapter(Context context, List<ExerciseChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_exercise_resistance, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseChildInfo info = list.get(position);
        holder.timeTextView.setText(info.getTime());
        holder.typeTextView.setText(info.getType());
        holder.numTextView.setText(info.getOnceFire() + "个");
        holder.stateTextView.setText(info.getState());
        if ("未达标".equals(info.getState())) {
            holder.stateTextView.setTextColor(Color.parseColor("#FF921C"));
        } else if ("完成".equals(info.getState())) {
            holder.stateTextView.setTextColor(Color.parseColor("#00C27F"));
        } else {
            holder.stateTextView.setTextColor(Color.parseColor("#FB4747"));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTextView;
        private TextView timeTextView;
        private TextView numTextView;
        private TextView stateTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.tv_exercise_resistance_time);
            typeTextView = itemView.findViewById(R.id.tv_exercise_resistance_type);
            numTextView = itemView.findViewById(R.id.tv_exercise_resistance_num);
            stateTextView = itemView.findViewById(R.id.tv_exercise_resistance_state);
        }
    }


}

