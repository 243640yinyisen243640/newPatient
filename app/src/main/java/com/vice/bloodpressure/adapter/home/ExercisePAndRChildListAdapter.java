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
public class ExercisePAndRChildListAdapter extends RecyclerView.Adapter<ExercisePAndRChildListAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseChildInfo> list;


    public ExercisePAndRChildListAdapter(Context context, List<ExerciseChildInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_exercise_p_and_r_child, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseChildInfo info = list.get(position);

        holder.typeTextView.setText(info.getSportName());
        holder.numTextView.setText(info.getSportNum()+"个");
        //1未达标  2达标 3超标
        if ("1".equals(info.getSportStatus())) {
            holder.stateTextView.setText("未达标");
            holder.stateTextView.setTextColor(Color.parseColor("#FF921C"));
        } else if ("2".equals(info.getState())) {
            holder.stateTextView.setText("达标");
            holder.stateTextView.setTextColor(Color.parseColor("#00C27F"));
        } else {
            holder.stateTextView.setText("超标");
            holder.stateTextView.setTextColor(Color.parseColor("#FB4747"));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTextView;
        private TextView numTextView;
        private TextView stateTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.tv_exercise_resistance_type_child);
            numTextView = itemView.findViewById(R.id.tv_exercise_resistance_num_child);
            stateTextView = itemView.findViewById(R.id.tv_exercise_resistance_state_child);
        }
    }


}

