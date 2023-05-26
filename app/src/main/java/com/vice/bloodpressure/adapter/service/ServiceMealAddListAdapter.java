package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.List;

public class ServiceMealAddListAdapter extends XyBaseAdapter<MealChildInfo> {


    public ServiceMealAddListAdapter(Context context, List<MealChildInfo> list) {
        super(context, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_service_meal_add, null);
            holder.titleTextView = convertView.findViewById(R.id.tv_service_meal_add_title);
            holder.fireTextView = convertView.findViewById(R.id.tv_service_meal_add_fire);
            holder.numEditText = convertView.findViewById(R.id.ev_service_meal_add_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MealChildInfo info = getList().get(position);

        holder.numEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                float fixedWeight = TurnUtils.getFloat(info.getFixedWeight(), 0);
                float allK = TurnUtils.getFloat(holder.numEditText.getText().toString().trim(), 0) * fixedWeight;
                holder.fireTextView.setText(String.valueOf(allK));
            }
        });



        holder.titleTextView.setText(info.getFoodname());

        return convertView;
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView fireTextView;
        EditText numEditText;
    }
}
