package com.scott.demo.album;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;

import com.scott.demo.utils.BitmapUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huogd on 2015/08/14.
 */
public class ImageLoader {

    private LruCache<String, Bitmap> mMermoryCachre;
    private ExecutorService mEService;
    private static ImageLoader mLoader;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private ImageLoader() {
        int maxSize = (int) Runtime.getRuntime().maxMemory() / 4;
        mMermoryCachre = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
        getThreadPool();
    };

    public static ImageLoader getInstance() {
        synchronized (ImageLoader.class) {
            if (mLoader == null) {
                mLoader = new ImageLoader();
            }
            return mLoader;
        }
    }

    private void getThreadPool() {
        mEService = Executors.newFixedThreadPool(2);
    }

    @SuppressLint("HandlerLeak")
    public Bitmap loadImage(final String path, final OnCallBackListener listener) {
        Bitmap bitmap = getFormCache(path);
        final Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap) msg.obj;
                if (listener != null) {
                    listener.setBitmap(bitmap, path);
                }
                super.handleMessage(msg);
            }
        };
        if (bitmap == null) {
            mEService.execute(new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapUtil.getThumbImage(path);
                    Message msg = new Message();
                    msg.obj = bitmap;
                    handle.sendMessage(msg);
                    addtoCache(path, bitmap);
                }
            });
            return null;
        } else {
            return bitmap;
        }
    }

    @SuppressLint("NewApi")
    private Bitmap getFormCache(String path) {
        return mMermoryCachre.get(path);
    }

    public void cancleTask() {
        if (mEService != null) {
            mEService.shutdown();
        }
    }

    @SuppressLint("NewApi")
    private void addtoCache(String path, Bitmap bitmap) {
        if (bitmap != null) {
            mMermoryCachre.put(path, bitmap);
        }
    }

    public interface OnCallBackListener {
        void setBitmap(Bitmap bitmap, String url);
    }
}

