package com.scott.demo.translateactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

public class TranslateActivity extends BaseActivity {

    private TextView tv_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        tv_jump = (TextView) findViewById(R.id.tv_jump);
        tv_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TranslateActivity.this, TargetActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_from_left);
            }
        });
    }
}
