package com.vice.bloodpressure.baseadapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;
import com.vice.bloodpressure.utils.config.PictureConfig;
import com.vice.bloodpressure.utils.config.PictureMimeType;
import com.vice.bloodpressure.utils.config.PictureSelectionConfig;
import com.vice.bloodpressure.utils.entity.LocalMedia;
import com.vice.bloodpressure.utils.tools.HHSoftFileUtils;
import com.vice.bloodpressure.utils.tools.SdkVersionUtils;
import com.vice.bloodpressure.utils.tools.ToastManage;

import java.util.ArrayList;
import java.util.List;

public class HHSoftPictureGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PictureSelectionConfig config;
    private List<LocalMedia> photos = new ArrayList<>();
    private List<LocalMedia> selectedPhotos = new ArrayList<>();
    private OnPhotoSelectChangedListener imageSelectChangedListener;
    private Context context;
    private boolean showCamera;
    private int mimeType;
    private int overrideWidth, overrideHeight;
    private float sizeMultiplier;
    private boolean enablePreview, enablePreviewVideo;
    private int maxSelectNum;
    private int selectMode;
    /**
     * 单选图片
     */
    private boolean isGo;

    public HHSoftPictureGridAdapter(Context context, PictureSelectionConfig config) {
        this.context = context;
        this.config = config;
        this.showCamera = config.isCamera;
        this.mimeType = config.mimeType;
        this.overrideWidth = config.overrideWidth;
        this.overrideHeight = config.overrideHeight;
        this.sizeMultiplier = config.sizeMultiplier;
        this.enablePreview = config.enablePreview;
        this.enablePreviewVideo = config.enPreviewVideo;
        this.maxSelectNum = config.maxSelectNum;
        this.selectMode = config.selectionMode;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hhsoft_picture_item_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (getItemViewType(position) == PictureConfig.TYPE_CAMERA) {
            //首个拍摄功能
            holder.photoImageView.setImageResource(R.drawable.hhsoft_picture_camera);
            holder.checkImageView.setVisibility(View.GONE);
            holder.gifTextView.setVisibility(View.GONE);
            holder.videoTextView.setVisibility(View.GONE);
            holder.photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageSelectChangedListener != null) {
                        imageSelectChangedListener.onTakePhoto();
                    }
                }
            });
        } else {
            LocalMedia media = photos.get(showCamera ? position - 1 : position);
            media.position = holder.getAdapterPosition();
            selectImage(holder, isSelected(media), false);
            String pictureType = media.getPictureType();
            boolean isGif = PictureMimeType.isGif(pictureType);
            holder.gifTextView.setVisibility(isGif ? View.VISIBLE : View.GONE);
            int mediaMimeType = PictureMimeType.isPictureType(pictureType);
            //暂不考虑音频
            holder.videoTextView.setVisibility(mediaMimeType == PictureConfig.TYPE_VIDEO ? View.VISIBLE : View.GONE);
            holder.videoTextView.setText(XyTimeUtils.timeParse(media.getDuration()));
            RequestOptions options = new RequestOptions();
            if (overrideWidth <= 0 && overrideHeight <= 0) {
                options.sizeMultiplier(sizeMultiplier);
            } else {
                options.override(overrideWidth, overrideHeight);
            }
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            options.centerCrop();
            options.placeholder(R.drawable.hhsoft_picture_grid_bg);
//            Log.e("chen","onBindViewHolder=="+media.getPath());
            Glide.with(context)
                    .asBitmap()
                    .load(media.getPath())
                    .apply(options)
                    .into(holder.photoImageView);
            if (enablePreview || enablePreviewVideo) {
                holder.checkLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!hasMediaFile(media.getPath(), mediaMimeType)) {
                            return;
                        }
                        changeCheckboxState(holder, media);
                    }
                });
            }
            holder.photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!hasMediaFile(media.getPath(), mediaMimeType)) {
                        return;
                    }
                    //判断contentView点击时间是执行预览功能还是改变选中状态
                    int index = showCamera ? position - 1 : position;
                    boolean eqResult = mediaMimeType == PictureConfig.TYPE_IMAGE && enablePreview
                            || mediaMimeType == PictureConfig.TYPE_VIDEO && (enablePreviewVideo || selectMode == PictureConfig.SINGLE);
                    if (eqResult) {
                        imageSelectChangedListener.onPictureClick(media, index);
                    } else {
                        changeCheckboxState(holder, media);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return showCamera ? photos.size() + 1 : photos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (showCamera && position == 0) {
            return PictureConfig.TYPE_CAMERA;
        } else {
            return PictureConfig.TYPE_PICTURE;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;
        LinearLayout checkLayout;
        ImageView checkImageView;
        TextView videoTextView;
        TextView gifTextView;
        View contentView;

        public ViewHolder(View itemView) {
            super(itemView);
            contentView = itemView;
            photoImageView = itemView.findViewById(R.id.hhsoft_iv_picture_grid_photo);
            checkLayout = itemView.findViewById(R.id.hhsoft_ll_picture_grid_select);
            checkImageView = itemView.findViewById(R.id.hhsoft_iv_picture_grid_select);
            videoTextView = itemView.findViewById(R.id.hhsoft_tv_picture_grid_video);
            gifTextView = itemView.findViewById(R.id.hhsoft_tv_picture_grid_gif);
            int width = (ScreenUtils.screenWidth(context) - DensityUtils.dip2px(context, 10)) / 4;
            photoImageView.getLayoutParams().width = width;
            photoImageView.getLayoutParams().height = width;
            contentView.getLayoutParams().width = width;
            contentView.getLayoutParams().height = width;
        }
    }

    public boolean isSelected(LocalMedia image) {
        for (LocalMedia media : selectedPhotos) {
            if (media.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 选中的图片并执行动画
     *
     * @param holder
     * @param isChecked
     * @param isAnim
     */
    public void selectImage(ViewHolder holder, boolean isChecked, boolean isAnim) {
        holder.checkImageView.setSelected(isChecked);
//        if (isChecked) {
//            if (isAnim) {
//                if (animation != null) {
//                    holder.check.startAnimation(animation);
//                }
//            }
//            holder.iv_picture.setColorFilter(ContextCompat.getColor
//                    (context, R.color.image_overlay_true), PorterDuff.Mode.SRC_ATOP);
//        } else {
//            holder.iv_picture.setColorFilter(ContextCompat.getColor
//                    (context, R.color.image_overlay_false), PorterDuff.Mode.SRC_ATOP);
//        }
    }

    /**
     * 改变图片选中状态
     *
     * @param holder
     * @param image
     */

    private void changeCheckboxState(ViewHolder holder, LocalMedia image) {
        boolean isChecked = holder.checkImageView.isSelected();
        //判断两者图片是否是同一类型图片，及图片和视频不能一块选择
        String pictureType = selectedPhotos.size() > 0 ? selectedPhotos.get(0).getPictureType() : "";
        if (!TextUtils.isEmpty(pictureType)) {
            boolean toEqual = PictureMimeType.mimeToEqual(pictureType, image.getPictureType());
            if (!toEqual) {
                ToastManage.s(context, context.getString(R.string.picture_rule));
                return;
            }
        }
        //判断选择图片或视频的最大数量
        if (selectedPhotos.size() >= maxSelectNum && !isChecked) {
            boolean eqImg = pictureType.startsWith(PictureConfig.IMAGE);
            String str = eqImg ? context.getString(R.string.picture_message_max_num, maxSelectNum + "")
                    : context.getString(R.string.picture_message_video_max_num, maxSelectNum + "");
            ToastManage.s(context, str);
            return;
        }

        if (isChecked) {
            for (LocalMedia media : selectedPhotos) {
                if (media.getPath().equals(image.getPath())) {
                    selectedPhotos.remove(media);
                    break;
                }
            }
        } else {
            // 如果是单选，则清空已选中的并刷新列表(作单一选择)
            if (selectMode == PictureConfig.SINGLE) {
                singleRadioMediaImage();
            }
            selectedPhotos.add(image);
            image.setNum(selectedPhotos.size());
        }
        //通知点击项发生了改变
        notifyItemChanged(holder.getAdapterPosition());
        selectImage(holder, !isChecked, true);
        if (imageSelectChangedListener != null) {
            imageSelectChangedListener.onChange(selectedPhotos);
        }
    }

    /**
     * 单选模式
     */
    private void singleRadioMediaImage() {
        if (selectedPhotos != null && selectedPhotos.size() > 0) {
            isGo = true;
            LocalMedia media = selectedPhotos.get(0);
            notifyItemChanged(config.isCamera ? media.position : isGo ? media.position : media.position > 0 ? media.position - 1 : 0);
            selectedPhotos.clear();
        }
    }


    /**
     * 判断文件是否存在，
     * 当我们通过Content Uri拿到路径之后，是无法通过File来判断文件是否存在，即file.exist()会总是为False。所以我们借助于ContentResolver来判断对应于一个同步 I/O 调用，易造成线程等待
     * 其余的默认用file判断
     *
     * @param path
     * @param mediaMimeType
     * @return
     */
    public boolean hasMediaFile(String path, int mediaMimeType) {
       /* String newPath;
        if (SdkVersionUtils.checkedAndroid_Q()) {
            if (SdkVersionUtils.checkedUriScheme_CONTENT(Uri.parse(path))) {
                newPath = PictureFileUtils.getPath(context, Uri.parse(path));
            } else {
                newPath = path;
            }
        } else {
            newPath = path;
        }
//        Log.e("chen","hasMediaFile=="+path+"=="+newPath);
        //checkedAndroid_Q开启
//        hasMediaFile==content://media/external/file/154==/storage/emulated/0/Download/Browser/c-ssl.duitang.jpg
        //checkedAndroid_Q不开启
//        hasMediaFile==/storage/emulated/0/DCIM/Camera/IMG20210130103635.jpg==/storage/emulated/0/DCIM/Camera/IMG20210130103635.jpg
        if (TextUtils.isEmpty(newPath)) {
            if (!HHSoftFileUtils.isContentUriExists(context, Uri.parse(path))) {
                ToastManage.s(context, PictureMimeType.s(context, mediaMimeType));
                return false;
            }
        } else {
            if (!HHSoftFileUtils.isFileExist(newPath)) {
                ToastManage.s(context, PictureMimeType.s(context, mediaMimeType));
                return false;
            }
        }*/

        if (SdkVersionUtils.checkedUriScheme_CONTENT(Uri.parse(path))) {
            if (!HHSoftFileUtils.isContentUriExists(context, Uri.parse(path))) {
                ToastManage.s(context, PictureMimeType.s(context, mediaMimeType));
                return false;
            }
        } else {
            if (!HHSoftFileUtils.isFileExist(path)) {
                ToastManage.s(context, PictureMimeType.s(context, mediaMimeType));
                return false;
            }
        }
        return true;
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    public void bindImagesData(List<LocalMedia> images) {
        this.photos = images;
        this.notifyDataSetChanged();
    }

    public void clear() {
        if (this.photos != null) {
            this.photos.clear();
        }
        if (this.selectedPhotos != null) {
            this.selectedPhotos.clear();
        }
        if (imageSelectChangedListener != null) {
            imageSelectChangedListener.onChange(selectedPhotos);
        }
        this.notifyDataSetChanged();
    }

    public void bindSelectImages(List<LocalMedia> images) {
        // 这里重新构构造一个新集合，不然会产生已选集合一变，结果集合也会添加的问题
        List<LocalMedia> selection = new ArrayList<>();
        for (LocalMedia media : images) {
            selection.add(media);
        }
        if (this.selectedPhotos != null) {
            this.selectedPhotos.clear();
        }
        this.selectedPhotos = selection;
        if (imageSelectChangedListener != null) {
            imageSelectChangedListener.onChange(selectedPhotos);
        }
    }

    public List<LocalMedia> getPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        return photos;
    }

    public List<LocalMedia> getSelectedPhotos() {
        if (selectedPhotos == null) {
            selectedPhotos = new ArrayList<>();
        }
        return selectedPhotos;
    }

    public void setOnPhotoSelectChangedListener(OnPhotoSelectChangedListener
                                                        imageSelectChangedListener) {
        this.imageSelectChangedListener = imageSelectChangedListener;
    }

    public interface OnPhotoSelectChangedListener {
        /**
         * 拍照回调
         */
        void onTakePhoto();

        /**
         * 已选Media回调
         *
         * @param selectImages
         */
        void onChange(List<LocalMedia> selectImages);

        /**
         * 图片预览回调
         *
         * @param media
         * @param position
         */
        void onPictureClick(LocalMedia media, int position);
    }


}
