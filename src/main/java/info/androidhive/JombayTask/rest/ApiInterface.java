package info.androidhive.JombayTask.rest;

import java.util.Map;

import info.androidhive.JombayTask.model.Users.ArrayOfUser;
import info.androidhive.JombayTask.model.GetAccessToken;
import info.androidhive.JombayTask.model.GetUserProfile.UserProfiles;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface ApiInterface {

    @POST("/oauth/token.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<GetAccessToken> getAccessToken(@FieldMap Map<String , String> mMap);



    @GET("users/current.json")
    @Headers("Content-Type : application/json")
    Call<ArrayOfUser> getCurrentUserModel(@Header("Authorization") String token);


    @GET("companies/585772cfd3a07bffe3000077/sq/users/58577374d3a07bffe30000cc/" +
            "user_profile?include[user_lessons][only][]=status&include[user_lessons][include][lesson][only]=title&include[user_lessons][only][]=lesson_id&select[]=_id&select[]=user_document")
    Call<UserProfiles> getUserProfiles(@Header("Authorization") String token);

    @POST("oauth/token.json")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<GetAccessToken> refreshToken(@FieldMap Map<String,String> mMapForRefreshToken );
}
