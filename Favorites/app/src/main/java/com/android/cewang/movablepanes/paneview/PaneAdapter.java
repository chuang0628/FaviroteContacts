package com.android.cewang.movablepanes.paneview;

import android.graphics.Rect;
import android.widget.ListAdapter;

public interface PaneAdapter extends ListAdapter {

    /*
     * Used by PaneView to find out where to position items.
     *
     * @param position The position of the item whose location we want
     * @param location The location object that should be set to the wanted
     *            location of the item
     */
    public void updateLocation(int position, Rect location, int layoutWidth);

    public int getLocationIndex(int x, int y);

    /*
     * Used by PaneView to find out number of columns.
     *
     * @return The number of columns in the PaneView
     */
    public int getColumnsCount();

    /*
     * Used by PaneView to find out cell size.
     *
     * @return The size of the cell within the PaneView
     */
    public int getCellSize();

    /*
     * Returns true if this info represents an interactive object that
     * wants to handle touch and key interaction on its own.
     *
     * @param position The position of the item
     * @return true if this object is interactive, false otherwise
     */
    public boolean isInteractive(int position);
}
