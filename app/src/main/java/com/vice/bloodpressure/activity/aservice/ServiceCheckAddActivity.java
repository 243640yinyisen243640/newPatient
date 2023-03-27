package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IImageBrower;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.utils.entity.LocalMedia;
import com.vice.bloodpressure.utils.widget.PictureSelector;
import com.vice.bloodpressure.view.image.GalleryUploadImageInfo;
import com.vice.bloodpressure.view.image.GalleryUploadImageView;

import java.util.ArrayList;
import java.util.List;

import static com.vice.bloodpressure.utils.config.PictureConfig.CHOOSE_REQUEST;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:添加检验检查数据
 */
public class ServiceCheckAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private EditText nameEditText;
    private TextView timeTextView;
    private LinearLayout sureLinearLayout;
    private GalleryUploadImageView uploadImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加检验检查数据");
        initView();
        initValues();
        initListener();
    }

    private void initValues() {
        GalleryUploadImageView.Builder builder = new GalleryUploadImageView.Builder(getPageContext())
                .maxCount(9)
                .rowMaxCount(4)
                .defaultImage(R.drawable.choose_pic_default)
                .paddingWidth(DensityUtils.dip2px(getPageContext(), 10))
                .totalWidth(ScreenUtils.screenWidth(getPageContext()) - DensityUtils.dip2px(getPageContext(), 40))
                .uploadImageListener(new GalleryUploadImageView.IGalleryUploadImageListener() {

                    @Override
                    public void onChooseImage(int count) {
                        XyImageUtils.getImagePictureSelector(getPageContext(), 1, count, true);

                    }

                    @Override
                    public void onLoadImage(String imagePath, ImageView imageView) {
                        //                        HHSoftImageUtils.loadImage(getPageContext(), R.drawable.album_upload, imagePath, imageView);

                    }

                    @Override
                    public void onDeleteImage(int position, String galleryId) {

                    }


                    @Override
                    public void onBrowerImage(int position, List<GalleryUploadImageInfo> list) {
                        //查看大图
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setBigImage(list.get(i).thumbImage());
                        }
                        XyImageUtils.lookBigImage(getPageContext(), position, (ArrayList<? extends IImageBrower>) list);
                    }
                });
        uploadImageView.init(builder);
    }

    private void initListener() {

    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_check, null);
        nameEditText = view.findViewById(R.id.et_service_check_add_name);
        timeTextView = view.findViewById(R.id.tv_service_check_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_check_sure);
        uploadImageView = view.findViewById(R.id.guiv_check_add);

        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    String imagePath;
                    List<String> mList = new ArrayList<>();
                    for (int i = 0; i < selectList.size(); i++) {
                        if (selectList.get(i).isCompressed()) {
                            imagePath = selectList.get(i).getCompressPath();
                        } else {
                            imagePath = selectList.get(i).getPath();
                        }
                        mList.add(imagePath);

                    }
                    uploadImageView.addItems(mList);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_check_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_check_sure:
                break;
            default:
                break;
        }
    }
}
