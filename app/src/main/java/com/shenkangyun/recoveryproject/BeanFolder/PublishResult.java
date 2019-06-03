package com.shenkangyun.recoveryproject.BeanFolder;

/**
 * Created by Administrator on 2018/4/4.
 */

public class PublishResult {

    /**
     * status : 0
     * data : {"shows":{"id":"40288052628e88b301628e8c26ad001e","name":"测试","content":"测试","coverPhoto":"","createUser":"石屹哲","createTime":"2018-04-04 10:46:12","updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shows : {"id":"40288052628e88b301628e8c26ad001e","name":"测试","content":"测试","coverPhoto":"","createUser":"石屹哲","createTime":"2018-04-04 10:46:12","updateUser":null,"updateTime":null,"delFlag":0,"delTime":null,"remark":null}
         */

        private ShowsBean shows;

        public ShowsBean getShows() {
            return shows;
        }

        public void setShows(ShowsBean shows) {
            this.shows = shows;
        }

        public static class ShowsBean {
            /**
             * id : 40288052628e88b301628e8c26ad001e
             * name : 测试
             * content : 测试
             * coverPhoto :
             * createUser : 石屹哲
             * createTime : 2018-04-04 10:46:12
             * updateUser : null
             * updateTime : null
             * delFlag : 0
             * delTime : null
             * remark : null
             */

            private String id;
            private String name;
            private String content;
            private String coverPhoto;
            private String createUser;
            private String createTime;
            private String updateUser;
            private String updateTime;
            private int delFlag;
            private String delTime;
            private String remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCoverPhoto() {
                return coverPhoto;
            }

            public void setCoverPhoto(String coverPhoto) {
                this.coverPhoto = coverPhoto;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }

            public String getDelTime() {
                return delTime;
            }

            public void setDelTime(String delTime) {
                this.delTime = delTime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
