package info.androidhive.retrofit.model.GetUserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import info.androidhive.retrofit.model.GetUserProfile.Lesson;

/**
 * Created by Krishna on 08-07-2017.
 */

public class UserLesson {

        @SerializedName("lesson_id")
        @Expose
        private String lessonId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("lesson")
        @Expose
        private Lesson lesson;

        public String getLessonId() {
            return lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

    }
