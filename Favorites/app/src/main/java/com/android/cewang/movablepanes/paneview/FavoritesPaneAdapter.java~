package com.android.cewang.movablepanes.paneview;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.cewang.favorites.R;
import com.android.cewang.movablepanes.models.ContactInfo;
import com.android.cewang.movablepanes.models.ContactsModel;

import java.io.InputStream;
import java.util.zip.Inflater;

/**
 * Created by 28851482 on 7/13/15.
 */
public final class FavoritesPaneAdapter extends BaseAdapter implements PaneAdapter {
    private Context mContext;
    private ContactsModel mContactsModel;
    private View mPaneView;
    private LayoutInflater mInflater;

    private int mColumns;
    private int mCellSize;
    private int mBadgeSize;
    private int mTransferContactId;

    private BidiFormatter mBidiFormatter;

    private static class ViewHolder{

        public final ImageView photoView;

        public final QuickContactBadge quickContactBadge;

        public final TextView nameView;

        public final ImageView rcsIconView;

        public final View view;

        public RelativeLayout gradientLayout;

        public ViewHolder(View v) {
            this.view = v;
            this.photoView = (ImageView) v.findViewById(R.id.imageview_favoritePhoto);
            this.rcsIconView = (ImageView) v.findViewById(R.id.rcs_status_icon);
            this.quickContactBadge = (QuickContactBadge) v.findViewById(R.id.quickcontactbadge);
            this.nameView = (TextView) v.findViewById(R.id.list_item_name);
            this.gradientLayout = (RelativeLayout) v.findViewById(R.id.gradientLayout);
        }
    }

    public FavoritesPaneAdapter(Context context, ContactsModel contactsModel,
                                View paneView, LayoutInflater inflater,
                                int badgeSize, int transferContactId){
        mContext = context;
        mContactsModel = contactsModel;
        mPaneView = paneView;
        mInflater = inflater;
        mBadgeSize = badgeSize;
        mTransferContactId = transferContactId;
        mBidiFormatter = BidiFormatter.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v;
        final ViewHolder holder;
        if (convertView == null){
            v = mInflater.inflate(R.layout.favirotes_photo_and_name, parent, false);
            holder = new ViewHolder(v);
            v.setTag(R.id.tag_favorite_contact_view, holder);
        }else {
            v = convertView;
            holder = (ViewHolder)v.getTag(R.id.tag_favorite_contact_view);
        }

        bindPaneView(position, holder);
        return v;
    }

    protected void bindPaneView(final int position, ViewHolder view){
        ImageView photoView = view.photoView;
        /*maybe do not need this view*/
        ImageView rcsIconView = view.rcsIconView;
        TextView nameView = view.nameView;
        RelativeLayout gradientLayout = view.gradientLayout;
        QuickContactBadge quickContactBadge = view.quickContactBadge;
        ContactInfo data = mContactsModel.get(position);

        final long id = data.getContactId();
        String name = data.getContactName();
        Uri photoUri = data.getPhotoUri();
        int presence = data.getContactPresence();

        /*maybe we donot need check the status res and delete this property*/
//        boolean isRcsContact = TextUtils.equals(Constants.RCS_STATUS_PACKAGE,
//                data.getContactStatusResPackage()) && (ContactsContract.StatusUpdates.AVAILABLE == presence);

        if (data.getContactId() == mTransferContactId) {
            gradientLayout.setVisibility(View.INVISIBLE);
            photoView.setImageDrawable(mContext.getResources().getDrawable(
                    R.drawable.favorites_hint_item));
            return;
        } else {
            gradientLayout.setVisibility(View.VISIBLE);
            view.view.bringToFront();
        }

        if (name != null && name.length() != 0) {
            name = mBidiFormatter.unicodeWrap(name);
            nameView.setText(name);
            photoView.setContentDescription(name);
        } else {
            final CharSequence unknownNameText = mContext
                    .getText(R.string.spb_strings_contacts_list_no_name_txt);
            nameView.setText(unknownNameText);
            photoView.setContentDescription(unknownNameText);
        }

        /*maybe donot need this view*/
//        if (isRcsContact) {
//            rcsIconView.setImageBitmap(BitmapUtil.getRCSeCapableIcon(ContactsContract.StatusUpdates.AVAILABLE,
//                    mContext, Constants.RCS_STATUS_PACKAGE));
//            rcsIconView.setVisibility(View.VISIBLE);
//        } else {
//            rcsIconView.setVisibility(View.GONE);
//        }

        quickContactBadge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /*when click used to start contact activity,
                 * will write the activity myself */
                Intent intent = new Intent();
//                Intent intent = ContactsContract.QuickContact.composeQuickContactsIntent(mContext, (Rect) null,
//                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id),
//                        QuickContactActivity.MODE_FULLY_EXPANDED, null);
                mContext.startActivity(intent);
            }
        });

        loadPhoto(mContext, photoView, id);
    }

    private void loadPhoto(Context context, ImageView imageView, long contactId){
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(
                context.getContentResolver(), uri, true);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        if (bitmap != null){
            Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
            imageView.setImageDrawable(drawable);
        }else {
            /*need to created a picture used as the default photo*/
            imageView.setImageDrawable(context.getResources()
                    .getDrawable(R.drawable.abc_dialog_material_background_dark));
        }

    }

    @Override
    public void updateLocation(int position, Rect rect, int layoutWidth) {
        mColumns = calColumnCounts(layoutWidth);
        mCellSize = layoutWidth / mColumns;

        int row = position / mColumns;
        int col;
        if (mPaneView.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL){
            col = (position % mColumns - mColumns + 1) * -1;
        }else {
            col = position % mColumns;
        }

        rect.left = col * mCellSize;
        rect.top = row * mCellSize;
        rect.right = rect.left + mCellSize;
        rect.bottom = rect.top + mCellSize;
    }

    private int calColumnCounts(int layoutWidth){
        int columns = Math.max(layoutWidth/mBadgeSize, 1);

        if ((layoutWidth % mBadgeSize) > (mBadgeSize/3)) {
            columns++;
        }

        return columns;
    }

    @Override
    public int getLocationIndex(int x, int y) {

        if (mPaneView.getLayoutDirection() ==  View.LAYOUT_DIRECTION_RTL) {
            return (Math.min((x / mCellSize), mColumns - 1) - mColumns + 1) * -1
                    + mColumns * (y / mCellSize);
        } else {
            return Math.min((x / mCellSize), mColumns - 1) + mColumns * (y / mCellSize);
        }
    }

    @Override
    public int getColumnsCount() {
        return mColumns;
    }

    @Override
    public int getCellSize() {
        return mCellSize;
    }

    @Override
    public boolean isInteractive(int position) {
        return false;
    }

    @Override
    public int getCount() {
        return mContactsModel.size();
    }

    @Override
    public ContactInfo getItem(int index) {
        return mContactsModel.get(index);
    }

    @Override
    public long getItemId(int index) {
        return mContactsModel.get(index).getUniqueId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
