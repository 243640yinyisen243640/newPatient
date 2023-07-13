package com.vice.bloodpressure.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.List;

public class EducationQuestionInvestigateRealAdapter extends BaseAdapter {
    private List<BaseLocalDataInfo> list;
    private Context context;

    public EducationQuestionInvestigateRealAdapter(List<BaseLocalDataInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_education_question_investigate, null);

        LinearLayout linearLayout = view.findViewById(R.id.ll_education_question_investigate_bg);
        TextView textView = view.findViewById(R.id.tv_education_question_investigate_text);
        ImageView imageView = view.findViewById(R.id.tv_education_question_investigate_check);
        textView.setText(list.get(position).getName());
        if (list.get(position).isCheck()) {
            linearLayout.setBackground(context.getDrawable(R.drawable.education_question_investigate_item_bg));
            imageView.setVisibility(View.VISIBLE);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            linearLayout.setBackground(context.getDrawable(R.drawable.shape_grey_10));
            imageView.setVisibility(View.GONE);
            textView.setTextColor(Color.parseColor("#242424"));
        }


        return view;
    }
}
