package com.vice.bloodpressure.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.vice.bloodpressure.R;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class NumberSeekBar extends AppCompatSeekBar {
    private int oldPaddingTop;

    private int oldPaddingLeft;

    private int oldPaddingRight;

    private int oldPaddingBottom;

    private boolean isMysetPadding = true;

    private String mText;

    private float mTextWidth;

    private float mImgWidth;

    private float mImgHei;

    private Paint mPaint;

    private Resources res;

    private Bitmap bm;

    private int textsize = 13;

    private int textpaddingleft;

    private int textpaddingtop;

    private int imagepaddingleft;

    private int imagepaddingtop;

    public NumberSeekBar(Context context) {
        super(context);
        init();
    }

    public NumberSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    // 屏蔽滑动
    // @Override
    // public boolean onTouchEvent(MotionEvent event) {
    // return false;
    // }

    /**
     * 77      * (非 Javadoc)
     * 78      *
     * 79      * @方法名: onTouchEvent
     * 80      * @描述: 不屏蔽屏蔽滑动
     * 81      * @日期: 2014-8-11 下午2:03:15
     * 82      * @param event
     * 83      * @return
     * 84      * @see android.widget.AbsSeekBar#onTouchEvent(android.view.MotionEvent)
     * 85
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    // 修改setpadding 使其在外部调用的时候无效
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (isMysetPadding) {
            super.setPadding(left, top, right, bottom);
        }
    }

    // 初始化
    private void init() {
        res = getResources();
//        initBitmap();
        initDraw();
        setPadding();
    }

    private void initDraw() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(textsize);
        mPaint.setColor(0xff23fc4f);
    }

    private void initBitmap() {
        bm = BitmapFactory.decodeResource(res, R.drawable.shape_tran_2_2_circle);
        if (bm != null) {
            mImgWidth = bm.getWidth();
            mImgHei = bm.getHeight();
        } else {
            mImgWidth = 0;
            mImgHei = 0;
        }
    }

    protected synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            mText = (getProgress() * 100 / getMax()) + "%";
            mTextWidth = mPaint.measureText(mText);
            Rect bounds = this.getProgressDrawable().getBounds();
            float xImg = bounds.width() * getProgress() / getMax()
                    + imagepaddingleft + oldPaddingLeft;
            float yImg = imagepaddingtop + oldPaddingTop;
            float xText = bounds.width() * getProgress() / getMax() + mImgWidth
                    / 2 - mTextWidth / 2 + textpaddingleft + oldPaddingLeft;
            float yText = yImg + textpaddingtop + mImgHei / 2 + getTextHei()
                    / 4;
            canvas.drawBitmap(bm, xImg, yImg, mPaint);
            canvas.drawText(mText, xText, yText, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化padding 使其左右上 留下位置用于展示进度图片
    private void setPadding() {
        int top = getBitmapHeigh() + oldPaddingTop;
        int left = getBitmapWidth() / 2 + oldPaddingLeft;
        int right = getBitmapWidth() / 2 + oldPaddingRight;
        int bottom = oldPaddingBottom;
        isMysetPadding = true;
        setPadding(left, top, right, bottom);
        isMysetPadding = false;
    }

    /**
     * 157      * 设置展示进度背景图片
     * 158      *
     * 159      * @param resid
     * 160
     */
    public void setBitmap(int resid) {
        bm = BitmapFactory.decodeResource(res, resid);
        if (bm != null) {
            mImgWidth = bm.getWidth();
            mImgHei = bm.getHeight();
        } else {
            mImgWidth = 0;
            mImgHei = 0;
        }
        setPadding();
    }

    /**
     * 174      * 替代setpadding
     * 175      *
     * 176      * @param left
     * 177      * @param top
     * 178      * @param right
     * 179      * @param bottom
     * 180
     */
    public void setMyPadding(int left, int top, int right, int bottom) {
        oldPaddingTop = top;
        oldPaddingLeft = left;
        oldPaddingRight = right;
        oldPaddingBottom = bottom;
        isMysetPadding = true;
        setPadding(left + getBitmapWidth() / 2, top + getBitmapHeigh(), right
                + getBitmapWidth() / 2, bottom);
        isMysetPadding = false;
    }

    /**
     * 193      * 设置进度字体大小
     * 194      *
     * 195      * @param textsize
     * 196
     */
    public void setTextSize(int textsize) {
        this.textsize = textsize;
        mPaint.setTextSize(textsize);
    }

    /**
     * 203      * 设置进度字体颜色
     * 204      *
     * 205      * @param color
     * 206
     */
    public void setTextColor(int color) {
        mPaint.setColor(color);
    }

    /**
     * 212      * 调整进度字体的位置 初始位置为图片的正中央
     * 213      *
     * 214      * @param top
     * 215      * @param left
     * 216
     */
    public void setTextPadding(int top, int left) {
        this.textpaddingleft = left;
        this.textpaddingtop = top;
    }

    /**
     * 223      * 调整进图背景图的位置 初始位置为进度条正上方、偏左一半
     * 224      *
     * 225      * @param top
     * 226      * @param left
     * 227
     */
    public void setImagePadding(int top, int left) {
        this.imagepaddingleft = left;
        this.imagepaddingtop = top;
    }

    private int getBitmapWidth() {
        return (int) Math.ceil(mImgWidth);
    }

    private int getBitmapHeigh() {
        return (int) Math.ceil(mImgHei);
    }

    private float getTextHei() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        return (float) Math.ceil(fm.descent - fm.top) + 2;
    }

    public int getTextpaddingleft() {
        return textpaddingleft;
    }

    public int getTextpaddingtop() {
        return textpaddingtop;
    }

    public int getImagepaddingleft() {
        return imagepaddingleft;
    }

    public int getImagepaddingtop() {
        return imagepaddingtop;
    }

    public int getTextsize() {
        return textsize;
    }

}