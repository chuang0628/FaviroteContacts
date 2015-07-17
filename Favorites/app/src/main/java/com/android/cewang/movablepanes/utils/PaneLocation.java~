package com.android.cewang.movablepanes.utils;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by 28851482 on 7/9/15.
 */
public class PaneLocation implements Recyclable {
    /** List of recycled PaneLocations */
    private static ArrayList<PaneLocation> sRecycledLocations = new ArrayList<PaneLocation>();

    public Rect drawRect;

    public PaneLocation obtain(){
        if (sRecycledLocations.isEmpty()){
            return new PaneLocation();
        }

        return sRecycledLocations.remove(sRecycledLocations.size() -1);
    }

    private PaneLocation(){
        drawRect = new Rect();
    }

    public void set(PaneLocation source){
        drawRect.set(source.drawRect);
    }

    @Override
    public void recycle() {
        drawRect.setEmpty();
        sRecycledLocations.add(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaneLocation other = (PaneLocation)obj;
        if (drawRect == null) {
            if (other.drawRect != null)
                return false;
        } else if (!drawRect.equals(other.drawRect))
            return false;
        return true;
    }

    public int hashCode(){
        throw new UnsupportedOperationException();
    }
}
