package info.androidhive.retrofit.Realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import info.androidhive.retrofit.model.GetUserProfile.UserDocument;
import info.androidhive.retrofit.model.GetUserProfile.UserLesson;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Krishna on 11-07-2017.
 */

public class UserLessons extends RealmObject {

    private String id;

    private RealmList<info.androidhive.retrofit.Realm.UserLesson> userLessons = new RealmList<info.androidhive.retrofit.Realm.UserLesson>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<info.androidhive.retrofit.Realm.UserLesson> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(RealmList<info.androidhive.retrofit.Realm.UserLesson> userLessons) {
        this.userLessons = userLessons;
    }

}
