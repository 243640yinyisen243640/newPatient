package com.vice.bloodpressure.fragment.fhome.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.ResourceProportionInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietResourceProportionFragment extends UIBaseFragment {
    private List<ResourceProportionInfo> proportionInfos;

    public static DietResourceProportionFragment getInstance(String text) {

        DietResourceProportionFragment resourceProportionFragment = new DietResourceProportionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        resourceProportionFragment.setArguments(bundle);
        return resourceProportionFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());

    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_resource_meal, null);
        initValues(view);
        return view;
    }


    private void initValues(View view){
        proportionInfos = new ArrayList<>();
        //这是模拟数据的
        proportionInfos.add(new ResourceProportionInfo("猪瘦肉", "24.5"));
        proportionInfos.add(new ResourceProportionInfo("莴笋", "73.54"));
        proportionInfos.add(new ResourceProportionInfo("香葱", "1.96"));

        int maxLengthIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < proportionInfos.size(); i++) {
            if (proportionInfos.get(i).getName().length() > maxLength) {
                maxLength = proportionInfos.get(i).getName().length();
                maxLengthIndex = i;
            }

            if (proportionInfos.get(i).getName().length() >= 3) {
                proportionInfos.get(i).setName(proportionInfos.get(i).getName().substring(0, 3));
            }
        }

        String name = proportionInfos.get(maxLengthIndex).getName();

        LinearLayout linearLayout = view.findViewById(R.id.ll_test);

        LinearLayout ll = (LinearLayout) View.inflate(getPageContext(), R.layout.item_fragment_diet_resource_meal, null);
        TextView tv1 = ll.findViewById(R.id.tv_1);
        tv1.setText(name);
        TextView tv3 = ll.findViewById(R.id.tv_3);

        //获取屏幕宽度
        float screenWidth = ScreenUtils.screenWidth(getPageContext());
        //获取文字宽度
        float text1Width = tv1.getPaint().measureText(tv1.getText().toString().trim());
        float text2Width = tv3.getPaint().measureText(tv3.getText().toString().trim());
        //空白部分长度
        float width = DensityUtils.dip2px(getPageContext(), 70);
        //剩余长度

        float v2Width = screenWidth - text1Width - text2Width - width;

        for (int i = 0; i < proportionInfos.size(); i++) {
            LinearLayout linearLayout1 = (LinearLayout) View.inflate(getPageContext(), R.layout.item_fragment_diet_resource_meal, null);
            TextView tv11 = linearLayout1.findViewById(R.id.tv_1);
            TextView v22 = linearLayout1.findViewById(R.id.v_2);
            TextView tv33 = linearLayout1.findViewById(R.id.tv_3);
            tv11.setWidth((int) text1Width);
            tv33.setWidth((int) text2Width);
            v22.setWidth((int) (v2Width * Double.parseDouble(proportionInfos.get(i).getProportion()) / 100));

            tv11.setText(proportionInfos.get(i).getName());
            tv33.setText(proportionInfos.get(i).getProportion() + "%");
            linearLayout.addView(linearLayout1);
        }

    }
}
