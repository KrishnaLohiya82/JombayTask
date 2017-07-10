package info.androidhive.retrofit.Realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Krishna on 10-07-2017.
 */

public class UserModel  extends RealmObject{


        @SerializedName("_id")
        @Expose
        private String id;

        private String companyIds ;

        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("is_suspended")
        @Expose
        private RealmString isSuspended;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("role_ids")
        @Expose
        private String roleIds;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("username")
        @Expose
        private String username;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyIds() {
            return companyIds;
        }

        public void setCompanyIds(String companyIds) {
            this.companyIds = companyIds;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public RealmString getIsSuspended() {
            return isSuspended;
        }

        public void setIsSuspended(RealmString isSuspended) {
            this.isSuspended = isSuspended;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(String roleIds) {
            this.roleIds = roleIds;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }



}
