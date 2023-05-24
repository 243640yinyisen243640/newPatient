package com.vice.bloodpressure.view.image;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.utils.XyImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhsoft on 2019/2/22.
 */

public class GalleryUploadImageView extends LinearLayout {
    private static final String TAG = "GalleryUploadImageView";
    private Builder builder;
    private LayoutParams rowParams;//每行的布局参数
    private LayoutParams itemLayoutParams;//每行图片的布局参数
    private LayoutParams itemLastLayoutParams;//每行最后一张图片的布局参数
    private FrameLayout.LayoutParams itemImageParams;//每张图片的布局参数
    private int itemImageWidth;
    private int pxPadding = 0;
    private List<GalleryUploadImageInfo> mList;

    public GalleryUploadImageView(Context context) {
        super(context);
    }

    public GalleryUploadImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Builder builder) {
        this.builder = builder;
        initVariable();
        initView();
    }

    private void initVariable() {
        int match = LayoutParams.MATCH_PARENT;
        int wrap = LayoutParams.WRAP_CONTENT;
        pxPadding = builder.mPaddingWidth;
        rowParams = new LayoutParams(match, wrap);
        rowParams.setMargins(0, 0, 0, pxPadding);

        itemImageWidth = (builder.mTotalWidth - pxPadding * (builder.mRowMaxCount - 1)) / builder.mRowMaxCount;
        itemLayoutParams = new LayoutParams(itemImageWidth, itemImageWidth);
        itemLayoutParams.setMargins(0, 0, pxPadding, 0);
        itemLastLayoutParams = new LayoutParams(itemImageWidth, itemImageWidth);
        itemImageParams = new FrameLayout.LayoutParams(itemImageWidth, itemImageWidth);
    }

    private void initView() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        this.setOrientation(VERTICAL);
        List<String> tempList = new ArrayList<>();
        if (builder.mIsEdit){
            tempList.add("add");
        }
        addItems(tempList);
    }

    public void addItems(List<String> tempList) {
        if (tempList == null || tempList.size() == 0) {
            return;
        }
        for (int i = 0; i < tempList.size(); i++) {
            GalleryUploadImageInfo model = new GalleryUploadImageInfo(tempList.get(i));
            if (mList.size() == 0) {
                mList.add(model);
            } else {
                mList.add(mList.size() - 1, model);
            }
        }
        if (mList.size() == (builder.mMaxCount + 1)) {
            mList.remove(mList.size() - 1);
        }
        addItemsView(mList);
    }

    public void addItemsForServer(List<GalleryUploadImageInfo> tempList) {
        if (tempList == null || tempList.size() == 0) {
            return;
        }
        for (int i = 0; i < tempList.size(); i++) {
            GalleryUploadImageInfo model = tempList.get(i);
            if (mList.size() == 0) {
                mList.add(model);
            } else {
                mList.add(mList.size() - 1, model);
            }
        }
        if (mList.size() == (builder.mMaxCount + 1)) {
            mList.remove(mList.size() - 1);
        }
        addItemsView(mList);
    }

    /**
     * 所有图片数量，包含服务器的
     *
     * @return
     */
    public int getAllImageSize() {
        if (mList == null) {
            return 0;
        }
        List<GalleryUploadImageInfo> imageList = new ArrayList<>();
        for (GalleryUploadImageInfo gallery : mList) {
            if (!"add".equals(gallery.thumbImage())) {
                imageList.add(gallery);
            }
        }
        return imageList.size();
    }

    /**
     * 获取本地选择，可上传图片数量
     *
     * @return
     */
    public int getChooseImageSize() {
        if (mList == null) {
            return 0;
        }

        return getChooseImageList().size();
    }

    /**
     * 获取本地选择，可上传图片集合
     *
     * @return
     */
    public List<GalleryUploadImageInfo> getChooseImageList() {
        List<GalleryUploadImageInfo> imageList = new ArrayList<>();
        imageList.addAll(mList);
        if ("add".equals(imageList.get(imageList.size() - 1).thumbImage())) {
            imageList.remove(imageList.size() - 1);
        }
        return imageList;
    }

    public void deleteImage(int position) {
        mList.remove(position);
        if (!"add".equals(mList.get(mList.size() - 1).thumbImage())) {
            GalleryUploadImageInfo tempModel = new GalleryUploadImageInfo("add");
            mList.add(tempModel);
        }
        addItemsView(mList);
    }

    private void addItemsView(List<GalleryUploadImageInfo> list) {
        this.removeAllViews();
        int allCount = list.size();
        int rowMaxCount = builder.mRowMaxCount;
        int rowCount = allCount / rowMaxCount + (allCount % rowMaxCount > 0 ? 1 : 0);// 行数
        for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
            LinearLayout rowLayout = new LinearLayout(getContext());
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setLayoutParams(rowParams);
            this.addView(rowLayout);
            int columnCount = allCount % rowMaxCount == 0 ? rowMaxCount : allCount % rowMaxCount;
            if (rowCursor != rowCount - 1) {
                columnCount = rowMaxCount;
            }
            int rowOffset = rowCursor * rowMaxCount;// 行偏移
            for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                int position = columnCursor + rowOffset;
                rowLayout.addView(initItemView(position, columnCursor));
            }
        }
    }

    private View initItemView(final int position, int columnCursor) {
        final GalleryUploadImageInfo model = mList.get(position);
        View view = View.inflate(getContext(), R.layout.item_gallery_upload_list, null);
        FrameLayout itemLayout = view.findViewById(R.id.fl_gallery_upload_item);
        ImageView imageView = view.findViewById(R.id.iv_gallery_upload_image);
        ImageView deleteImageView = view.findViewById(R.id.iv_gallery_upload_delete);
        if (columnCursor == builder.mRowMaxCount) {
            itemLayout.setLayoutParams(itemLastLayoutParams);
        } else {
            itemLayout.setLayoutParams(itemLayoutParams);
        }
        imageView.setLayoutParams(itemImageParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.mListener != null) {
                    if ("add".equals(model.thumbImage())) {
                        builder.mListener.onChooseImage(builder.mMaxCount - getAllImageSize());
                    } else {
                        //查看大图
                        List<GalleryUploadImageInfo> imagesList = new ArrayList<>();
                        int imageSize = mList.size();
                        if ("add".equals(mList.get(imageSize - 1).thumbImage())) {
                            imageSize = imageSize - 1;
                        }
                        for (int i = 0; i < imageSize; i++) {
                            imagesList.add(mList.get(i));
                        }
                        if (builder.mListener != null) {
                            builder.mListener.onBrowerImage(position, imagesList);
                        }
                    }
                }
            }
        });


        if (builder.mIsEdit){
            if ("add".equals(model.thumbImage())) {
                imageView.setImageResource(R.drawable.choose_pic_default);
                deleteImageView.setVisibility(GONE);
            } else {
                if (builder.mListener != null) {
                    builder.mListener.onLoadImage(model.getBigImage(), imageView);
                }
                XyImageUtils.loadRoundImage(getContext(), getDefaultImage(), model.thumbImage(), imageView);
                deleteImageView.setVisibility(VISIBLE);
                deleteImageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteImage(position);
                        if (builder.mListener != null) {
                            builder.mListener.onDeleteImage(position, mList.get(position).getGalleryId());
                        }
                    }
                });
            }
        }else {
            XyImageUtils.loadRoundImage(getContext(), getDefaultImage(), model.thumbImage(), imageView);
            deleteImageView.setVisibility(GONE);
        }

        return view;
    }

    private int getDefaultImage() {
        if (-1 == this.builder.mDefaultImageRes) {
            return R.drawable.choose_pic_default;
        }
        return this.builder.mDefaultImageRes;
    }

    public static class Builder {
        private Context mContext;
        private boolean mIsLoadGif = false;
        private int mMaxCount = 9;
        private int mTotalWidth;//px
        private int mRowMaxCount = 4;
        private int mPaddingWidth = 20;//默认20px
        private int mDefaultImageRes = -1;
        private int mDefaultAddRes = -1;
        //是否编辑   编辑可删除
        private boolean mIsEdit = false;
        private IGalleryUploadImageListener mListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder isLoadGif(boolean isLoadGif) {
            this.mIsLoadGif = isLoadGif;
            return this;
        }

        public Builder maxCount(int count) {
            this.mMaxCount = count;
            return this;
        }

        public Builder totalWidth(int pxWidth) {
            this.mTotalWidth = pxWidth;
            return this;
        }

        public Builder rowMaxCount(int maxCount) {
            this.mRowMaxCount = maxCount;
            return this;
        }

        public Builder paddingWidth(int pxPaddingWidth) {
            this.mPaddingWidth = pxPaddingWidth;
            return this;
        }

        public Builder defaultImage(int defaultImageRes) {
            this.mDefaultImageRes = defaultImageRes;
            return this;
        }

        public Builder uploadImageListener(IGalleryUploadImageListener listener) {
            this.mListener = listener;
            return this;
        }

        public Builder isEdit(boolean isEdit) {
            this.mIsEdit = isEdit;
            return this;
        }

    }

    public interface IGalleryUploadImageListener {
        void onChooseImage(int count);

        void onLoadImage(String imagePath, ImageView imageView);

        void onDeleteImage(int position, String galleryId);

        void onBrowerImage(int position, List<GalleryUploadImageInfo> list);
    }
}

