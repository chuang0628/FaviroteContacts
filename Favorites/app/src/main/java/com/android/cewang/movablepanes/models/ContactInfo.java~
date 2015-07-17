package com.android.cewang.movablepanes.models;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by 28851482 on 7/13/15.
 */
public class ContactInfo extends Info {
    private final long mContactId;

    private final String mContactName;

    private final Uri mPhotoUri;

    private final Uri mContactUri;

    private String mContactStatusResPackage;

    private int mContactPresence;

    public ContactInfo(long contactId, String contactName, String photoUri){
        mContactId = contactId;
        mContactName = contactName;
        mPhotoUri = photoUri != null ? Uri.parse(photoUri) : null;
        mContactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);

    }

    public ContactInfo(long contactId, String contactName, String photoUri,
                       String contactStatusResPackage, int contactPresence) {
        this(contactId, contactName, photoUri);
        mContactStatusResPackage = contactStatusResPackage;
        mContactPresence = contactPresence;
    }

    public long getContactId(){
        return mContactId;
    }

    @Override
    public long getUniqueId() {
        return mContactId;
    }

    public String getContactName(){
        return mContactName;
    }

    public Uri getPhotoUri(){
        return mPhotoUri;
    }

    @Override
    public Uri getUri() {
        return mContactUri;
    }

    public String getContactStatusResPackage() {
        return mContactStatusResPackage;
    }

    public int getContactPresence(){
        return mContactPresence;
    }
}
