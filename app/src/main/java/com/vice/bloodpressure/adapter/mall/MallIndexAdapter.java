package com.vice.bloodpressure.adapter.mall;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IAdapterViewClickOneListener;
import com.vice.bloodpressure.model.GoodsInfo;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/16 14:22
 */
public class MallIndexAdapter extends RecyclerView.Adapter<MallIndexAdapter.ViewHolder> {
    private Context context;
    private List<GoodsInfo> list;
    private IAdapterViewClickOneListener clickListener;


    public MallIndexAdapter(Context context, List<GoodsInfo> list, IAdapterViewClickOneListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_mall_good, null);
        //返回MyViewHolder的对象
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsInfo info = list.get(position);

        holder.titleTextView.setText(info.getTitle());
        int width = (ScreenUtils.screenWidth(context) - ScreenUtils.dip2px(context, 27)) / 2;
        int height = width;
        holder.coverImageView.getLayoutParams().width = width;
        holder.coverImageView.getLayoutParams().height = height;
        XyImageUtils.loadCustomuRoundImage(context, R.drawable.shape_defaultbackground_5, info.getCover(), holder.coverImageView, new int[]{5, 5, 0, 0});
        holder.paintTextView.setPaintFlags(holder.paintTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("¥");
        String price = "" + info.getPrice();
        SpannableString span = new SpannableString(price);
        String[] split = price.split("\\.");
        span.setSpan(new AbsoluteSizeSpan(DensityUtils.sp2px(context, 17)), 0, split[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(span);
        holder.priceTextView.setText(spannableStringBuilder);

        ClickGoods goodsClick = new ClickGoods(position);
        holder.clickLinearLayout.setOnClickListener(goodsClick);



    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout clickLinearLayout;
        private ImageView coverImageView;
        private TextView titleTextView;
        private TextView label1TextView;
        private TextView label2TextView;
        private TextView priceTextView;
        private TextView paintTextView;
        private ImageView hotImageView;
        private TextView saleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            clickLinearLayout = itemView.findViewById(R.id.ll_mall_goods_click);
            coverImageView = itemView.findViewById(R.id.iv_mall_goods_img);
            titleTextView = itemView.findViewById(R.id.tv_mall_goods_title);
            label1TextView = itemView.findViewById(R.id.tv_mall_goods_label1);
            label2TextView = itemView.findViewById(R.id.tv_mall_goods_label2);
            priceTextView = itemView.findViewById(R.id.tv_mall_goods_price);
            paintTextView = itemView.findViewById(R.id.tv_mall_goods_price_paint);
            hotImageView = itemView.findViewById(R.id.iv_mall_goods_hot);
            saleTextView = itemView.findViewById(R.id.tv_mall_goods_sale);
        }
    }

    private class ClickGoods implements View.OnClickListener {
        private int position;

        public ClickGoods(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.adapterClickListener(position, v);
            }
        }
    }

}

