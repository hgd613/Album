package com.scott.demo.platformparam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;

public class PlatformparamActivity extends BaseActivity {

    private TextView text;
    private Button click;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platformparam);

        initView();

        text = (TextView) findViewById(R.id.tv_display);
        click= (Button) findViewById(R.id.click);

        Display dispaly = getWindowManager().getDefaultDisplay();
        Point outSize = new Point();
        dispaly.getSize(outSize);

        //outSize.set(10, 20);

        int width = outSize.x;
        int height = outSize.y;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density; // where one DIP is one pixel on an approximately 160 dpi screen
        int densityDpi = metrics.densityDpi; // The screen density expressed as dots-per-inch
        int widthPixels = metrics.widthPixels; //The absolute width of the display in pixels.
        int heightPixels = metrics.heightPixels;
        float xdpi = metrics.xdpi;// The exact physical pixels per inch of the screen in the X dimension.
        float ydpi = metrics.ydpi;
        String string =
                "  屏幕宽："+width+" pixels \n\t"
                        +"屏幕高："+height+" pixels \n\t"+"\n\t"

                        +"屏幕density->Thus on a 160dpi screen this density value will be 1 ："+density+"\n\t"
                        +"one DIP is one pixel on an approximately 160 dpi screen "+"\n\t"
                        +"屏幕densityDpi(dots-per-inch)->The screen density expressed as dots-per-inch ："+densityDpi+"\n\t"+"\n\t"

                        +"屏幕widthPixels->The absolute width of the display in pixels："+widthPixels+"\n\t"
                        +"屏幕heightPixels->："+heightPixels+"\n\t"+"\n\t"

                        +"屏幕xdpi->The exact physical pixels per inch of the screen in the X dimension.："+xdpi+"\n\t"
                        +"屏幕ydpi："+ydpi+"\n\t"+"\n\t"

                        +"Density-independent pixel (dip，或dp),独立像素，虚拟单位，又称设备无关像素。1dp的长度相当于一个160dpi的屏幕上一个物理像素的长度。而160dpi的屏幕则是被android定义为基准的屏幕（mdpi）。在app运行的时候，android会将dp转为实际像素进行布局。转换的公式为：px = dp * (dpi / 160)。";

        text.setText(string);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlatformparamActivity.this, ImageSizeActivity.class));
            }
        });
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

        tv_Title.setText("Platformparam");
    }
}
