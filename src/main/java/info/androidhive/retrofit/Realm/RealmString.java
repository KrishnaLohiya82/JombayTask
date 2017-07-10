package info.androidhive.retrofit.Realm;

import io.realm.RealmObject;

/**
 * Created by Krishna on 10-07-2017.
 */

public class RealmString extends RealmObject {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
