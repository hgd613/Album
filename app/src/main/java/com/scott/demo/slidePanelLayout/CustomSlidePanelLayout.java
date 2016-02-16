package com.scott.demo.slidePanelLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huogd on 2015/8/10.
 */
public class CustomSlidePanelLayout extends SlidingPaneLayout {
    protected View mMenuPanel;
    protected float mSlideOffset;
    protected boolean isCustomable = false;

    public CustomSlidePanelLayout(Context context) {
        this(context, null);
    }

    public CustomSlidePanelLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomSlidePanelLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            isCustomable = true;
            setPanelSlideListener(new SimplePanelSlideListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onPanelSlide(View panel, float slideOffset) {
                    mSlideOffset = slideOffset;
                    if (mMenuPanel == null) {
                        final int childCount = getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            final View child = getChildAt(i);
                            if (child != panel) {
                                mMenuPanel = child;
                                break;
                            }
                        }
                    }
                    final float scaleLeft = 1 - 0.3f * (1 - slideOffset);
                    mMenuPanel.setPivotX(-0.3f * mMenuPanel.getWidth());
                    mMenuPanel.setPivotY(mMenuPanel.getHeight() / 2f);
                    mMenuPanel.setScaleX(scaleLeft);
                    mMenuPanel.setScaleY(scaleLeft);

                    final float scale = 1 - 0.2f * slideOffset;
                    panel.setPivotX(0);
                    panel.setPivotY(panel.getHeight() / 2.0f);
                    panel.setScaleX(scale);
                    panel.setScaleY(scale);
                }
            });
            setSliderFadeColor(0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isCustomable) {
            dimOnForeground(canvas);
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result = super.drawChild(canvas, child, drawingTime);
        if (isCustomable && child == mMenuPanel) {
            dimOnForeground(canvas);
        }
        return result;
    }

    /**
     * dim the view
     *
     * @param canvas
     */
    private void dimOnForeground(Canvas canvas) {
        canvas.drawColor(Color.argb((int) (0xff * (1 - mSlideOffset)), 0, 0, 0));
    }
}
