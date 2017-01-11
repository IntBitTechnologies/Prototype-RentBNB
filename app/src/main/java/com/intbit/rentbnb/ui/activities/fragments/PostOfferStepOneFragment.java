package com.intbit.rentbnb.ui.activities.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.intbit.rentbnb.R;
import com.intbit.rentbnb.adapters.ThumbnailImageRecyclerViewAdapter;
import com.intbit.rentbnb.base.ApplicationConstants;
import com.intbit.rentbnb.base.RentbnbBaseFragment;
import com.intbit.rentbnb.models.Thumbnail;
import com.intbit.rentbnb.support.ImageUtil;
import com.intbit.rentbnb.support.Preferences;
import com.intbit.rentbnb.support.RentBnbOnFragmentSelectedListener;
import com.intbit.rentbnb.support.RecyclerItemClickListener;
import com.intbit.rentbnb.ui.activities.PostOfferActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adiba on 14/11/2016.
 */
public class PostOfferStepOneFragment extends RentbnbBaseFragment implements RentBnbOnFragmentSelectedListener {
    private Button step2, takePhotoButton, selectPhotoButton;
    private Activity mContext;
    private RecyclerView photosRecyclerView;
    ThumbnailImageRecyclerViewAdapter thumbnailImageRecyclerViewAdapter;

    private int SELECT_FILE = ApplicationConstants.OPEN_GALLERY,
            REQUEST_CAMERA = ApplicationConstants.OPEN_CAMERA,
            PERMISSION_STORAGE_READ = ApplicationConstants.PERMISSION_REQUEST_STORAGE_READ,
            PERMISSION_STORAGE_WRITE = ApplicationConstants.PERMISSION_REQUEST_STORAGE_WRITE;
    boolean isCameraButton = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_post_offer_step_1, container, false);

        step2 = (Button) view.findViewById(R.id.tab_post_offer_step_1_next_button);
        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setCurrentPage(2);
                ((PostOfferActivity) getActivity()).changefragmentalongStepProcess(2);
            }
        });

        photosRecyclerView = (RecyclerView) view.findViewById(R.id.tab_post_offer_step1_thumbnailRecyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        photosRecyclerView.setLayoutManager(mLayoutManager);

        List<Thumbnail> thumbnailList = new ArrayList<>();
        Thumbnail thumbnail = new Thumbnail();
        thumbnailList.add(thumbnail);

        thumbnailImageRecyclerViewAdapter = new ThumbnailImageRecyclerViewAdapter(mContext, thumbnailList);
        photosRecyclerView.setAdapter(thumbnailImageRecyclerViewAdapter);
        photosRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            //if (position == (photosRecyclerView.getAdapter().getItemCount()-1)) {
                            popup();
                        } else {
                            Thumbnail itemThumbnail = thumbnailImageRecyclerViewAdapter.getItem(position);
                            String imageUrl = itemThumbnail.getImageUrl();
                            loadBaseImageViewImage(imageUrl);
                        }
                    }
                }));

        return view;
    }

    @Override
    public void onFragmentSelected(FragmentManager fragmentManager, ViewPager viewPager, int position, Fragment fragment) {
        if (fragment instanceof PostOfferStepOneFragment) {
            //getData(RentBnbEnums.Offers_View_Grid.toInt());
        }
    }

    @Override
    public void changeViewType() {

    }

    private void loadBaseImageViewImage(String imageUrl) {
        File file = new File(imageUrl);
        Uri imageUri = Uri.fromFile(file);
        String path = ImageUtil.getPath(mContext, imageUri);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap processedBitmap = adjustImageOrientation(bitmap, path);
        //productPhotoImageView.setImageBitmap(processedBitmap);
    }

    private void popup() {
        final CharSequence[] items = {"Take Photo", "Choose from Library"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AlertDialogCSS);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    if (checkCameraPermission()) {
                        openCamera();
                    }
                } else if (items[item].equals("Choose from Library")) {
                    if (checkReadStorage()) {
                        openGallery();
                    }
                }
            }
        });
        builder.show();
    }

    private boolean checkCameraPermission() {
        boolean isPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(mContext, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
        return isPermissionGranted;
    }

    private boolean checkReadStorage() {
        boolean isPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_STORAGE_READ);
        }
        return isPermissionGranted;
    }

    private boolean checkWriteStorage() {
        boolean isPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_STORAGE_WRITE);
        }
        return isPermissionGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE_READ) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isCameraButton) {
                    takePhotoButton.performClick();
                } else {
                    selectPhotoButton.performClick();
                }
            }
        }

        if (requestCode == PERMISSION_STORAGE_WRITE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isCameraButton) {
                    takePhotoButton.performClick();
                } else {
                    selectPhotoButton.performClick();
                }
            }
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String unique_id = String.valueOf(System.currentTimeMillis());
                File destination = new File(Environment.getExternalStorageDirectory(), unique_id + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri imageUri = Uri.fromFile(destination);
                String path = ImageUtil.getPath(mContext, imageUri);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Bitmap processedBitmap = adjustImageOrientation(bitmap, path);
                //productPhotoImageView.setImageBitmap(processedBitmap);
                uploadPhotoToRecyclerView(path);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(mContext, selectedImageUri, projection, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 300;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                //productPhotoImageView.setImageBitmap(bm);
            }
        }
    }

    private void uploadPhotoToRecyclerView(String imageUri) {
        int itemCount = photosRecyclerView.getAdapter().getItemCount();
        if (itemCount > 6) {
            //Toast.makeText(PostOfferStepThreeFragment.this, "You can upload only 6 photos maximum", Toast.LENGTH_SHORT).show();
        } else {
            thumbnailImageRecyclerViewAdapter.addThumbnail(imageUri.toString());
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
}