package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.ExercisePlanIllAdapter;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:运动答题  关于疾病
 */
public class ExercisePlanIllActivity extends UIBaseActivity {
    private RecyclerView recyclerView;
    private TextView backTv;
    private TextView nextTv;
    private List<BaseLocalDataInfo> normalInfoList;
    private ExercisePlanIllAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定运动方案");
        initView();
        initValues();
        initListener();
    }

    private void initValues() {
        //chd -> 冠心病
        //dn -> 肾病
        //dr -> 视网膜病变
        //dpn -> 神经病变
        //htn -> 高血压
        //no -> 无
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        normalInfoList = new ArrayList<>();
        normalInfoList.add(new BaseLocalDataInfo("冠心病", "chd"));
        normalInfoList.add(new BaseLocalDataInfo("高血压", "htn"));
        normalInfoList.add(new BaseLocalDataInfo("合并神经病变", "dpn"));
        normalInfoList.add(new BaseLocalDataInfo("合并视网膜病变", "dr"));
        normalInfoList.add(new BaseLocalDataInfo("合并肾病", "dn"));
        normalInfoList.add(new BaseLocalDataInfo("无", "no"));
        normalInfoList.get(5).setIsCheck(true);
        adapter = new ExercisePlanIllAdapter(getPageContext(), normalInfoList, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_item_exercise_ill_type:
                        if (normalInfoList.get(position).getName().equals("无")) {
                            for (int i = 0; i < normalInfoList.size(); i++) {
                                normalInfoList.get(i).setIsCheck(false);
                            }
                        } else {
                            normalInfoList.get(position).setIsCheck(!normalInfoList.get(position).getIsCheck());
                        }
                        //是否有选择的
                        boolean isCheck = false;
                        for (int i = 0; i < normalInfoList.size(); i++) {
                            if (normalInfoList.get(i).getIsCheck()) {
                                isCheck = true;
                            }
                        }
                        normalInfoList.get(5).setIsCheck(!isCheck);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {
        backTv.setOnClickListener(v -> finish());
        nextTv.setOnClickListener(v -> {


            StringBuilder illTypeBuilder = new StringBuilder();
            for (int i = 0; i < normalInfoList.size(); i++) {
                if (normalInfoList.get(i).getIsCheck()) {
                    illTypeBuilder.append(normalInfoList.get(i).getId());
                    illTypeBuilder.append(",");
                }
            }
            illTypeBuilder.deleteCharAt(illTypeBuilder.length() - 1);
            Intent intent = new Intent(getPageContext(), ExercisePlanExerciseActivity.class);
            intent.putExtra("height", getIntent().getStringExtra("height"));
            intent.putExtra("weight", getIntent().getStringExtra("weight"));
            intent.putExtra("age", getIntent().getStringExtra("age"));
            intent.putExtra("diseases", illTypeBuilder.toString());
            startActivity(intent);
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_ill, null);
        backTv = view.findViewById(R.id.tv_exercise_ill_back);
        nextTv = view.findViewById(R.id.tv_exercise_ill_next);
        recyclerView = view.findViewById(R.id.rv_exercise_ill_type);
        containerView().addView(view);
    }
}
