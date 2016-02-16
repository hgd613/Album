package com.scott.demo.coordinatorlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tab;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private MyAdapter adapter;
    private List<String> str=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);

        tab = (TabLayout) findViewById(R.id.tl);
        viewPager = (ViewPager) findViewById(R.id.vp);

        str.add("菜单1");
        str.add("菜单2");
        str.add("菜单3");
        str.add("菜单4");
        str.add("菜单5");
        str.add("菜单6");
        str.add("菜单7");
        str.add("菜单8");
        str.add("菜单9");
        str.add("菜单10");

        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());
        fragments.add(OneFragment.newInstance());

        viewPager.setAdapter(adapter = new MyAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
        tab.setTabsFromPagerAdapter(adapter);

        /*tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return str.get(position);
        }
    }
}
