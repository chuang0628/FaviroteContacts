package com.android.cewang.movablepanes.utils;

/**
 * Created by 28851482 on 7/9/15.
 */
public interface Recyclable {
    /**
     * Called when the object should no longer be used. After calling recycle, the object should be
     * set to null to guard against bad usage.
     */
    public void recycle();
}
