package com.uwo.tools.aibum.cropper2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.isseiaoki.simplecropview.CropImageView;
import com.uwo.tools.aibum.MyApplication;
import com.uwo.tools.aibum.R;
import com.uwo.tools.aibum.local.basic.BasicActivity;

/**
 * Created by SRain on 2016/1/4.
 * <p/>
 * 图片处理类
 * https://github.com/IsseiAoki/SimpleCropView
 */
public class SimpleCropperActivity extends BasicActivity {

    // Views ///////////////////////////////////////////////////////////////////////////////////////
    private CropImageView mCropView;
    private RelativeLayout mRootLayout;

    // Image file index(1 ~ 5)
    private int mImageIndex = 5;

    // Bundle key for Save/Restore state ///////////////////////////////////////////////////////////
    private static final String KEY_IMG_INDEX = "img_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropper);
    }

    @Override
    protected void initView() {
        // bind Views
        findViews();
        // apply custom font
        FontUtils.setFont(mRootLayout);
        // set bitmap to CropImageView
        mCropView.setImageBitmap(getImageForIndex(mImageIndex));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_IMG_INDEX, mImageIndex);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mImageIndex = savedInstanceState.getInt(KEY_IMG_INDEX);
    }

    // Handle button event /////////////////////////////////////////////////////////////////////////

    private final View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.buttonDone:
                    // Get cropped bitmap and pass it to Application
                    ((MyApplication) getApplication()).cropped = mCropView.getCroppedBitmap();
                    // Start ResultActivity
                    Intent intent = new Intent(SimpleCropperActivity.this, ResultActivity.class);
                    startActivity(intent);
                    break;
                case R.id.buttonFitImage:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_FIT_IMAGE);
                    break;
                case R.id.button1_1:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);
                    break;
                case R.id.button3_4:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_3_4);
                    break;
                case R.id.button4_3:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                    break;
                case R.id.button9_16:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_9_16);
                    break;
                case R.id.button16_9:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_16_9);
                    break;
                case R.id.buttonCustom:
                    mCropView.setCustomRatio(7, 5);
                    break;
                case R.id.buttonFree:
                    mCropView.setCropMode(CropImageView.CropMode.RATIO_FREE);
                    break;
                case R.id.buttonCircle:
                    mCropView.setCropMode(CropImageView.CropMode.CIRCLE);
                    break;
                case R.id.buttonChangeImage:
                    incrementImageIndex();
                    mCropView.setImageBitmap(getImageForIndex(mImageIndex));
                    break;
                case R.id.buttonRotateImage:
                    mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
                    break;
            }
        }
    };

    // Bind views //////////////////////////////////////////////////////////////////////////////////
    private void findViews() {
        mCropView = (CropImageView) findViewById(R.id.cropImageView);
        findViewById(R.id.buttonDone).setOnClickListener(btnListener);
        findViewById(R.id.buttonFitImage).setOnClickListener(btnListener);
        findViewById(R.id.button1_1).setOnClickListener(btnListener);
        findViewById(R.id.button3_4).setOnClickListener(btnListener);
        findViewById(R.id.button4_3).setOnClickListener(btnListener);
        findViewById(R.id.button9_16).setOnClickListener(btnListener);
        findViewById(R.id.button16_9).setOnClickListener(btnListener);
        findViewById(R.id.buttonFree).setOnClickListener(btnListener);
        findViewById(R.id.buttonChangeImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonRotateImage).setOnClickListener(btnListener);
        findViewById(R.id.buttonCustom).setOnClickListener(btnListener);
        findViewById(R.id.buttonCircle).setOnClickListener(btnListener);
        mRootLayout = (RelativeLayout) findViewById(R.id.layout_root);
    }

    // Switch image files //////////////////////////////////////////////////////////////////////////
    private void incrementImageIndex() {
        mImageIndex++;
        if (mImageIndex > 5) mImageIndex -= 5;
    }

    public Bitmap getImageForIndex(int index) {
        String fileName = "sample" + index;
        int resId = getResources().getIdentifier(fileName, "mipmap", getPackageName());
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    public static void actionStart(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SimpleCropperActivity.class);
        activity.startActivity(intent);
    }
}
