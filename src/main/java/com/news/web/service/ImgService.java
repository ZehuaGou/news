package com.news.web.service;

/**
 * @author Paul Lee
 */
public interface ImgService {

    /**
     * 保存图片信息
     *
     * @param path
     * @param articleId
     * @return
     */
    int save(String path, String articleId);

    /**
     * 根据主键删除相应图片信息
     *
     * @param id
     * @return
     */
    int remove(String id);

    /**
     * 根据文章主键删除相应图片信息
     *
     * @param id
     * @return
     */
    int removeByArticle(String id);
}
