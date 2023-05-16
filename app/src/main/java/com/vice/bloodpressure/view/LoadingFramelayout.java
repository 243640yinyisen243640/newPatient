package com.vice.bloodpressure.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class LoadingFramelayout extends LinearLayout {
    String TAG = "LoadingFramelayout";
    private ImageView mIvLoading;
    private Button mBtnReTry;
    private TextView mTextLoading;
    private RelativeLayout mLoadingView;
    private AnimationDrawable mAnimationDrawable;
    private LoadingFramelayout mLoadingFramelayout;
    private OnFragmentInteractionListener mOnFragmentInteractionListener;
    private OnFragmentInteractionListener mListener;

    public LoadingFramelayout(Context context) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        //mInflater.inflate(res,this);
        View rootView = mInflater.inflate(R.layout.fragment_loading_framelayout,this);//该布局的外部再嵌套一层父布局
        mLoadingView =  rootView.findViewById(R.id.load_view);
        mIvLoading = (ImageView) rootView.findViewById(R.id.iv_loading);
        mBtnReTry = (Button) rootView.findViewById(R.id.iv_btn_retry);
        mTextLoading = rootView.findViewById(R.id.iv_LoadingText);
        mBtnReTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFragmentInteractionListener.onReload();
            }
        });
        init(context);
    }

    public LoadingFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater mInflater = LayoutInflater.from(context);
        //mInflater.inflate(res,this);
        View rootView = mInflater.inflate(R.layout.fragment_loading_framelayout,this);//该布局的外部再嵌套一层父布局
        mLoadingView =  rootView.findViewById(R.id.load_view);
        mIvLoading = (ImageView) rootView.findViewById(R.id.iv_loading);
        mBtnReTry = (Button) rootView.findViewById(R.id.iv_btn_retry);
        mTextLoading = rootView.findViewById(R.id.iv_LoadingText);
        mBtnReTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFragmentInteractionListener.onReload();
            }
        });
        init(context);
    }

    public LoadingFramelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater mInflater = LayoutInflater.from(context);
        //mInflater.inflate(res,this);
        View rootView = mInflater.inflate(R.layout.fragment_loading_framelayout,this);//该布局的外部再嵌套一层父布局
        mLoadingView =  rootView.findViewById(R.id.load_view);
        mIvLoading = (ImageView) rootView.findViewById(R.id.iv_loading);
        mBtnReTry = (Button) rootView.findViewById(R.id.iv_btn_retry);
        mTextLoading = rootView.findViewById(R.id.iv_LoadingText);
        mBtnReTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFragmentInteractionListener.onReload();
            }
        });
        init(context);
    }

    public LoadingFramelayout(Context context, @LayoutRes int res) {
        // Required empty public constructor
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        //mInflater.inflate(res,this);
        View rootView = mInflater.inflate(R.layout.fragment_loading_framelayout,this);//该布局的外部再嵌套一层父布局
        mLoadingView =  rootView.findViewById(R.id.load_view);
        mIvLoading = (ImageView) rootView.findViewById(R.id.iv_loading);
        mBtnReTry = (Button) rootView.findViewById(R.id.iv_btn_retry);
        mTextLoading = rootView.findViewById(R.id.iv_LoadingText);
        mBtnReTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFragmentInteractionListener.onReload();
            }
        });
        init(context);
    }

    public LoadingFramelayout(Context context,View view) {
        super(context);
        addView(view);
        LayoutInflater mInflater = LayoutInflater.from(context);
        View rootView = mInflater.inflate(R.layout.fragment_loading_framelayout,this);
        mLoadingView =  rootView.findViewById(R.id.load_view);
        mIvLoading = (ImageView) rootView.findViewById(R.id.iv_loading);
        mBtnReTry = (Button) rootView.findViewById(R.id.iv_btn_retry);
        mBtnReTry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnFragmentInteractionListener.onReload();
            }
        });
        init(context);
    }

    private void init(Context context) {
        mAnimationDrawable = new AnimationDrawable();
        mAnimationDrawable.setOneShot(false);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_01),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_02),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_03),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_04),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_05),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_06),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_07),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_08),500);
        mAnimationDrawable.addFrame(ContextCompat.getDrawable(context,R.drawable.loading_09),500);
        mIvLoading.setImageDrawable(mAnimationDrawable);

        Log.d(TAG, "走到");
        mIvLoading.post(new Runnable() {
            @Override
            public void run() {
                mAnimationDrawable.start();
                Log.d(TAG, "走到线程内");
            }
        });
    }

    //这一个用于完成加载的调用方法
    public void completeLoading(){
        stopLoading();
        post(new Runnable() {
            @Override
            public void run() {
                mLoadingView.setVisibility(GONE);//加载完成后，隐藏整个布局
            }
        });
    }

    //成功和失败后停着加载，将动画stop
    public void stopLoading() {
        Log.d(TAG, "stopLoading");
        ((AnimationDrawable) mIvLoading.getDrawable()).stop();

    }

    //在加载失败后，点击加载再次加载的时候调用
    public void startLoading(){
        post(new Runnable() {
            @Override
            public void run() {
                mBtnReTry.setVisibility(GONE);
                mTextLoading.setText("加载");
                mIvLoading.setImageDrawable(mAnimationDrawable);
                ((AnimationDrawable)mIvLoading.getDrawable()).start();
            }
        });
    }

    //加载失败后，停着动画，将失败页面和重新加载按钮Visible出来
    public void loadingFailed(){
        stopLoading();
        post(new Runnable() {
            @Override
            public void run() {
//                mIvLoading.setImageResource(R.drawable.loading_fail);
                mBtnReTry.setVisibility(VISIBLE);
                mTextLoading.setText("加载失败");
            }
        });
    }

    public void setOnReloadListener(OnFragmentInteractionListener onReloadListener) {
        mOnFragmentInteractionListener = onReloadListener;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    //实现接口，重新加载用作回调
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onReload();
    }
}

