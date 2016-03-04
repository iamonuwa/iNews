package com.nowayo.news.services;

/**
 * Created by matrix on 03/03/2016.
 */
public class AppData {

        private String newsId;

        private String newsActive;

        private String newsTitle;

        private String newsTitleUrl;

        private String newsContent;

        private String category;

        private String newsAdded;

        private String uploadId;

        private String newsUserFk;

        private String link;

        private String type;

        private String serverCreated;

    public AppData(String longForm, String title, String content, String date, String image) {
        this.newsId = longForm;
        this.newsTitle = title;
        this.newsContent = content;
        this.newsAdded = date;
        this.link = image;
    }

    /**
         *
         * @return
         * The newsId
         */
        public String getNewsId() {
            return newsId;
        }

        /**
         *
         * @param newsId
         * The news_id
         */
        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        /**
         *
         * @return
         * The newsActive
         */
        public String getNewsActive() {
            return newsActive;
        }

        /**
         *
         * @param newsActive
         * The news_active
         */
        public void setNewsActive(String newsActive) {
            this.newsActive = newsActive;
        }

        /**
         *
         * @return
         * The newsTitle
         */
        public String getNewsTitle() {
            return newsTitle;
        }

        /**
         *
         * @param newsTitle
         * The news_title
         */
        public void setNewsTitle(String newsTitle) {
            this.newsTitle = newsTitle;
        }

        /**
         *
         * @return
         * The newsTitleUrl
         */
        public String getNewsTitleUrl() {
            return newsTitleUrl;
        }

        /**
         *
         * @param newsTitleUrl
         * The news_title_url
         */
        public void setNewsTitleUrl(String newsTitleUrl) {
            this.newsTitleUrl = newsTitleUrl;
        }

        /**
         *
         * @return
         * The newsContent
         */
        public String getNewsContent() {
            return newsContent;
        }

        /**
         *
         * @param newsContent
         * The news_content
         */
        public void setNewsContent(String newsContent) {
            this.newsContent = newsContent;
        }

        /**
         *
         * @return
         * The category
         */
        public String getCategory() {
            return category;
        }

        /**
         *
         * @param category
         * The category
         */
        public void setCategory(String category) {
            this.category = category;
        }

        /**
         *
         * @return
         * The newsAdded
         */
        public String getNewsAdded() {
            return newsAdded;
        }

        /**
         *
         * @param newsAdded
         * The news_added
         */
        public void setNewsAdded(String newsAdded) {
            this.newsAdded = newsAdded;
        }

        /**
         *
         * @return
         * The uploadId
         */
        public String getUploadId() {
            return uploadId;
        }

        /**
         *
         * @param uploadId
         * The upload_id
         */
        public void setUploadId(String uploadId) {
            this.uploadId = uploadId;
        }

        /**
         *
         * @return
         * The newsUserFk
         */
        public String getNewsUserFk() {
            return newsUserFk;
        }

        /**
         *
         * @param newsUserFk
         * The news_user_fk
         */
        public void setNewsUserFk(String newsUserFk) {
            this.newsUserFk = newsUserFk;
        }

        /**
         *
         * @return
         * The link
         */
        public String getLink() {
            return link;
        }

        /**
         *
         * @param link
         * The link
         */
        public void setLink(String link) {
            this.link = link;
        }

        /**
         *
         * @return
         * The type
         */
        public String getType() {
            return type;
        }

        /**
         *
         * @param type
         * The type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         *
         * @return
         * The serverCreated
         */
        public String getServerCreated() {
            return serverCreated;
        }

        /**
         *
         * @param serverCreated
         * The server_created
         */
        public void setServerCreated(String serverCreated) {
            this.serverCreated = serverCreated;
        }

    }

