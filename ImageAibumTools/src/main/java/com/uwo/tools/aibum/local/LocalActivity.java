package com.uwo.tools.aibum.local;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uwo.tools.aibum.R;
import com.uwo.tools.aibum.imagescan.ScanMainActivity;
import com.uwo.tools.aibum.local.basic.BasicActivity;
import com.uwo.tools.aibum.local.card.CalendarCardActivity;
import com.uwo.tools.aibum.local.dialog.DialogMainActivity;
import com.uwo.tools.aibum.local.square.SampleTimesSquareActivity;
import com.uwo.tools.aibum.utils.ActionUtils;
import com.uwo.tools.aibum.utils.DialogUtils;
import com.uwo.tools.aibum.utils.ImageUriUtils;
import com.uwo.tools.aibum.utils.StaticCode;

import java.io.File;

/**
 * Created by SRain on 2015/12/11.
 * <p/>
 * 读取本地相册(整理)
 */
public class LocalActivity extends BasicActivity implements View.OnClickListener {

    private Button btnAlbum, btnScan, btnMap, btnDialog, btnDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        TextView back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("工具类综合整理");
        btnAlbum = (Button) findViewById(R.id.btnAlbum);
        btnAlbum.setOnClickListener(this);
        btnScan = (Button) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(this);
        btnDateTime = (Button) findViewById(R.id.btnDateTime);
        btnDateTime.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.btnAlbum:
                DialogUtils.createListDialog(this, this.getResources().getString(R.string.selectLocalAlbumType), this.getResources().getStringArray(R.array.selectAlbumType), new ItemClickListener(StaticCode.SELECT_BUTTON_STATE_ALBUM));
                break;
            case R.id.btnScan:
                DialogUtils.createListDialog(this, this.getResources().getString(R.string.selectSCanType), this.getResources().getStringArray(R.array.selectSCanType), new ItemClickListener(StaticCode.SELECT_BUTTON_STATE_SCAN));
                break;
            case R.id.btnMap:
                break;
            case R.id.btnDialog:
                Intent intent = new Intent();
                intent.setClass(LocalActivity.this, DialogMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDateTime:
                DialogUtils.createListDialog(this, this.getResources().getString(R.string.selectDateType), this.getResources().getStringArray(R.array.selectDateType), new ItemClickListener(StaticCode.SELECT_BUTTON_STATE_DATE));
                break;
        }
    }

    public class ItemClickListener implements DialogInterface.OnClickListener {

        private int state;

        public ItemClickListener(int state) {
            this.state = state;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (state == StaticCode.SELECT_BUTTON_STATE_ALBUM) {
                switch (which) {
                    case 0:
                        ActionUtils.openLocalImage(LocalActivity.this);
                        break;
                    case 1:
                        Intent intent = new Intent(LocalActivity.this, ScanMainActivity.class);
                        startActivity(intent);
                        break;
                }
            } else if (state == StaticCode.SELECT_BUTTON_STATE_SCAN) {
                switch (which) {
                    case 0:
                        ActionUtils.openCameraImage(LocalActivity.this);
                        break;
                    case 1:
                        String test = "srainTest.jpg"; // 文件名
                        ActionUtils.openCameraImage(LocalActivity.this, test);
                        break;
                    case 2:
                        ActionUtils.openCameraImageThumb(LocalActivity.this);
                        break;
                    case 3:
                        ActionUtils.openCameraImageNoResult(LocalActivity.this);
                        break;
                    case 4:
                        CameraActivity.actionStart(LocalActivity.this);
                        break;
                    case 5:
                        Camera1Activity.actionStart(LocalActivity.this);
                        break;
                }
            } else if (state == StaticCode.SELECT_BUTTON_STATE_DATE) {
                switch (which) {
                    case 0:
                        DateDialogActivity.actionStart(LocalActivity.this);
                        break;
                    case 1:
                        SlideActivity.actionStart(LocalActivity.this);
                        break;
                    case 2:
                        DateTimePickerActivity.actionStart(LocalActivity.this);
                        break;
                    case 3:
                        CalendarCardActivity.actionStart(LocalActivity.this);
                        break;
                    case 4:
                        SampleTimesSquareActivity.actionStart(LocalActivity.this);
                        break;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView image = (ImageView) findViewById(R.id.image);

        /**
         * 拍照返回值处理
         */
        switch (requestCode) {
            // 调用系统相机拍照，直接返回uri
            case ActionUtils.OPEN_CAMERA_IMAGE_URI:
                if (ImageUriUtils.imageUriFromCamera != null) {
                    // 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
                    image.setImageURI(ImageUriUtils.imageUriFromCamera);
                }
                break;

            // 调用系统相机拍照,传入返回路径
            case ActionUtils.OPEN_CAMERA_IMAGE_PATH:
                // 当传入文件路径的的情况下，data返回参数是null值，
                // 只要resultCode为RESULT_OK，则上述代码中/sdcard/srainTest.jpg的图片文件就是最新的照片文件。
                // MediaStore.EXTRA_OUTPUT的方法，经过手机实测除了我们设定的路径下有照片外，
                // 在手机存储卡上也会保存一份照片，默认目录为sdcard/dcim/camera下面，
                // 我曾经尝试着想如果每次返回可以取得sdcard/dcim/camera下面的路径就好了，
                // 但是目前看来没办法直接获得，可以借助MediaStroe每次去查询最后一条照片记录，应该也是可行的。
                String filePath = Environment.getExternalStorageDirectory() + "/srainTest.jpg";
                image.setImageURI(Uri.fromFile(new File(filePath)));
                break;

            // 调用系统相机拍照,返回缩略图
            case ActionUtils.OPEN_CAMERA_IMAGE_THUMB:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
                break;
        }
    }
}