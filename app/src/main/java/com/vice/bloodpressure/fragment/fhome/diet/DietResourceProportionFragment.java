package com.vice.bloodpressure.fragment.fhome.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ScreenUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietResourceProportionFragment extends UIBaseFragment {

    private MealExclusiveInfo mealExclusiveInfo;

    public static DietResourceProportionFragment getInstance(MealExclusiveInfo mealExclusiveInfo) {

        DietResourceProportionFragment resourceProportionFragment = new DietResourceProportionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mealExclusiveInfo", mealExclusiveInfo);
        resourceProportionFragment.setArguments(bundle);
        return resourceProportionFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        mealExclusiveInfo= (MealExclusiveInfo) getArguments().getSerializable("mealExclusiveInfo");
        containerView().addView(initView());

    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_resource_meal, null);
        initValues(view);
        return view;
    }


    private void initValues(View view){
        int maxLengthIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < mealExclusiveInfo.getIngRatio().size(); i++) {
            if (mealExclusiveInfo.getIngRatio().get(i).getName().length() > maxLength) {
                maxLength = mealExclusiveInfo.getIngRatio().get(i).getName().length();
                maxLengthIndex = i;
            }

            if (mealExclusiveInfo.getIngRatio().get(i).getName().length() >= 3) {
                mealExclusiveInfo.getIngRatio().get(i).setName(mealExclusiveInfo.getIngRatio().get(i).getName().substring(0, 3));
            }
        }

        String name = mealExclusiveInfo.getIngRatio().get(maxLengthIndex).getName();

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

        for (int i = 0; i < mealExclusiveInfo.getIngRatio().size(); i++) {
            LinearLayout linearLayout1 = (LinearLayout) View.inflate(getPageContext(), R.layout.item_fragment_diet_resource_meal, null);
            TextView tv11 = linearLayout1.findViewById(R.id.tv_1);
            TextView v22 = linearLayout1.findViewById(R.id.v_2);
            TextView tv33 = linearLayout1.findViewById(R.id.tv_3);
            tv11.setWidth((int) text1Width);
            tv33.setWidth((int) text2Width);
            v22.setWidth((int) (v2Width * Double.parseDouble(mealExclusiveInfo.getIngRatio().get(i).getIngRatio()) / 100));

            tv11.setText(mealExclusiveInfo.getIngRatio().get(i).getName());
            tv33.setText(mealExclusiveInfo.getIngRatio().get(i).getIngRatio() + "%");
            linearLayout.addView(linearLayout1);
        }

    }
}
