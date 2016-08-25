package com.zhou.test.ormlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.zhou.test.ormlite.bean.Article;
import com.zhou.test.ormlite.bean.User;
import com.zhou.test.ormlite.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * 文章DAO
 * Created by zhou on 2016/7/29.
 */
public class ArticleDao {

    private Context context;
    private Dao<Article, Integer> articleDaoOpe;
    private DatabaseHelper helper;


    @SuppressWarnings("unchecked")
    public ArticleDao(Context context) {
        try {
            this.context = context;
            helper = DatabaseHelper.getHelper(context);
            articleDaoOpe = helper.getDao(Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个Article
     *
     * @param article
     */
    public void add(Article article) {
        try {
            articleDaoOpe.create(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个Article
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Article getArticleWithUser(int id) {
        Article article = null;
        try {
            article = articleDaoOpe.queryForId(id);
            //调用userDao进行刷新，才能获得user数据
            helper.getDao(User.class).refresh(article.getArticleUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一篇文章
     *
     * @param id
     * @return
     */
    public Article get(int id) {
        Article article = null;
        try {
            //这里面无user信息
            article = articleDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过UserId获取所有的文章
     *
     * @param userId
     * @return
     */
    public List<Article> listByUserId(int userId) {
        try {
            return articleDaoOpe.queryBuilder().where().eq("user_id", userId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
