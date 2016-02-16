package com.scott.demo.slidePanelLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

/**
 * Created by huogd on 2015/8/10.
 */
public class SlideActivity extends BaseActivity{

    private Fragment menuFragment;
    private Fragment contentFragment;

    private FrameLayout menu;
    private FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        menuFragment = new MenuFragment();
        contentFragment = new ContentFragment();
    }
}
