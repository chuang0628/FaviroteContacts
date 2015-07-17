package com.android.cewang.movablepanes.paneview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;

import com.android.cewang.favorites.R;
import com.android.cewang.movablepanes.utils.PaneLocation;

import java.util.HashMap;

/**
 * TODO: document your custom view class.
 */
public class PaneView extends AdapterView<PaneAdapter> {

    private PaneAdapter mPaneAdapter;

    /*
     * List of items from the adapter. May not be up to date. Do not access
     * directly, use mItems
     */
    private HashMap<Long, AdapterItem> mItems1 = new HashMap<Long, AdapterItem>();

    /*
     * List of items from the adapter. May not be up to date. Do not access
     * directly, use mItems
     */
    private HashMap<Long, AdapterItem> mItems2 = new HashMap<Long, AdapterItem>();

    /* The current items of the pane view */
    private HashMap<Long, AdapterItem> mItems = mItems1;

    /**
     * This represents an item in the pane view. It has a view that is displayed
     * at the specified location.
     */
    protected static class Item{
        View view;
        PaneLocation location;
    }

    /**
     * This is an item that comes from the adaptor. It stores, in addition, the
     * id and position for the item and whether the view is valid or need to be
     * refreshed from the adapter.
     */
    protected static class AdapterItem extends Item{
        /** The position, in the adaptor, that corresponds to this item */
        private int position;

        /** The id, supplied by the adaptor, of this item */
        private long id;

        /** Indicates whether the view is valid or not */
        protected boolean viewValid = true;

        protected boolean animateScaleUp = false;

        protected boolean animateMove = false;

        protected Rect animateMoveFrom = new Rect();

        protected Rect animateMoveTo = new Rect();
    }


    public PaneView(Context context, AttributeSet attrs){
        super(context, attrs);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        int touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for (AdapterItem item : mItems.values()){
            if (item.view != null){
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                        item.location.drawRect.width(), MeasureSpec.EXACTLY);
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                        item.location.drawRect.height(), MeasureSpec.EXACTLY);
                item.view.measure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public PaneAdapter getAdapter() {
        return mPaneAdapter;
    }

    @Override
    public void setAdapter(PaneAdapter adapter) {
        mPaneAdapter = adapter;
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }
}
