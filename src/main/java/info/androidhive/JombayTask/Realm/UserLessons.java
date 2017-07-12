package info.androidhive.JombayTask.Realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Krishna on 11-07-2017.
 */

public class UserLessons extends RealmObject {

    private String id;

    private RealmList<info.androidhive.JombayTask.Realm.UserLesson> userLessons = new RealmList<info.androidhive.JombayTask.Realm.UserLesson>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<info.androidhive.JombayTask.Realm.UserLesson> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(RealmList<info.androidhive.JombayTask.Realm.UserLesson> userLessons) {
        this.userLessons = userLessons;
    }

}
