package com.vice.bloodpressure.adapter.service;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.XyBaseAdapter;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.List;

public class ServiceMealAddListAdapter extends XyBaseAdapter<MealChildInfo> {
    private ImageView fireImageView;
    private TextView fireTextView;

    public ServiceMealAddListAdapter(Context context, List<MealChildInfo> list, ImageView fireImageView, TextView fireTextView) {
        super(context, list);
        this.fireImageView = fireImageView;
        this.fireTextView = fireTextView;
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
                float kcalval = TurnUtils.getFloat(info.getKcalval(), 0) / fixedWeight;
                int allK = (int) (TurnUtils.getFloat(holder.numEditText.getText().toString().trim(), 0) * kcalval);
                holder.fireTextView.setText(String.valueOf(allK));
                float kcalAll = 0;
                for (int i = 0; i < getList().size(); i++) {
                    float kcal = TurnUtils.getFloat(holder.fireTextView.getText().toString(), 0);
                    kcalAll = kcalAll + kcal;
                }
                fireTextView.setText(String.valueOf((int) (kcalAll)));
                fireImageView.setImageResource(R.drawable.service_meal_no_have_data);
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
