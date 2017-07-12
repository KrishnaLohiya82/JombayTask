package info.androidhive.JombayTask;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Krishna on 08-07-2017.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {

        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
