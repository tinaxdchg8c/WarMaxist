package com.uwo.tools.aibum.local.progressbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.github.glomadrian.velocimeterlibrary.VelocimeterView;
import com.uwo.tools.aibum.R;

public class VelocimeterActivity extends AppCompatActivity {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, VelocimeterActivity.class);
        activity.startActivity(intent);
    }

    private SeekBar seek;
    private VelocimeterView velocimeter;
    private VelocimeterView velocimeter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocimeter_main);
        seek = (SeekBar) findViewById(R.id.seek);
        seek.setOnSeekBarChangeListener(new SeekListener());
        velocimeter = (VelocimeterView) findViewById(R.id.velocimeter);
        velocimeter2 = (VelocimeterView) findViewById(R.id.velocimeter2);
    }

    private class SeekListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            velocimeter.setValue(progress, true);
            velocimeter2.setValue(progress, true);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //Empty
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //Empty
        }
    }
}
