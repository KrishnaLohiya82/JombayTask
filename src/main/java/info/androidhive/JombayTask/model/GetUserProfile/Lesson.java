package info.androidhive.JombayTask.model.GetUserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna on 08-07-2017.
 */

public class Lesson {

        @SerializedName("title")
        @Expose
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
