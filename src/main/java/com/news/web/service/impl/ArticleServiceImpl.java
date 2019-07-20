package com.news.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.web.dto.*;
import com.news.web.utils.KeyUtil;
import com.news.web.form.ArticleSaveForm;
import com.news.web.form.ArticleUpdateForm;
import com.news.web.mapper.ArticleMapper;
import com.news.web.model.Article;
import com.news.web.model.ArticleExample;
import com.news.web.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private ReplyServiceImpl replyService;

    @Autowired
    private ImgServiceImpl imgService;

    @Autowired
    private OneCategoryServiceImpl oneCategoryService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int save(ArticleSaveForm form) {

        Article article = new Article();
        BeanUtils.copyProperties(form, article);

        //查找相应的一级分类信息
        OneCategoryDTO oneCategoryDTO = oneCategoryService.getOne(form.getOneCategoryId());
        //将一级分类标题存入文章对象
        article.setOneCategoryName(oneCategoryDTO.getName());

        //获得随机文章主键
        String articleId = KeyUtil.genUniqueKey();

        //将所有图片路径取出并保存
        for (String path : form.getImgNames()) {
            imgService.save(path, articleId);
        }

        article.setArticleId(articleId);

        return mapper.insertSelective(article);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int remove(String id) {
        //删除此文章下所有关联的回复信息
        replyService.removeByArticle(id);
        //删除此文章中所有图片
        imgService.removeByArticle(id);
        //根据主键删除此文章信息
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeByAuthor(String id) {

        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andAuthorIdEqualTo(id);

        List<Article> articleList = mapper.selectByExample(example);

        //统计删除总数量
        int count = 0;
        for (Article article : articleList) {
            //删除此文章下所有关联的回复信息以及此文章
            int num = remove(article.getArticleId());

            count += num;
        }

        return count;
    }

    @Override
    public ArticleUpdateDTO update(ArticleUpdateForm form) {

        Article article = new Article();

        //将form内容复制到article对象中
        BeanUtils.copyProperties(form, article);

        //查找相应的一级分类信息
        OneCategoryDTO oneCategoryDTO = oneCategoryService.getOne(form.getOneCategoryId());
        //将一级分类标题存入文章对象
        article.setOneCategoryName(oneCategoryDTO.getName());

        //修改文章信息
        mapper.updateByPrimaryKeySelective(article);

        Article temp = mapper.selectByPrimaryKey(article.getArticleId());

        return article2articleUpdateDTO(temp);
    }

    @Override
    public ArticleDTO getOne(String id) {
        Article article = mapper.selectByPrimaryKey(id);

        //将Article转化为ArticleDTO
        ArticleDTO result = article2articleDTO(article);
        //通过文章id查询此文章下所有回复
        List<ReplyInArticleDTO> replyList = replyService.listByArticle(id);

        result.setReplyDTOList(replyList);

        return result;
    }

    @Override
    public PageInfo<ArticleTitleDTO> pageByOneCategory(Integer id, Integer pageNum, Integer pageSize) {

        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andOneCategoryIdEqualTo(id);
        example.setOrderByClause("create_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);

        List<Article> articleList = mapper.selectByExample(example);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);

        return article2articleTitleDTO(pageInfo);
    }

    @Override
    public List<ArticleTitleDTO> listByAuthor(String id) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andAuthorIdEqualTo(id);
        example.setOrderByClause("create_time DESC");

        List<Article> articleList = mapper.selectByExample(example);

        return article2articleTitleDTO(articleList);
    }

    @Override
    public List<ArticleTitleDTO> listByRecent(int num) {
        ArticleExample example = new ArticleExample();

        example.setOrderByClause("create_time DESC");
        //利用分页的规律查询出相应数量的新闻
        PageHelper.startPage(1, num, true);
        List<Article> articleList = mapper.selectByExample(example);

        PageInfo<ArticleTitleDTO> pageInfo = new PageInfo<>(article2articleTitleDTO(articleList));

        return pageInfo.getList();
    }

    @Override
    public List<ArticleTitleDTO> listByClickVolume(int num) {
        ArticleExample example = new ArticleExample();

        example.setOrderByClause("click_volume DESC");
        //利用分页的规律查询出相应数量的新闻
        PageHelper.startPage(1, num, true);
        List<Article> articleList = mapper.selectByExample(example);

        PageInfo<ArticleTitleDTO> pageInfo = new PageInfo<>(article2articleTitleDTO(articleList));

        return pageInfo.getList();
    }

    @Override
    public PageInfo<ArticleTitleDTO> pageByBlurry(String keyWords, Integer pageNum, Integer pageSize) {

        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andTitleLike("%" + keyWords + "%");
        example.setOrderByClause("create_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);
        List<Article> articleList = mapper.selectByExample(example);

        PageInfo<Article> pageInfo = new PageInfo<>(articleList);

        return article2articleTitleDTO(pageInfo);
    }

    @Override
    public PageInfo<AdminArticleTitleDTO> pageByBlurryAdmin(String keyWords, Integer pageNum, Integer pageSize) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();

        criteria.andTitleLike("%" + keyWords + "%");
        example.setOrderByClause("create_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);
        List<Article> articleList = mapper.selectByExample(example);

        PageInfo<Article> pageInfo = new PageInfo<>(articleList);

        return article2adminArticleTitleDTO(pageInfo);
    }

    @Override
    public PageInfo<AdminArticleTitleDTO> pageAll(Integer pageNum, Integer pageSize) {
        ArticleExample example = new ArticleExample();

        example.setOrderByClause("create_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);
        List<Article> articleList = mapper.selectByExample(example);

        PageInfo<Article> pageInfo = new PageInfo<>(articleList);

        return article2adminArticleTitleDTO(pageInfo);
    }

    @Override
    public void increase(String articleId) {
        Article article = mapper.selectByPrimaryKey(articleId);
        article.setClickVolume(article.getClickVolume() + 1);

        mapper.updateByPrimaryKey(article);
    }

    /**
     * 将Article对象转化为ArticleUpdateDTO对象
     *
     * @param article Article对象
     * @return ArticleUpdateDTO对象
     */
    private ArticleUpdateDTO article2articleUpdateDTO(Article article) {

        ArticleUpdateDTO dto = new ArticleUpdateDTO();
        BeanUtils.copyProperties(article, dto);

        return dto;
    }

    /**
     * 将Article对象转化为ArticleDTO对象
     *
     * @param article Article对象
     * @return ArticleDTO对象
     */
    private ArticleDTO article2articleDTO(Article article) {

        ArticleDTO dto = new ArticleDTO();
        BeanUtils.copyProperties(article, dto);

        return dto;
    }

    /**
     * 将Article分页对象转化为ArticleTitleDTO分页对象
     *
     * @param pageInfo Article分页对象
     * @return ArticleTitleDTO分页对象
     */
    private PageInfo<ArticleTitleDTO> article2articleTitleDTO(PageInfo<Article> pageInfo) {
        PageInfo<ArticleTitleDTO> result = new PageInfo<>();

        List<ArticleTitleDTO> dtoList = new ArrayList<>();

        for (Article article : pageInfo.getList()) {
            ArticleTitleDTO dto = new ArticleTitleDTO();
            BeanUtils.copyProperties(article, dto);
            dtoList.add(dto);
        }

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(dtoList);

        return result;
    }


    /**
     * 将Article对象集合转化为ArticleTitleDTO对象集合
     *
     * @param list List对象
     * @return List对象
     */
    private List<ArticleTitleDTO> article2articleTitleDTO(List<Article> list) {

        List<ArticleTitleDTO> result = new ArrayList<>();

        for (Article article : list) {
            ArticleTitleDTO dto = new ArticleTitleDTO();
            BeanUtils.copyProperties(article, dto);
            result.add(dto);
        }

        return result;
    }

    /**
     * 将Article分页对象转化为AdminArticleTitleDTO分页对象
     *
     * @param pageInfo Article分页对象
     * @return AdminArticleTitleDTO分页对象
     */
    private PageInfo<AdminArticleTitleDTO> article2adminArticleTitleDTO(PageInfo<Article> pageInfo) {
        PageInfo<AdminArticleTitleDTO> result = new PageInfo<>();

        List<AdminArticleTitleDTO> dtoList = new ArrayList<>();

        for (Article article : pageInfo.getList()) {
            AdminArticleTitleDTO dto = new AdminArticleTitleDTO();
            BeanUtils.copyProperties(article, dto);
            dtoList.add(dto);
        }

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(dtoList);

        return result;
    }
}
