package com.vice.bloodpressure.utils.widget;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseadapter.SimpleFragmentAdapter;
import com.vice.bloodpressure.utils.AttrsUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.anim.OptAnimationLoader;
import com.vice.bloodpressure.utils.config.PictureConfig;
import com.vice.bloodpressure.utils.config.PictureMimeType;
import com.vice.bloodpressure.utils.entity.EventEntity;
import com.vice.bloodpressure.utils.entity.LocalMedia;
import com.vice.bloodpressure.utils.rxbus2.RxBus;
import com.vice.bloodpressure.utils.tools.ToastManage;
import com.vice.bloodpressure.utils.tools.VoiceUtils;
import com.vice.bloodpressure.utils.tools.observable.ImagesObservable;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.ui
 * email：893855882@qq.com
 * data：16/12/31
 */
public class XyPicturePreviewActivity extends XyPictureBaseActivity implements
        View.OnClickListener, Animation.AnimationListener, SimpleFragmentAdapter.OnCallBackActivity {
    private ImageView picture_left_back;
    private TextView tv_img_num, tv_title, tv_ok;
    private PreviewViewPager viewPager;
    private LinearLayout id_ll_ok;
    private LinearLayout originalLayout;
    private ImageView originalImageView;
    private int position;
    private LinearLayout ll_check;
    private List<LocalMedia> images = new ArrayList<>();
    private List<LocalMedia> selectImages = new ArrayList<>();
    private ImageView check;
    private SimpleFragmentAdapter adapter;
    private Animation animation;
    private boolean refresh;
    private int index;
    private int screenWidth;
    private Handler mHandler;
    private boolean numComplete;

    /**
     * EventBus 3.0 回调
     *
     * @param obj
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(EventEntity obj) {
        switch (obj.what) {
            case PictureConfig.CLOSE_PREVIEW_FLAG:
                // 压缩完后关闭预览界面
//                dismissDialog();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 150);
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numComplete = AttrsUtils.getTypeValueBoolean(this,
                R.attr.picture_style_numComplete);
        setContentView(R.layout.xysoft_picture_activity_preview);
        if (!RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().register(this);
        }
        mHandler = new Handler();
        screenWidth = ScreenUtils.getScreenWidth(this);
        animation = OptAnimationLoader.loadAnimation(this, R.anim.modal_in);
        animation.setAnimationListener(this);
        picture_left_back = findViewById(R.id.picture_left_back);
        viewPager = findViewById(R.id.preview_pager);
        ll_check = findViewById(R.id.ll_check);
        id_ll_ok = findViewById(R.id.id_ll_ok);
        check = findViewById(R.id.check);
        picture_left_back.setOnClickListener(this);
        tv_ok = findViewById(R.id.tv_ok);
        id_ll_ok.setOnClickListener(this);
        tv_img_num = findViewById(R.id.tv_img_num);
        tv_title = findViewById(R.id.picture_title);
        originalLayout = findViewById(R.id.hhsoft_ll_picture_original);
        originalImageView = findViewById(R.id.hhsoft_iv_picture_original);
        originalLayout.setVisibility(config.isOriginal ? View.VISIBLE : View.GONE);
        originalLayout.setOnClickListener(this);
        position = getIntent().getIntExtra(PictureConfig.EXTRA_POSITION, 0);
        tv_ok.setText(numComplete ? getString(R.string.hhsoft_picture_select_info,
                0, config.selectionMode == PictureConfig.SINGLE ? 1 : config.maxSelectNum)
                : getString(R.string.picture_please_select));

        tv_img_num.setSelected(config.checkNumMode ? true : false);

        selectImages = (List<LocalMedia>) getIntent().
                getSerializableExtra(PictureConfig.EXTRA_SELECT_LIST);
        boolean is_bottom_preview = getIntent().
                getBooleanExtra(PictureConfig.EXTRA_BOTTOM_PREVIEW, false);
        if (is_bottom_preview) {
            // 底部预览按钮过来
            images = (List<LocalMedia>) getIntent().
                    getSerializableExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST);
        } else {
            images = ImagesObservable.getInstance().readLocalMedias();
        }
        initViewPageAdapterData();
        ll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (images != null && images.size() > 0) {
                    LocalMedia image = images.get(viewPager.getCurrentItem());
                    String pictureType = selectImages.size() > 0 ?
                            selectImages.get(0).getPictureType() : "";
                    if (!TextUtils.isEmpty(pictureType)) {
                        boolean toEqual = PictureMimeType.
                                mimeToEqual(pictureType, image.getPictureType());
                        if (!toEqual) {
                            ToastManage.s(getPageContext(), getString(R.string.picture_rule));
                            return;
                        }
                    }
                    // 刷新图片列表中图片状态
                    boolean isChecked;
                    if (!check.isSelected()) {
                        isChecked = true;
                        check.setSelected(true);
                        check.startAnimation(animation);
                    } else {
                        isChecked = false;
                        check.setSelected(false);
                    }
                    if (selectImages.size() >= config.maxSelectNum && isChecked) {
                        ToastManage.s(getPageContext(), getString(R.string.picture_message_max_num, config.maxSelectNum + ""));
                        check.setSelected(false);
                        return;
                    }
                    if (isChecked) {
                        VoiceUtils.playVoice(getPageContext(), config.openClickSound);
                        // 如果是单选，则清空已选中的并刷新列表(作单一选择)
                        if (config.selectionMode == PictureConfig.SINGLE) {
                            singleRadioMediaImage();
                        }
                        selectImages.add(image);
                        image.setNum(selectImages.size());
                        if (config.checkNumMode) {
//                            check.setText(String.valueOf(image.getNum()));
                        }
                    } else {
                        for (LocalMedia media : selectImages) {
                            if (media.getPath().equals(image.getPath())) {
                                selectImages.remove(media);
                                subSelectPosition();
                                notifyCheckChanged(media);
                                break;
                            }
                        }
                    }
                    onSelectNumChange(true);
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                isPreviewEggs(config.previewEggs, position, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int i) {
                position = i;
                tv_title.setText(position + 1 + "/" + images.size());
                LocalMedia media = images.get(position);
                index = media.getPosition();
                if (!config.previewEggs) {
                    if (config.checkNumMode) {
//                        check.setText(media.getNum() + "");
//                        notifyCheckChanged(media);
                    }
                    onImageChecked(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 这里没实际意义，好处是预览图片时 滑动到屏幕一半以上可看到下一张图片是否选中了
     *
     * @param previewEggs          是否显示预览友好体验
     * @param positionOffsetPixels 滑动偏移量
     */
    private void isPreviewEggs(boolean previewEggs, int position, int positionOffsetPixels) {
        if (previewEggs) {
            if (images.size() > 0 && images != null) {
                LocalMedia media;
                int num;
                if (positionOffsetPixels < screenWidth / 2) {
                    media = images.get(position);
                    check.setSelected(isSelected(media));
                    if (config.checkNumMode) {
//                        num = media.getNum();
//                        check.setText(num + "");
//                        notifyCheckChanged(media);
//                        onImageChecked(position);
                    }
                } else {
                    media = images.get(position + 1);
                    check.setSelected(isSelected(media));
                    if (config.checkNumMode) {
//                        num = media.getNum();
//                        check.setText(num + "");
//                        notifyCheckChanged(media);
//                        onImageChecked(position + 1);
                    }
                }
            }
        }
    }

    /**
     * 单选图片
     */
    private void singleRadioMediaImage() {
        if (selectImages != null
                && selectImages.size() > 0) {
            LocalMedia media = selectImages.get(0);
            RxBus.getDefault().post(new EventEntity(PictureConfig.UPDATE_FLAG,
                            selectImages, media.getPosition()));
            selectImages.clear();
        }
    }

    /**
     * 初始化ViewPage数据
     */
    private void initViewPageAdapterData() {
        tv_title.setText(position + 1 + "/" + images.size());
        adapter = new SimpleFragmentAdapter(images, this, this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        onSelectNumChange(false);
        onImageChecked(position);
        if (images.size() > 0) {
            LocalMedia media = images.get(position);
            index = media.getPosition();
            if (config.checkNumMode) {
//                tv_img_num.setSelected(true);
//                check.setText(media.getNum() + "");
//                notifyCheckChanged(media);
            }
        }
    }

    /**
     * 选择按钮更新
     */
    private void notifyCheckChanged(LocalMedia imageBean) {
        if (config.checkNumMode) {
//            check.setText("");
//            for (LocalMedia media : selectImages) {
//                if (media.getPath().equals(imageBean.getPath())) {
//                    imageBean.setNum(media.getNum());
//                    check.setText(String.valueOf(imageBean.getNum()));
//                }
//            }
        }
    }

    /**
     * 更新选择的顺序
     */
    private void subSelectPosition() {
        for (int index = 0, len = selectImages.size(); index < len; index++) {
            LocalMedia media = selectImages.get(index);
            media.setNum(index + 1);
        }
    }

    /**
     * 判断当前图片是否选中
     *
     * @param position
     */
    public void onImageChecked(int position) {
        if (images != null && images.size() > 0) {
            LocalMedia media = images.get(position);
            check.setSelected(isSelected(media));
        } else {
            check.setSelected(false);
        }
    }

    /**
     * 当前图片是否选中
     *
     * @param image
     * @return
     */
    public boolean isSelected(LocalMedia image) {
        for (LocalMedia media : selectImages) {
            if (media.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新图片选择数量
     */

    public void onSelectNumChange(boolean isRefresh) {
        this.refresh = isRefresh;
        boolean enable = selectImages.size() != 0;
        if (enable) {
            tv_ok.setSelected(true);
            id_ll_ok.setEnabled(true);
            if (numComplete) {
//                tv_ok.setText(getString(R.string.picture_done_front_num, selectImages.size(),
//                        config.selectionMode == PictureConfig.SINGLE ? 1 : config.maxSelectNum));
                tv_ok.setText(getString(R.string.hhsoft_picture_select_info, selectImages.size(),
                        config.selectionMode == PictureConfig.SINGLE ? 1 : config.maxSelectNum));
            } else {
                if (refresh) {
                    tv_img_num.startAnimation(animation);
                }
                tv_img_num.setVisibility(View.VISIBLE);
                tv_img_num.setText(String.valueOf(selectImages.size()));
                tv_ok.setText(getString(R.string.picture_completed));
            }
        } else {
            id_ll_ok.setEnabled(false);
            tv_ok.setSelected(false);
            if (numComplete) {
//                tv_ok.setText(getString(R.string.picture_done_front_num, 0,
//                        config.selectionMode == PictureConfig.SINGLE ? 1 : config.maxSelectNum));
                tv_ok.setText(getString(R.string.hhsoft_picture_select_info, 0,
                        config.selectionMode == PictureConfig.SINGLE ? 1 : config.maxSelectNum));
            } else {
                tv_img_num.setVisibility(View.INVISIBLE);
                tv_ok.setText(getString(R.string.picture_please_select));
            }
        }
        updateSelector(refresh);
    }

    /**
     * 更新图片列表选中效果
     *
     * @param isRefresh
     */
    private void updateSelector(boolean isRefresh) {
        if (isRefresh) {
            EventEntity obj = new EventEntity(PictureConfig.UPDATE_FLAG, selectImages, index);
            RxBus.getDefault().post(obj);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        updateSelector(refresh);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.picture_left_back) {
            onBackPressed();
        }
        if (id == R.id.id_ll_ok) {
            // 如果设置了图片最小选择数量，则判断是否满足条件
            int size = selectImages.size();
            LocalMedia image = selectImages.size() > 0 ? selectImages.get(0) : null;
            String pictureType = image != null ? image.getPictureType() : "";
            if (config.minSelectNum > 0) {
                if (size < config.minSelectNum && config.selectionMode == PictureConfig.MULTIPLE) {
                    boolean eqImg = pictureType.startsWith(PictureConfig.IMAGE);
                    String str = eqImg ? getString(R.string.picture_min_img_num, config.minSelectNum + "")
                            : getString(R.string.picture_min_video_num, config.minSelectNum + "");
                    ToastManage.s(getPageContext(), str);
                    return;
                }
            }
            if (config.enableCrop && pictureType.startsWith(PictureConfig.IMAGE)) {
//                if (config.selectionMode == PictureConfig.SINGLE) {
//                    originalPath = image.getPath();
//                    startCrop(originalPath);
//                } else {
//                    // 是图片和选择压缩并且是多张，调用批量压缩
//                    ArrayList<String> cuts = new ArrayList<>();
//                    for (LocalMedia media : selectImages) {
//                        cuts.add(media.getPath());
//                    }
//                    startCrop(cuts);
//                }
            } else {
                onResult(selectImages);
            }
        }
        if (id == R.id.hhsoft_ll_picture_original) {
            //原图
            boolean isSelected = originalImageView.isSelected();
            originalImageView.setSelected(!isSelected);
            config.isCompress=!isSelected;
        }
    }

    /**
     * return image result
     *
     * @param images
     */
    protected void onResult(List<LocalMedia> images) {
        RxBus.getDefault().post(new EventEntity(PictureConfig.PREVIEW_DATA_FLAG, images));
        // 如果开启了压缩，先不关闭此页面，PictureImageGridActivity压缩完在通知关闭
        if (originalImageView.isSelected()){
            onBackPressed();
        }else {
            if (!config.isCompress) {
                onBackPressed();
            } else {
                if (!isFinishing()){
                    ToastUtils.getInstance().showProgressDialog(getPageContext(),R.string.waiting,false);
                }
            }
        }

    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case UCropMulti.REQUEST_MULTI_CROP:
                    List<CutInfo> list = UCropMulti.getOutput(data);
                    setResult(RESULT_OK, new Intent().putExtra(UCropMulti.EXTRA_OUTPUT_URI_LIST,
                            (Serializable) list));
                    finish();
                    break;
                case UCrop.REQUEST_CROP:
                    if (data != null) {
                        setResult(RESULT_OK, data);
                    }
                    finish();
                    break;
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Throwable throwable = (Throwable) data.getSerializableExtra(UCrop.EXTRA_ERROR);
            ToastManage.s(mContext,throwable.getMessage());
        }
    }*/


    @Override
    public void onBackPressed() {
        closeActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (animation != null) {
            animation.cancel();
            animation = null;
        }
    }

    @Override
    public void onActivityBackPressed() {
        onBackPressed();
    }
}
