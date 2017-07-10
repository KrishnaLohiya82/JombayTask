package info.androidhive.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.androidhive.retrofit.R;
import info.androidhive.retrofit.Realm.RealmString;
import info.androidhive.retrofit.Realm.UserModel;
import info.androidhive.retrofit.model.GetUserProfile.UserProfile;
import info.androidhive.retrofit.model.TokenAccessRequest;
import info.androidhive.retrofit.model.Users.ArrayOfUser;
import info.androidhive.retrofit.model.GetAccessToken;
import info.androidhive.retrofit.model.GetUserProfile.UserProfiles;
import info.androidhive.retrofit.model.Users.User;
import info.androidhive.retrofit.rest.ApiClient;
import info.androidhive.retrofit.rest.ApiInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static String _refreshToken = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDatabase();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //getAccessToken(apiService);

        getCurrentProfile(apiService);

        //getUserProfile(apiService);

        //getRefreshToken(apiService);

    }

    private void initializeDatabase() {
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }


    public void getUserProfile(ApiInterface apiService) {
        String mToken =  "bearer 373e662f8c4a61567b580ee91ebe285dd4ed663216dcdd3d06f8247a356db6c3";
        Call<UserProfiles> mCallForUserProfiles = apiService.getUserProfiles(mToken);
        mCallForUserProfiles.enqueue(new Callback<UserProfiles>() {
            @Override
            public void onResponse(Call<UserProfiles> call, Response<UserProfiles> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    UserProfiles mArrayOfUserProfiles = response.body();
                    mArrayOfUserProfiles.getUserProfiles();

                    /*Realm mRealm = Realm.getInstance(getBaseContext());
                    mRealm.beginTransaction();

                    // Create an object
                    UserProfile mUserProfile = mRealm.createObject(UserProfile.class);

                    // Set its fields
                    mUserProfile.setId(mArrayOfUserProfiles.getUserProfiles().getId());
                    mUserProfile.setUserDocument(mArrayOfUserProfiles.getUserProfiles().getUserDocument());
                    mUserProfile.setUserLessons(mArrayOfUserProfiles.getUserProfiles().getUserLessons());

                    mRealm.commitTransaction();
*/                }

                if(response.code() == 401){
                    Toast.makeText(MainActivity.this, "401", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserProfiles> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getAccessToken (ApiInterface apiService){

        TokenAccessRequest mTokenAccessRequest = new TokenAccessRequest();
        Map<String, String > mGrant = new HashMap<>();
        mGrant.put("username","sqfos18@jombay.com");
        mTokenAccessRequest.set_grantType(mGrant);

        Map<String, String > mPassword = new HashMap<>();
        mGrant.put("password","test123");

        mTokenAccessRequest.set_password(mPassword);

        Map<String, String > mUser = new HashMap<>();
        mGrant.put("grant_type","password");

        mTokenAccessRequest.set_scope(mUser);

        Map<String, String > mScope = new HashMap<>();
        mGrant.put("scope","user");

        mTokenAccessRequest.set_username(mScope);

        Call<GetAccessToken> call = apiService.getAccessToken(mTokenAccessRequest);
        call.enqueue(new Callback<GetAccessToken>() {
            @Override
            public void onResponse(Call<GetAccessToken> call, Response<GetAccessToken> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    GetAccessToken mGetAccessToken = response.body();
                    mGetAccessToken.getAccessToken();
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


    public void getCurrentProfile(ApiInterface apiService) {
        String mToken = "bearer 591966fe7c84b5d0c37d7551e2710bd6284870d150ccbdf98ba654d6b18bbfe0";
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

                    // Create an object
                    UserModel mUser = mRealm.createObject(UserModel.class);

                    // Set its fields
                    mUser.setId(mArray.getUser().getId());

                 /*   RealmString realmString = new RealmString();
                    realmString.setName(mArray.getUser().getCompanyIds().get(0));
                    RealmList<RealmString> realmStringList =  new RealmList<RealmString>();
                    realmStringList.add(realmString);
*/
                    mUser.setCompanyIds(mArray.getUser().getCompanyIds().get(0));

                    mUser.setCreatedAt(mArray.getUser().getCreatedAt());
                    mUser.setEmail(mArray.getUser().getEmail());
                    /*mUser.setIsSuspended(mArray.getUser().getIsSuspended().toString());
                    mUser.setMobile(mArray.getUser().getMobile());
                    mUser.setName(mArray.getUser().getName());
                    mUser.setRoleIds(mArray.getUser().getRoleIds());
                    mUser.setUsername(mArray.getUser().getUsername());
*/
                    mRealm.commitTransaction();
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

        private void getRefreshToken(ApiInterface apiService) {
            _refreshToken = "c2dcfa930305246676ea6606c694d3bef23beaa21010466ef847c95a179bd12c";

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
                        mGetAccessToken.getAccessToken();
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
