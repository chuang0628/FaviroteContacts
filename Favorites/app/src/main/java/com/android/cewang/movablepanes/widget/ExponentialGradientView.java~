package com.android.cewang.movablepanes.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.android.cewang.favorites.R;

/**
 * TODO: document your custom view class.
 */
public class ExponentialGradientView extends View {
    private final Paint mPaint = new Paint();
    private Bitmap mBitmap;

    public ExponentialGradientView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExponentialGradientView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.ExponentialGradientView);
        int color =
                typedArray.getColor(R.styleable.ExponentialGradientView_android_color, Color.BLACK);

        typedArray.recycle();

        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
    }

    /*
     * Called when the size for the view has changed.
     * We should re-draw gradient if size has changed to ensure correct proportions.
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(mBitmap);
        mPaint.setAlpha(0);

        for (int i = 0; i <= height; i++) {
            // draw line onto bitmap
            bitmapCanvas.drawLine(0, i, width, i, mPaint);

            // calculate new opacity
            float deci = ((float) i / height);
            float pow = (float) Math.pow(deci, 2);
            float alpha = pow * 255f;
            mPaint.setAlpha((int) alpha);
        }
    }

    /**
     * Called when this view is about to be drawn.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }
}
