package com.scott.demo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * 压缩图片
 *
 * @author:huoguodong
 * @date：2015-5-8 下午3:52:37
 */
public class BitmapUtil {

    private final static int size = 200;

    public static Bitmap getThumbImage(String path) {
        Options option = new Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, option);
        option.inSampleSize = getScale(option);
        option.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, option);
    }

    private static int getScale(Options options) {
        int inSampleSize = 1;

        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        if (bitmapWidth > size || bitmapHeight > size) {
            while (true) {
                if (bitmapWidth / 2 < size && bitmapHeight / 2 < size)
                    break;
                bitmapWidth = bitmapWidth / 2;
                bitmapHeight = bitmapHeight / 2;
                inSampleSize = inSampleSize * 2;
            }
        }
        return inSampleSize;
    }
}
