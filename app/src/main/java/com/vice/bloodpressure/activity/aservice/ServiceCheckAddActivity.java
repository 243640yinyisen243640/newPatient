package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.IImageBrower;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.GalleryInfo;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DataUtils;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyImageUtils;
import com.vice.bloodpressure.utils.entity.LocalMedia;
import com.vice.bloodpressure.utils.widget.PictureSelector;
import com.vice.bloodpressure.view.image.GalleryUploadImageInfo;
import com.vice.bloodpressure.view.image.GalleryUploadImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.vice.bloodpressure.utils.config.PictureConfig.CHOOSE_REQUEST;

/**
 * 作者: beauty
 * 类名:
 * 传参:type 1：添加 2：编辑   pkId：检验检查ID
 * 描述:添加检验检查数据
 */
public class ServiceCheckAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private EditText nameEditText;
    private TextView timeTextView;
    private LinearLayout sureLinearLayout;
    private GalleryUploadImageView uploadImageView;
    /**
     * 添加时间
     */
    private String addTime = "";
    /**
     * 检验检查ID
     */
    private String pkId = "";
    /**
     * 1：添加 2:编辑
     */
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pkId = getIntent().getStringExtra("pkId");
        type = getIntent().getStringExtra("type");


        initView();
        initValues();
        initListener();
        if ("1".equals(type)) {
            topViewManager().titleTextView().setText("添加检验检查数据");
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            sureLinearLayout.setVisibility(View.VISIBLE);
        } else {
            topViewManager().titleTextView().setText("检验检查详情");
            loadViewManager().changeLoadState(LoadStatus.LOADING);
            nameEditText.setEnabled(false);
            timeTextView.setEnabled(false);
            uploadImageView.setEnabled(false);
            sureLinearLayout.setVisibility(View.GONE);
        }

    }

    private void initValues() {
        GalleryUploadImageView.Builder builder = new GalleryUploadImageView.Builder(getPageContext())
                .maxCount(9)
                .rowMaxCount(3)
                .defaultImage(R.drawable.choose_pic_default)
                .paddingWidth(DensityUtils.dip2px(getPageContext(), 10))
                .isEdit("1".equals(type) ? true : false)
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
        sureLinearLayout.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
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
        Call<String> requestCall = ServiceDataManager.checkLook(pkId == null ? "" : pkId, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                HealthyDataChildInfo allInfo = (HealthyDataChildInfo) response.object;
                bindData(allInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("medicineLook", requestCall);
    }

    private void bindData(HealthyDataChildInfo allInfo) {

        nameEditText.setText(allInfo.getProjectName());

        timeTextView.setText(DataUtils.changeDataFormat(DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, DataFormatManager.TIME_FORMAT_Y_M_D, allInfo.getAddTime()));


        List<GalleryUploadImageInfo> uploadImageAuthList = new ArrayList<>();
        for (int i = 0; i < allInfo.getFileUrls().size(); i++) {
            uploadImageAuthList.add(new GalleryUploadImageInfo(allInfo.getFileUrls().get(i)));
        }

        uploadImageView.addItemsForServer(uploadImageAuthList);
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
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = object.toString();
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.ll_service_check_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }


    private void sureToAddData() {

        String content = nameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入项目名称");
            return;
        }
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入检测时间");
            return;
        }
        if (uploadImageView.getChooseImageList() == null || uploadImageView.getChooseImageList().size() == 0) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择图片");
            return;
        }


        Call<String> requestCall = ServiceDataManager.userUploadImgMultipleSheets(uploadImageView.getChooseImageList(), (call, response) -> {
            if ("0000".equals(response.code)) {
                GalleryInfo galleryInfo = (GalleryInfo) response.object;
                String imgString = "";
                imgString = galleryInfo.getName();

                commentSure(content, imgString);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userUploadImgMultipleSheets", requestCall);
    }


    private void commentSure(String content, String commentImgStr) {
        Call<String> requestCall = ServiceDataManager.checkAdd(UserInfoUtils.getArchivesId(getPageContext()), "2", content, commentImgStr, addTime, (call, response) ->
        {
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }

        }, (call, throwable) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("checkAdd", requestCall);
    }
}
