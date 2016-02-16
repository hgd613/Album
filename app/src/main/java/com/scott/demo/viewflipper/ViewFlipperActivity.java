package com.scott.demo.viewflipper;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

import static android.view.GestureDetector.SimpleOnGestureListener;

/**
 * Created by huogd on 2015/08/10.
 */
public class ViewFlipperActivity extends BaseActivity {

    private ViewFlipper vf_flipper;
    private int[] images = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner0};

    private GestureDetector gestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        tv_Title.setText("ViewFliper");
        vf_flipper = (ViewFlipper) findViewById(R.id.vf_flipper);

        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gestureDetector = new GestureDetector(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void initData() {
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vf_flipper.addView(imageView);
        }
    }

    private class MyGestureListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.d("onFling", e1.getX() + "-" + e2.getX() + "=" + (e1.getX() - e2.getX()));
            if (e1.getX() - e2.getX() > 100) { // 从右向左滑动
                vf_flipper.setInAnimation(ViewFlipperActivity.this, R.anim.animation_right_in);
                vf_flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.animation_left_out);
                vf_flipper.showNext();
                return true;
            } else if (e1.getX() - e2.getX() < -100) { // 从左向右滑动
                vf_flipper.setInAnimation(ViewFlipperActivity.this, R.anim.animation_left_in);
                vf_flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.animation_right_out);
                vf_flipper.showPrevious();
                return true;
            }
            return false;
        }
    }
}
