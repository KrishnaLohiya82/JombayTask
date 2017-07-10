package info.androidhive.retrofit.rest;

import java.util.Map;

import info.androidhive.retrofit.model.TokenAccessRequest;
import info.androidhive.retrofit.model.Users.ArrayOfUser;
import info.androidhive.retrofit.model.GetAccessToken;
import info.androidhive.retrofit.model.GetUserProfile.UserProfiles;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;



public interface ApiInterface {

    @POST("/oauth/token.json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<GetAccessToken> getAccessToken(@Body TokenAccessRequest mTokenAccessRequest);



    @GET("users/current.json")
    @Headers("Content-Type : application/json")
    Call<ArrayOfUser> getCurrentUserModel(@Header("Authorization") String token);


    @GET("companies/585772cfd3a07bffe3000077/sq/users/58577374d3a07bffe30000cc/" +
            "user_profile?include[user_lessons][only][]=status&include[user_lessons][include][lesson][only]=title&include[user_lessons][only][]=lesson_id&select[]=_id&select[]=user_document")
    Call<UserProfiles> getUserProfiles(@Header("Authorization") String token);

    @POST("oauth/token.json")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @Multipart
    Call<GetAccessToken> refreshToken(@Body Map<String,String> mMapForRefreshToken );
}
