package com.vice.bloodpressure.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.utils.DensityUtils;

import java.text.DecimalFormat;

public class SaccharifySeekBar extends androidx.appcompat.widget.AppCompatSeekBar {
    private Context context;
    private Paint mPaint;
    private String mText;
    private float mTextWidth;
    private float mTextBaseLineY;
    private float mBgWidth = 50;
    private float mBgHeight = 50;

    public SaccharifySeekBar(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public SaccharifySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(context.getResources().getColor(R.color.main_base_color));
        mPaint.setTextSize(DensityUtils.sp2px(context, 20));

        //设置SeekBar顶部数值文字预留空间，左右为数值背景图片的一半，顶部为数值背景图片高度加五的间隔
        setPadding((int) Math.ceil(mBgWidth) / 2, (int) Math.ceil(mBgHeight) + 15, (int) Math.ceil(mBgWidth) / 2, 0);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        getTextLocation();
        Rect bgRect = getProgressDrawable().getBounds();
        //计算数值背景X坐标
        float bgX = bgRect.width() * getProgress() / getMax();
        //计算数值文字X坐标
        float textX = bgX + (mBgWidth - mTextWidth) / 2;
        canvas.drawText(mText, textX, mTextBaseLineY, mPaint);
    }

    /**
     * 计算SeekBar数值文字的显示位置
     */
    private void getTextLocation() {
        // Paint.FontMetrics fm = mPaint.getFontMetrics();
        //        mText = txfloat(getProgress());
        mText = String.valueOf(getProgress());
        //测量文字宽度
        mTextWidth = mPaint.measureText(mText);
        //计算文字基线Y坐标
        mTextBaseLineY = mBgHeight;/// 2 - fm.descent + (fm.descent - fm.ascent) / 2
    }

    private String txfloat(int a) {
        //        if (a == 0) {
        //            return "0";
        //        }
        //        if (100 == a) {
        //            return "10";
        //        }
        DecimalFormat df = new DecimalFormat("0.0");//设置保留位数
        return df.format((float) a / 10);
    }
}
