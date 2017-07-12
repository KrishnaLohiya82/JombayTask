package info.androidhive.JombayTask.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.JombayTask.R;

import info.androidhive.JombayTask.Realm.UserLesson;
import info.androidhive.JombayTask.Realm.UserLessons;
import info.androidhive.JombayTask.Realm.UserModel;
import info.androidhive.JombayTask.model.Users.ArrayOfUser;
import info.androidhive.JombayTask.model.GetAccessToken;
import info.androidhive.JombayTask.model.GetUserProfile.UserProfiles;
import info.androidhive.JombayTask.rest.ApiClient;
import info.androidhive.JombayTask.rest.ApiInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static String _refreshToken = "";
    public static String _accessToken = "";
    public static String _tokenType = "";
    public static int _expireTime = 0;
    public ListView mCompletedLessons ;
    public ListView mInCompletedLessons ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDatabase();

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        final Button mSignButton = (Button) findViewById(R.id.bt_signin);
        Button mGetData = (Button) findViewById(R.id.bt_getuserprofilr);

        final RelativeLayout mOuterRL = (RelativeLayout) findViewById(R.id.ll_main_signedin);
         mCompletedLessons = (ListView) findViewById(R.id.lv_completed_lessons);
         mInCompletedLessons = (ListView) findViewById(R.id.lv_incompleted_lessons);


        if (mSignButton != null) {
            mSignButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAccessToken(apiService);

                    mSignButton.setVisibility(View.GONE);
                    mOuterRL.setVisibility(View.VISIBLE);
                }
            });
        }

        if (mGetData != null) {
            mGetData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getCurrentProfile(apiService);



                }
            });
        }





    }



    private void initializeDatabase() {

        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    public synchronized void getAccessToken (final ApiInterface apiService){
        Map<String, String > mGrant = new HashMap<>();
        mGrant.put("username","sqfos18@jombay.com");
        mGrant.put("password","test123");
        mGrant.put("grant_type","password");
        mGrant.put("scope","user");

        Call<GetAccessToken> call = apiService.getAccessToken(mGrant);
        call.enqueue(new Callback<GetAccessToken>() {
            @Override
            public void onResponse(Call<GetAccessToken> call, Response<GetAccessToken> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    GetAccessToken mGetAccessToken = response.body();
                    _accessToken = mGetAccessToken.getAccessToken();
                    _refreshToken = mGetAccessToken.getRefreshToken();
                    _tokenType =  mGetAccessToken.getTokenType();
                    _expireTime = mGetAccessToken.getExpiresIn();

                    Toast.makeText(MainActivity.this, "Got access token", Toast.LENGTH_SHORT).show();


                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getRefreshToken(apiService);
                        }
                    },(MainActivity._expireTime * 1000));

                }

                if(response.code() == 401){
                    Toast.makeText(MainActivity.this, "401 for getting access token", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAccessToken> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getUserProfile(ApiInterface apiService) {
        String mToken = "Bearer " + _accessToken;
        Call<UserProfiles> mCallForUserProfiles = apiService.getUserProfiles(mToken);
        mCallForUserProfiles.enqueue(new Callback<UserProfiles>() {
            @Override
            public void onResponse(Call<UserProfiles> call, Response<UserProfiles> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    UserProfiles mArrayOfUserProfiles = response.body();
                    mArrayOfUserProfiles.getUserProfiles();

                     Realm mRealm = Realm.getInstance(getBaseContext());
                    mRealm.beginTransaction();
                    mRealm.clear(UserLessons.class);
                    mRealm.clear(UserLesson.class);
                    mRealm.commitTransaction();

                    mRealm.beginTransaction();
                    // Create an object
                    UserLessons mUserLessons = mRealm.createObject(UserLessons.class);

                    // Set its fields
                    mUserLessons.setId(mArrayOfUserProfiles.getUserProfiles().getUserDocument().getId());

                    for (int i =0 ; i <mArrayOfUserProfiles.getUserProfiles().getUserLessons().size() ; i++ ) {

                        UserLesson mUserLesson = mRealm.copyToRealm(new UserLesson(mArrayOfUserProfiles.getUserProfiles().getUserLessons().get(i).getLessonId()
                                , mArrayOfUserProfiles.getUserProfiles().getUserLessons().get(i).getStatus(),
                                mArrayOfUserProfiles.getUserProfiles().getUserLessons().get(i).getLesson().getTitle()));

                        mUserLessons.getUserLessons().add(mUserLesson);

                    }
                    mRealm.commitTransaction();



                    RealmQuery query = mRealm.where(UserLesson.class).contains("status", "completed");
                    RealmResults resultsforCompleted = query.findAllSorted("lesson");


                    if (resultsforCompleted.size() > 0) {
                        mCompletedLessons.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, resultsforCompleted);
                        mCompletedLessons.setAdapter(adapter);
                    }


                    RealmQuery query1 = mRealm.where(UserLesson.class).contains("status", "incompleted");
                    RealmResults resultsForInCompleted = query1.findAllSorted("lesson");

                    if (resultsForInCompleted.size() > 0) {
                        mInCompletedLessons.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String> (getBaseContext(), android.R.layout.simple_list_item_1, resultsforCompleted);
                        mInCompletedLessons.setAdapter(adapter);
                    }



                }

                if(response.code() == 401)
                    Toast.makeText(MainActivity.this, "401", Toast.LENGTH_SHORT).show();
                }



            @Override
            public void onFailure(Call<UserProfiles> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void getCurrentProfile(final ApiInterface apiService) {
        String mToken = "Bearer " + _accessToken;
        Call<ArrayOfUser> mCall = apiService.getCurrentUserModel(mToken);
        mCall.enqueue(new Callback<ArrayOfUser>() {
            @Override
            public void onResponse(Call<ArrayOfUser> call, Response<ArrayOfUser> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    ArrayOfUser mArray = response.body();
                    mArray.getUser();

                    Realm mRealm = Realm.getInstance(getBaseContext());
                    mRealm.beginTransaction();
                    mRealm.clear(UserModel.class);

                    mRealm.commitTransaction();

                    mRealm.beginTransaction();

                    // Create an object
                    UserModel mUser = mRealm.createObject(UserModel.class);


                    mUser.setId(mArray.getUser().getId());

                    mUser.setCompanyIds(mArray.getUser().getCompanyIds().get(0));
                    mUser.setCreatedAt(mArray.getUser().getCreatedAt());
                    mUser.setEmail(mArray.getUser().getEmail());
                    if (null != mArray.getUser().getIsSuspended()) {
                        mUser.setIsSuspended(mArray.getUser().getIsSuspended().toString());
                    }
                    mUser.setMobile(mArray.getUser().getMobile());
                    mUser.setName(mArray.getUser().getName());
                    mUser.setRoleIds(mArray.getUser().getRoleIds().get(0));
                    mUser.setUsername(mArray.getUser().getUsername());

                    mRealm.commitTransaction();


                    getUserProfile(apiService);
                }

                if (response.code() == 401) {
                    Toast.makeText(MainActivity.this, "401", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayOfUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

        private synchronized void getRefreshToken(ApiInterface apiService) {

            Map<String,String> mRefreahToken = new HashMap<>();
            mRefreahToken.put("refresh_token", _refreshToken);
            mRefreahToken.put("scope","user");
            mRefreahToken.put("grant_type","refresh_token");

            Call<GetAccessToken> mGetRefreshToken = apiService.refreshToken(mRefreahToken);
            mGetRefreshToken.enqueue(new Callback<GetAccessToken>() {
                @Override
                public void onResponse(Call<GetAccessToken> call, Response<GetAccessToken> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                        GetAccessToken mGetAccessToken = response.body();
                        _refreshToken = mGetAccessToken.getAccessToken();
                        _tokenType = mGetAccessToken.getTokenType();
                        _expireTime = mGetAccessToken.getExpiresIn();

                        Toast.makeText(MainActivity.this, "Refreshed access token", Toast.LENGTH_SHORT).show();
                    }

                    if(response.code() == 401){
                        Toast.makeText(MainActivity.this, "401", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<GetAccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
    }



}

