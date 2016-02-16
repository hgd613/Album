package com.scott.demo.album;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huogd on 2015/08/13.
 */
public class ScanImages {

    private Context context;
    private Handler handler;
    private List<ImageBean> imagesPath = new ArrayList<ImageBean>();

    public ScanImages(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public List<ImageBean> getImages() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Cursor cursor = context.getContentResolver().query(
                        uri,
                        null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[] {"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED + " DESC");
                if (cursor == null) {
                    return;
                }
                while (cursor.moveToNext()) {
                    String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                    ImageBean imageBean = new ImageBean();
                    imageBean.setImagePath(imagePath);
                    imageBean.setIsSelected(false);

                    imagesPath.add(imageBean);
                }
                cursor.close();
                handler.sendEmptyMessage(200);
            }
        }).start();
        return imagesPath;
    }
}
