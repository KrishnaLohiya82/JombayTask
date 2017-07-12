package info.androidhive.JombayTask.model.Users;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna on 08-07-2017.
 */

public class User  {

        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("company_ids")
        @Expose
        private List<String> companyIds = null;

        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("is_suspended")
        @Expose
        private Object isSuspended;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("role_ids")
        @Expose
        private List<String> roleIds = null;
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

        public List<String> getCompanyIds() {
            return companyIds;
        }

        public void setCompanyIds(List<String> companyIds) {
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

        public Object getIsSuspended() {
            return isSuspended;
        }

        public void setIsSuspended(Object isSuspended) {
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

        public List<String> getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(List<String> roleIds) {
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

