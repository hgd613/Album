package com.scott.demo.platformparam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

public class ImageSizeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_size);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_size, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        super.initView();
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_Title.setText("图片大小与像素、平台的的关系");

        ImageView a100= (ImageView) findViewById(R.id.a100);
        ImageView a200= (ImageView) findViewById(R.id.a200);

        TextView tv_a100= (TextView) findViewById(R.id.tv_a100);
        TextView tv_a200= (TextView) findViewById(R.id.tv_a200);

        a100.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        a200.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        tv_a100.setText("getMeasuredWidth()：" + a100.getMeasuredWidth() + "；getMeasuredHeight()：" + a100.getMeasuredHeight());
        tv_a200.setText("getMeasuredWidth()：" + a200.getMeasuredWidth() + "；getMeasuredHeight()：" + a200.getMeasuredHeight());
    }
}
