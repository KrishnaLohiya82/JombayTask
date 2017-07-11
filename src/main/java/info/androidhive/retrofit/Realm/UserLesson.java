package info.androidhive.retrofit.Realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import info.androidhive.retrofit.model.GetUserProfile.Lesson;
import io.realm.RealmObject;

/**
 * Created by Krishna on 11-07-2017.
 */

public class UserLesson extends RealmObject{

    private String lessonId;
    private String status;
    private String lesson;

    public  UserLesson(){}

    public UserLesson(String pLessonId, String pStatus,
             String pLesson
    ){
        this.lessonId = pLessonId;
        this.lesson =  pLesson;
        this.status = pStatus;
    }
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

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

}
