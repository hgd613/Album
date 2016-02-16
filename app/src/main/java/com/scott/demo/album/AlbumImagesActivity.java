package com.scott.demo.album;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.scott.demo.BaseActivity;
import com.scott.demo.R;
import com.scott.demo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huogd on 2015/8/13.
 */
public class AlbumImagesActivity extends BaseActivity implements AlbumImagesAdapter.OnCheckboxChangedListener
{

    public static final int MOST_PICTURE_COUNT = 9; // 允许用户选择的最大图片数量

    private GridView gridView;
    private AlbumImagesAdapter adapter;

    private List<ImageBean> allImagesList; // 从SD卡中读取的图片的地址
    private static ArrayList<String> selectedImageList;

    private int count; // 用户选择的图片数量
    private Bundle bundle;

    private int columWidht; // GridView的列宽, 像素值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        columWidht = (Utils.getScreenWidth(this) - Utils.dip2px(this, 6)) / 3;

        initView();
        initData();
    }

    /**
     * 初始化页面组件
     */
    @Override
    protected void initView() {
        super.initView();
        gridView = (GridView) findViewById(R.id.gv_images);

        tv_Title.setText("选择图片");
        tv_right_text.setText("完成");
        tv_right_text.setTextColor(Color.WHITE);
        tv_right_text.setBackgroundColor(Color.parseColor("#48BD23"));
        tv_right_text.setVisibility(View.VISIBLE);

        // 返回按钮点击事件
        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 完成按钮点击事件
        tv_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putStringArrayList("image_data", selectedImageList);
                intent.putExtras(bundle);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //点击gridview item事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // view.setAlpha(0.5f);
            }
        });
    }

    private void initData() {

        /** receive image count sent by forward activity */
        bundle = getIntent().getExtras();
        // count = bundle.getInt(Constants.SELECT_IMAGE_COUNT);

        /** 从SD卡扫描 */
        ScanImages scanImages = new ScanImages(getApplicationContext(), showImageHandler);
        allImagesList = scanImages.getImages();

        adapter = new AlbumImagesAdapter(this, gridView, columWidht);
        gridView.setAdapter(adapter);
        adapter.setListener(this);
        adapter.updateData(allImagesList, count);
    }

    @SuppressLint("HandlerLeak")
    public Handler showImageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if (msg.what == 200) {
                adapter.updateData(allImagesList, count);
            }
        }
    };

    @Override
    public void onCheck(ArrayList<String> selectedList) {
        selectedImageList = selectedList;
        if (count >= MOST_PICTURE_COUNT) {
            return;
        }
        tv_right_text.setText("完成(" + selectedImageList.size() + "/" + (MOST_PICTURE_COUNT - count) + ")");
    }
}

