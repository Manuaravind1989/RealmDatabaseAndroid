package com.mobiledev.realmdatabasesample.db;

import android.content.Context;

import com.mobiledev.realmdatabasesample.model.UserInfo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Manu on 11/6/2017.
 */

public class RealmHelper {

    public static final String DB_NAME = "myDatabase.realm";
    private Realm mRealm;

    public RealmHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }
   /**
     * add Data
     */
    public void addUserInfo(final UserInfo userInfo) {
//        mRealm.beginTransaction();
        mRealm.copyToRealm(userInfo);
        mRealm.commitTransaction();

    }

    /**
     * query getWholeData
     */
    public List<UserInfo> queryAllUsers(){
        RealmResults<UserInfo> realmResults = mRealm.where(UserInfo.class).findAll();
        realmResults=realmResults.sort("id");
        return mRealm.copyFromRealm(realmResults);
    }

    /**
     * query userinfo by id
     */
    public UserInfo queryUserInfoById(int id) {
        UserInfo userInfo = mRealm.where(UserInfo.class).equalTo("id", id).findFirst();
        return userInfo;
    }

    /**
     * query userinfo by date of birth
     */
    public List<UserInfo> queryDobByAge(int age) {
        RealmResults<UserInfo> userInfos = mRealm.where(UserInfo.class).equalTo("age", age).findAll();
        return mRealm.copyFromRealm(userInfos);
    }

    public boolean isUserInfoExist(String id){
        UserInfo userInfo = mRealm.where(UserInfo.class).equalTo("id",id).findFirst();
        if (userInfo==null){
            return false;
        }else {
            return  true;
        }
    }


    /**
     * delete userinfo by Id
     */
    public void deleteUserInfo(int id) {
        UserInfo userInfo = mRealm.where(UserInfo.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        userInfo.deleteFromRealm();
        mRealm.commitTransaction();

    }

    /**
     * update UserInfo by ID
     */
    public void updateUserInfo(int id, UserInfo userInfoModel) {
        UserInfo userInfo = mRealm.where(UserInfo.class).equalTo("id", id).findFirst();
        mRealm.beginTransaction();
        userInfo.setName(userInfoModel.getName());
        userInfo.setAge(userInfoModel.getAge());
        userInfo.setPhone(userInfoModel.getPhone());
        userInfo.setPlace(userInfoModel.getPlace());
        userInfo.setEmail(userInfoModel.getEmail());
        mRealm.commitTransaction();
    }


    public Realm getRealm(){
        return mRealm;
    }

    public void close(){
        if (mRealm!=null){
            mRealm.close();
        }
    }
    //Refresh the realm istance
//    public void refresh() {
//        mRealm.refresh();
//    }

    //clear all objects from Book.class
//    public void clearAll() {
//
//        mRealm.beginTransaction();
//        mRealm.cl(UserInfo.class);
//        mRealm.commitTransaction();
//    }


}
