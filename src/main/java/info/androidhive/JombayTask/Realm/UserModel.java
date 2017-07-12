package info.androidhive.JombayTask.Realm;

import io.realm.RealmObject;

/**
 * Created by Krishna on 10-07-2017.
 */

public class UserModel  extends RealmObject{


        private String id;
        private String companyIds ;
        private String createdAt;
        private String email;
        private String isSuspended;
        private String mobile;
        private String name;
        private String roleIds;
        private String updatedAt;
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

        public String getIsSuspended() {
            return isSuspended;
        }

        public void setIsSuspended(String isSuspended) {
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
