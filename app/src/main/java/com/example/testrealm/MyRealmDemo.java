package com.example.testrealm;

    import io.realm.RealmList;
    import io.realm.RealmObject;
    /**
     * @author Admin
     * https://realm.io/docs/java/latest/
     */
    public class MyRealmDemo extends RealmObject {

        String userName;
        String pwd;
        Long sessionId;
        private RealmList<Integer> Remarks = new RealmList<>();

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public Long getSessionId() {
            return sessionId;
        }

        public void setSessionId(Long sessionId) {
            this.sessionId = sessionId;
        }

        public RealmList<Integer> getRemarks() {
            return Remarks;
        }

        public void setRemarks(RealmList<Integer> remarks) {
            Remarks = remarks;
        }


        @Override
        public String toString() {
            return "MyRealmDemo{" +
                    "userName='" + userName + '\'' +
                    ", pwd='" + pwd + '\'' +
                    ", sessionId=" + sessionId +
                    ", Remarks=" + Remarks.size() +
                    '}';
        }
    }
