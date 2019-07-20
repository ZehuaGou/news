package com.news.web.service;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.AdminArticleTitleDTO;
import com.news.web.dto.ArticleDTO;
import com.news.web.dto.ArticleTitleDTO;
import com.news.web.dto.ArticleUpdateDTO;
import com.news.web.form.ArticleSaveForm;
import com.news.web.form.ArticleUpdateForm;

import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 文章业务层接口
 */
public interface ArticleService {

    /**
     * 保存文章内容
     *
     * @param form 需要保存的文章对象
     * @return 保存成功的数据数量
     */
    int save(ArticleSaveForm form);

    /**
     * 根据id删除文章内容
     *
     * @param id 要删除的文章主键
     * @return 删除成功的数据数量
     */
    int remove(String id);

    /**
     * 根据用户id删除该用户所有发表的文章
     *
     * @param id
     * @return
     */
    int removeByAuthor(String id);

    /**
     * 修改文章内容信息
     *
     * @param form 新的文章内容信息对象
     * @return 带着所有评论信息的文章DTO
     */
    ArticleUpdateDTO update(ArticleUpdateForm form);

    /**
     * 根据主键查询文章信息
     *
     * @param id 主键
     * @return 带着所有评论信息的文章DTO
     */
    ArticleDTO getOne(String id);

    /**
     * 根据一级分类主键查询相关的文章标题
     *
     * @param id       一级分类主键
     * @param pageNum  当前页
     * @param pageSize 每页数据量
     * @return 文章标题信息分页对象
     */
    PageInfo<ArticleTitleDTO> pageByOneCategory(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 根据用户主键查询相关的文章标题
     *
     * @param id 用户主键
     * @return 文章标题
     */
    List<ArticleTitleDTO> listByAuthor(String id);

    /**
     * 查找最近发表的 num 条文章标题
     *
     * @param num 要显示的文章标题数量
     * @return 文章标题
     */
    List<ArticleTitleDTO> listByRecent(int num);

    /**
     * 根据点击量，查找最热门的 num 条文章标题
     *
     * @param num 要显示的文章标题数量
     * @return 文章标题
     */
    List<ArticleTitleDTO> listByClickVolume(int num);

    /**
     * 根据关键词，模糊查询相应的文章标题
     *
     * @param keyWords 关键词
     * @param pageNum  当前页
     * @param pageSize 每页数据量
     * @return 文章标题信息分页对象
     */
    PageInfo<ArticleTitleDTO> pageByBlurry(String keyWords, Integer pageNum, Integer pageSize);

    /**
     * 管理员根据关键词，模糊查询相应的文章标题
     *
     * @param keyWords 关键词
     * @param pageNum  当前页
     * @param pageSize 每页数据量
     * @return 文章标题信息分页对象
     */
    PageInfo<AdminArticleTitleDTO> pageByBlurryAdmin(String keyWords, Integer pageNum, Integer pageSize);

    /**
     * 管理员查询相应的文章标题
     *
     * @param pageNum  当前页
     * @param pageSize 每页数据量
     * @return
     */
    PageInfo<AdminArticleTitleDTO> pageAll(Integer pageNum, Integer pageSize);

    /**
     * 点击率增加一
     *
     * @param articleId
     */
    void increase(String articleId);
}
