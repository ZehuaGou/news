package com.news.web.service;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.ReplyDTO;
import com.news.web.dto.ReplyInArticleDTO;
import com.news.web.form.ReplySaveForm;

import java.util.List;

/**
 * @author Paul Lee
 * <p>
 * 回复业务层接口
 */
public interface ReplyService {

    /**
     * 保存回复内容信息
     *
     * @param form 需要保存的回复form对象
     * @return 保存成功的数据数量
     */
    int save(ReplySaveForm form);

    /**
     * 根据主键删除回复信息
     *
     * @param id 需要删除的回复信息的主键
     * @return 删除成功的数据数量
     */
    int remove(String id);

    /**
     * 根据文章id删除关联的所有评论
     *
     * @param id 文章主键
     * @return 删除成功的数据数量
     */
    int removeByArticle(String id);

    /**
     * 根据用户id删除此用户所有评论
     *
     * @param id
     * @return
     */
    int removeByAuthor(String id);

    /**
     * 根据用户id查询相应的回复内容信息
     *
     * @param id       用户主键
     * @param pageNum  当前页
     * @param pageSize 每页数据量
     * @return 回复内容信息分页对象
     */
    PageInfo<ReplyDTO> pageByAuthor(String id, Integer pageNum, Integer pageSize);

    /**
     * 根据文章id查询相应的回复集合
     *
     * @param id 文章主键
     * @return 回复内容信息
     */
    List<ReplyInArticleDTO> listByArticle(String id);
}
