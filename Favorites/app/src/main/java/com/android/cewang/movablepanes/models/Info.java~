package com.android.cewang.movablepanes.models;

import android.net.Uri;

/**
 * Created by 28851482 on 7/13/15.
 */
public abstract  class Info {

    abstract public long getUniqueId();

    /**
     * Returns if this item is enabled for receiving events
     *
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns true if this info represents an interactive object that
     * wants to handle touch and key interaction on its own.
     *
     * @return true if this object is interactive, false otherwise
     */
    public boolean isInteractive() {
        return false;
    }

    /**
     * Gets the Uri associated with this item.
     *
     * @return The uri associated with this item
     */
    public abstract Uri getUri();
}
