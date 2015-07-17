package com.android.cewang.movablepanes.utils;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by 28851482 on 7/10/15.
 */
public class RectPool {
    private static ArrayList<Rect> mRectPool = new ArrayList<Rect>();

    public static Rect obtainRect(){
        if (mRectPool.isEmpty()){
            return new Rect();
        }
        return mRectPool.remove(mRectPool.size() -1);
    }

    public static void recycleRect(Rect rect){
        if (rect != null){
            rect.setEmpty();
            mRectPool.add(rect);
        }
    }
}
