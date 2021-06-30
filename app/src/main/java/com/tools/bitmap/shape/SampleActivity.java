package com.tools.bitmap.shape;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.tools.bitmap.R;


public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

//        getSupportActionBar().hide();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(pager);
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = SampleBubbleFragment.newInstance(R.layout.list_item_shader_bubble_left, R.layout.list_item_shader_bubble_right);
                    break;
                case 1:
                    fragment = SampleListFragment.newInstance(R.layout.list_item_shader_circle);
                    break;
                case 2:
                    fragment = SampleListFragment.newInstance(R.layout.list_item_shader_rounded);
                    break;
                case 3:
                    fragment = SampleFragment.newInstance(R.layout.fragment_all_sample);
                    break;
                case 4:
                    fragment = SampleFragment.newInstance(R.layout.fragment_shader_sample);
                    break;
                case 5:
                    fragment = SampleFragment.newInstance(R.layout.fragment_porter_sample);
                    break;
                case 6:
                default:
                    fragment = SampleFragment.newInstance(R.layout.fragment_relative_sample);
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String result;
            switch (position) {
                case 0:
                    result = "Chat Bubble(S)";
                    break;
                case 1:
                    result = "Circle(S)";
                    break;
                case 2:
                    result = "Rounded(S)";
                    break;
                case 3:
                    result = "Samples";
                    break;
                case 4:
                    result = "Shaders";
                    break;
                case 5:
                    result = "Porter";
                    break;
                case 6:
                default:
                    result = "Relative";
                    break;

            }
            return result;
        }
    }
}
