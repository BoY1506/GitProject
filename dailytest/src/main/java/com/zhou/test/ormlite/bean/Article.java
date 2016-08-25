package com.zhou.test.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 文章表（_id，_title，_content，_uid）
 * Created by zhou on 2016/7/29.
 */
@DatabaseTable(tableName = "article")
public class Article {

    @DatabaseField(columnName = "_id", generatedId = true)
    private int articleId;
    @DatabaseField(columnName = "_title")
    private String articleTitle;
    @DatabaseField(columnName = "_content")
    private String articleContent;
    @DatabaseField(columnName = "_uid", canBeNull = true, foreign = true, foreignAutoRefresh = true)
    //一对一关联，加上这一句，不用去userDao里进行refresh就能在获得文章的同时拿到user信息
    private User articleUser;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public User getArticleUser() {
        return articleUser;
    }

    public void setArticleUser(User articleUser) {
        this.articleUser = articleUser;
    }

}
