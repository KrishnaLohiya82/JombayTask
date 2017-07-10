package info.androidhive.retrofit.model.GetUserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna on 08-07-2017.
 */

public class UserProfiles {


        @SerializedName("user_profile")
        @Expose
        private UserProfile userProfile;

        public UserProfile getUserProfiles() {
            return userProfile;
        }

        public void setUserProfiles(UserProfile userProfile) {
            this.userProfile = userProfile;
        }

}


