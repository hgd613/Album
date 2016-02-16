package com.scott.demo.translateactivity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

import org.w3c.dom.Text;

public class TargetActivity extends BaseActivity {

    private TextView tv_right_transalte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        tv_right_transalte = (TextView) findViewById(R.id.tv_right_transalte);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d("TargetActivity", "滑动距离" + (downX - event.getX()));

                if (downX - event.getX() < -300) {
                    finish();
                    overridePendingTransition(0, R.anim.activity_out_from_right);
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
