package com.qlvb.vnpt.botttt.schedule.app.realm;

import android.app.Activity;
import android.app.Application;

import com.qlvb.vnpt.botttt.schedule.domain.model.GetGroupResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GetlistUserResult;
import com.qlvb.vnpt.botttt.schedule.domain.model.GroupObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.MinisterObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UnitObject;
import com.qlvb.vnpt.botttt.schedule.domain.model.UserObject;

import androidx.fragment.app.Fragment;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by linhl on 8/28/2018.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

    //find all objects in the UnitObject.class
    public RealmResults<UnitObject> getListUnits() {

        return realm.where(UnitObject.class).findAll();
    }


    //query a single item with the given id
    public UnitObject getUnitById(String id) {
        return realm.where(UnitObject.class).equalTo("id", id).findFirst();
    }  //query a single item with the given id

    public RealmResults<UnitObject> getUnitByParent(String parentId) {
        return realm.where(UnitObject.class).equalTo("parentId", parentId).findAll();
    }

    //find all objects in the UserObject.class
    public RealmResults<UserObject> getListUsers() {

        return realm.where(UserObject.class).findAll();
    } //find all objects in the UserObject.class

    public RealmResults<GroupObject> getListGroups(String nameGroup) {

        return realm.where(GroupObject.class).contains("name", nameGroup).findAll();
    }

    //query a single item with the given id
    public RealmResults<UserObject> getUserByUnit(String unitId) {
        return realm.where(UserObject.class).equalTo("userId", unitId).findAll();
    } //query a single item with the given id

    public RealmResults<UnitObject> getUnitByName(String strnName) {
        return realm.where(UnitObject.class).contains("name", strnName, Case.INSENSITIVE).findAll();
    }

    public RealmResults<UserObject> getUserByName(String strnName) {
        return realm.where(UserObject.class).contains("name", strnName, Case.INSENSITIVE).findAll();
    }

    public RealmResults<UserObject> getUserByUnitandName(String strnName, String unit) {
        return realm.where(UserObject.class)
                .equalTo("unitId", unit, Case.INSENSITIVE)
                .and()
                .contains("name", strnName, Case.INSENSITIVE).findAll();
    }

    public RealmResults<UserObject> getUserSuggest(String userName) {
        return realm.where(UserObject.class)
                .contains("name", userName, Case.INSENSITIVE)
                .and()
                .contains("updateDate", "", Case.INSENSITIVE).findAll();
    }

    public RealmResults<UnitObject> getUnitSuggest(String unitName) {
        return realm.where(UnitObject.class)
                .contains("name", unitName, Case.INSENSITIVE)
                .and()
                .contains("updateDate", "", Case.INSENSITIVE).findAll();
    }

    public RealmResults<MinisterObject> getMinniterByName(String strnName) {
        return realm.where(MinisterObject.class)
                .contains("fullName", strnName, Case.INSENSITIVE).findAll();
    }

    public RealmResults<UserObject> getUserByUnitandName(String unit) {
        return realm.where(UserObject.class)
                .equalTo("unitId", unit).findAll();
    }
}
