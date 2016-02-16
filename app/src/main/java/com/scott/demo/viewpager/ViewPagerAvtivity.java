package com.scott.demo.viewpager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huogd on 2015/8/11.
 */
public class ViewPagerAvtivity extends BaseActivity {

    private final String TAG = "ViewPagerAvtivity";
    private int[] images = {R.mipmap.banner3, R.mipmap.banner0, R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner0};
    private List<ImageView> imageViews;
    private List<View> dots;
    private ViewPager vp_viewpager;

    private int currentItem = 1;

    private MyHanlder myHanlder;

    private LinearLayout ll_dot_group;

    private Timer timer;

    /**
     * 是否是手动
     */
    private boolean isManual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        imageViews = new ArrayList<ImageView>();
        dots = new ArrayList<View>();

        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        tv_Title.setText("ViewPager");
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vp_viewpager = (ViewPager) findViewById(R.id.vp_viewpager);

        vp_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 5) {
                    currentItem = 1;
                } else if (position == 0) {
                    currentItem = 4;
                } else {
                    currentItem = position;
                }

                setDotBg(currentItem - 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (vp_viewpager.getCurrentItem() == 5 || vp_viewpager.getCurrentItem() == 0) {
                        Log.d(TAG, "onPageScrollStateChanged 当前页面currentItem：" + currentItem);
                        vp_viewpager.setCurrentItem(currentItem, false);
                    }
                }
            }
        });

        ll_dot_group = (LinearLayout) findViewById(R.id.ll_dot_group);
    }

    private void initData() {
        for(int i=0;i<images.length;i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViews.add(imageView);
        }

        for (int i=0;i<images.length-2;i++) {
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            if (i==0) {
                view.setBackgroundResource(R.drawable.circle_view_white);
            } else {
                view.setBackgroundResource(R.drawable.circle_view_gray);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(10, 10));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;

            dots.add(view);
            ll_dot_group.addView(view, layoutParams);
        }
        vp_viewpager.setAdapter(new MyPageAdapter());
        vp_viewpager.setCurrentItem(currentItem);

        myHanlder = new MyHanlder();
        timer = new Timer();
        timer.scheduleAtFixedRate(myTimerTask, 3000, 3000);
    }

    private TimerTask myTimerTask = new TimerTask(){
        @Override
        public void run() {
            if (!isManual) { // 如果不是手动在滑动，则进行自动滑动
                myHanlder.sendEmptyMessage(0);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //isManual = true; // 手动滑动
                //break;
            case MotionEvent.ACTION_MOVE:
                isManual = true;// 手动滑动
                myTimerTask.cancel();
                break;
            case MotionEvent.ACTION_UP:
                isManual = false;// 手动停止
                myTimerTask.run();
                break;
        }

        return super.onTouchEvent(event);
    }

    private void setDotBg(int selectedItem) {
        for (int i=0; i<dots.size(); i++) {
            if (i==selectedItem) {
                dots.get(i).setBackgroundResource(R.drawable.circle_view_white);
            } else {
                dots.get(i).setBackgroundResource(R.drawable.circle_view_gray);
            }
        }
    }
    private class MyHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentItem++;
            Log.d(TAG, "handleMessage 当前页面currentItem：" + currentItem);
            vp_viewpager.setCurrentItem(currentItem, true);
        }
    }

    private class MyPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }
}
