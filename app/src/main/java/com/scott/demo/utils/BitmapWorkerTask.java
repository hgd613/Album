package com.scott.demo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * 异步加载图片
 * Created by huogd on 2015/8/13.
 */
public class BitmapWorkerTask extends AsyncTask<Integer, Integer, Bitmap>{

    private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
    private Context context;

    public BitmapWorkerTask(Context context, ImageView imageView) {
        this.context = context;
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        return Utils.decodeSampledBitmapFromResource(this.context.getResources(), data, 100, 100);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
