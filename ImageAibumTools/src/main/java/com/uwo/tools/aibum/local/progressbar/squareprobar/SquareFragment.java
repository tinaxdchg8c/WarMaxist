package com.uwo.tools.aibum.local.progressbar.squareprobar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.uwo.tools.aibum.R;

import ch.halcyon.squareprogressbar.SquareProgressBar;

public class SquareFragment extends Fragment {
    public SquareProgressBar squareProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.square_layout, container, false);
        squareProgressBar = (SquareProgressBar) view.findViewById(R.id.subi2);
        squareProgressBar.setImage(R.drawable.blenheim_palace);
        squareProgressBar.setColor("#C9C9C9");
        squareProgressBar.setProgress(32);
        squareProgressBar.setWidth(8);

        final TextView progressView = (TextView) view
                .findViewById(R.id.progressDisplay);
        progressView.setText("32%");

        SeekBar progressSeekBar = (SeekBar) view
                .findViewById(R.id.progressSeekBar);
        progressSeekBar
                .setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // nothing to do
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // nothing to do
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        squareProgressBar.setProgress(progress);
                        progressView.setText(progress + "%");
                    }
                });

        SeekBar widthSeekBar = (SeekBar) view.findViewById(R.id.widthSeekBar);
        widthSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // nothing to do
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing to do
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                squareProgressBar.setWidth(progress);
            }
        });
        return view;
    }

}
