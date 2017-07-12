package info.androidhive.JombayTask.model.GetUserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Krishna on 08-07-2017.
 */

public class UserProfile {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("user_document")
        @Expose
        private UserDocument userDocument;
        @SerializedName("user_lessons")
        @Expose
        private List<UserLesson> userLessons = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public UserDocument getUserDocument() {
            return userDocument;
        }

        public void setUserDocument(UserDocument userDocument) {
            this.userDocument = userDocument;
        }

        public List<UserLesson> getUserLessons() {
            return userLessons;
        }

        public void setUserLessons(List<UserLesson> userLessons) {
            this.userLessons = userLessons;
        }

    }

