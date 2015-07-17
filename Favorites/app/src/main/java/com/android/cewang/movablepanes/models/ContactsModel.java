package com.android.cewang.movablepanes.models;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.android.cewang.movablepanes.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;

/**
 * Wrapper class forContactInfo objects which are used to represent
 * contact on favorites grid with utility methods for ordering contacts,
 * saving and loading order list and showing hint while dragging.
 */
public class ContactsModel {

    /* List of favorite contacts in no particular order */
    private ArrayList<ContactInfo> mModel = new ArrayList<ContactInfo>();

    /*Contact favorite order, same set of contacts as the model*/
    private ArrayList<Long> mContactsIdOrder = new ArrayList<Long>();

    private SharedPreferences mPrefs;

    public ContactsModel(Context context){
        /*loading contact order*/
        mPrefs = context.getSharedPreferences(Constants.PREFERENCE_NAME, context.MODE_PRIVATE);
        String order = mPrefs.getString(Constants.FAVIROTES_ORDER_KEY, null);

        if (order != null){
            final String favOrderArray[] = order.split(",");
            for (int i= 0; i < favOrderArray.length; i++){
                try{
                    long contactId = Long.parseLong(favOrderArray[i]);
                    if (!mContactsIdOrder.contains(contactId))
                        mContactsIdOrder.add(contactId);
                }
                catch (NumberFormatException ex){

                }
            }
        }
    }

    public void update(Cursor cursor){
        clear();

        ArrayList<Long> arrayList = new ArrayList<Long>();

        if (cursor != null){
            while (cursor.moveToNext()){
                long contactId = cursor.getLong(Constants.CONTACT_ID_COLUMN_INDEX);
                String name = cursor.getString(Constants.CONTACT_NAME_COLUMN_INDEX);
                String photoid = cursor
                        .getString(Constants.CONTACT_PHOTO_ID_COLUMN_INDEX);
                boolean isInVisibleGroup =
                        cursor.getInt(Constants.
                                CONTACT_IN_VISIBLE_GROUP_COLUMN_INDEX) == 1;
                String contactStatusResPkg = cursor
                        .getString(Constants.CONTACT_STATUS_RES_PACKAGE_COLUMN_INDEX);
                int contactPresence = cursor
                        .getInt(Constants.CONTACT_PRESENCE_COLUMN_INDEX);

                if (isInVisibleGroup) {
                    arrayList.add(contactId);

                    /*
                     * contactsIdOrder are loaded from shared preferences and to
                     * keep integrity duplicates are removed.
                     */
                    if (!mContactsIdOrder.contains(contactId)) {
                        mContactsIdOrder.add(contactId);
                    }

                    mModel.add(new ContactInfo(contactId, name, photoid, contactStatusResPkg,
                            contactPresence));
                }
            }
        }

        /*
         * remove contacts that are no longer favorites from the
         * contactIdsOrder.
         */
        if (mContactsIdOrder.size() != mModel.size()) {
            for (int i = 0; i < mContactsIdOrder.size(); ++i) {
                if (!arrayList.contains(mContactsIdOrder.get(i))) {
                    mContactsIdOrder.remove(i);
                    --i;
                }
            }
        }

    }

    /**
     *
     * Creates the String of contact ids separated by , and stores it in
     * SharedPreferences for later retrieval.
     *
     */
    public void saveOrder() {
        String order = Arrays.toString(mContactsIdOrder.toArray());
        order = order.replaceAll("\\[|\\]| ", "");
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Constants.FAVIROTES_ORDER_KEY, order);
        editor.apply();
    }

    public boolean moveContactToLocation(long contactId, int newLocation){
        int current = mContactsIdOrder.indexOf(contactId);

        if (current == -1){
            newLocation = Math.min(newLocation, mContactsIdOrder.size());
            mContactsIdOrder.add(newLocation, contactId);
            return true;
        }else {
            newLocation = Math.min(newLocation, mContactsIdOrder.size() -1);
            if (current != newLocation) {
                changeOrder(current, newLocation);
                return  true;
            }
        }
        return false;
    }

    private void changeOrder(int source, int destination){
        long contactId = mContactsIdOrder.remove(source);
        mContactsIdOrder.add(destination, contactId);
    }

    public ContactInfo get(int location) {
        long contactId = mContactsIdOrder.get(location);
        return findContact(contactId);
    }

    private ContactInfo findContact(long contactId) {
        for (int i = 0; i < mModel.size(); ++i) {
            if (mModel.get(i).getContactId() == contactId) {
                return mModel.get(i);
            }
        }
        return null;
    }

    public int getContactLocation(long contactId) {
        return mContactsIdOrder.indexOf(contactId);
    }

    public ContactInfo getContact(long contactId) {
        return findContact(contactId);
    }

    public boolean isEmpty() {
        return mModel.isEmpty();
    }

    public int size() {
        return mModel.size();
    }

    public void clear() {
        mModel.clear();
    }

    public void remove(long contactId) {
        ContactInfo info = findContact(contactId);

        if (info != null) {
            mModel.remove(info);
            mContactsIdOrder.remove((Long) contactId);
        }
    }
}
