package com.intbit.rentbnb.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.models.Thumbnail;
import com.intbit.rentbnb.support.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sakthivel on 14/11/16.
 */
public class ThumbnailImageRecyclerViewAdapter extends RecyclerView.Adapter<ThumbnailImageRecyclerViewAdapter.ViewHolder> {

    public Context mContext;
    List<Thumbnail> thumbnailList;

    public ThumbnailImageRecyclerViewAdapter(Context context, List<Thumbnail> thumbnailList) {
        super();
        this.mContext = context;
        this.thumbnailList = thumbnailList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem_thumbnails_recyclerview, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (i == 0) {
        //if (i == (getItemCount()-1)) {
            viewHolder.thumbnailImageView.setImageResource(R.drawable.ic_add_photo_icon);
        } else {
            File file = new File(thumbnailList.get(i).getImageUrl());
            Uri imageUri = Uri.fromFile(file);
            String path = ImageUtil.getPath(mContext, imageUri);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap processedBitmap = adjustImageOrientation(bitmap, path);
            viewHolder.thumbnailImageView.setImageBitmap(processedBitmap);
        }
    }

    private Bitmap adjustImageOrientation(Bitmap image, String picturePath) {
        ExifInterface exif;
        try {
            exif = new ExifInterface(picturePath);
            int exifOrientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            int rotate = 0;
            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
            }

            if (rotate != 0) {
                int w = image.getWidth();
                int h = image.getHeight();

                // Setting pre rotate
                Matrix mtx = new Matrix();
                mtx.preRotate(rotate);

                // Rotating Bitmap & convert to ARGB_8888, required by tess
                image = Bitmap.createBitmap(image, 0, 0, w, h, mtx, false);

            }
        } catch (IOException e) {
            return null;
        }
        return image.copy(Bitmap.Config.ARGB_8888, true);
    }

    public void addThumbnail(String url) {
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setImageUrl(url);
        thumbnailList.add(thumbnail);
        //thumbnailList.add(0, thumbnail);
        notifyDataSetChanged();
    }

    public void remove(final int position) {
        //mItems.remove(position);
        notifyItemRemoved(position);
    }

    public Thumbnail getItem(int position) {
        return thumbnailList != null ? thumbnailList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.thumbnailImageView = (ImageView) itemView.findViewById(R.id.rowItem_thumbnails_ImageView);
        }
    }

}