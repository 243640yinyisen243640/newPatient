package com.vice.bloodpressure.activity.auser;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.modules.zxing.utils.QRCodeUtils;
import com.vice.bloodpressure.utils.XyImageUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserQRCodeActivity extends UIBaseActivity {
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView phoneTextView;
    private ImageView qrImageView;
    private ImageView qrHeadImageView;

    private String archivesId;
    private String nickName;
    private String phoneNumber;
    private String avatar;
    private String sex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的二维码");
        archivesId = getIntent().getStringExtra("archivesId");
        nickName = getIntent().getStringExtra("nickName");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        avatar = getIntent().getStringExtra("avatar");
        sex = getIntent().getStringExtra("sex");
        initView();
        initValues();
    }

    private void initValues() {
        Bitmap bitmap = QRCodeUtils.createQRCodeBitmap("archivesId=="+archivesId, 150, 150);
        Log.i("yys", "bitmap==" + bitmap);
        qrImageView.setImageBitmap(bitmap);
        if ("1".equals(sex)){
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.user_center_default_head_img, archivesId, headImageView);
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.user_center_default_head_img, archivesId, qrHeadImageView);
        }else {
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.default_female_head, archivesId, headImageView);
            XyImageUtils.loadRoundImage(getPageContext(), R.drawable.default_female_head, archivesId, qrHeadImageView);
        }

        nameTextView.setText(nickName);
        phoneTextView.setText(phoneNumber);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_center_qr_code, null);
        containerView().addView(view);
        headImageView = view.findViewById(R.id.iv_user_qr_avatar);
        nameTextView = view.findViewById(R.id.tv_user_qr_nickname);
        phoneTextView = view.findViewById(R.id.tv_user_qr_phone);
        qrImageView = view.findViewById(R.id.iv_user_qr_code);
        qrHeadImageView = view.findViewById(R.id.iv_user_qr_code_head);

    }


}
