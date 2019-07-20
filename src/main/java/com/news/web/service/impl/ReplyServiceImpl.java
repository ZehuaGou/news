package com.news.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.web.utils.KeyUtil;
import com.news.web.dto.ReplyDTO;
import com.news.web.dto.ReplyInArticleDTO;
import com.news.web.form.ReplySaveForm;
import com.news.web.mapper.ReplyMapper;
import com.news.web.model.Reply;
import com.news.web.model.ReplyExample;
import com.news.web.service.ReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Lee
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper mapper;

    @Override
    public int save(ReplySaveForm form) {

        Reply reply = new Reply();
        BeanUtils.copyProperties(form, reply);
        reply.setReplyId(KeyUtil.genUniqueKey());

        return mapper.insert(reply);
    }

    @Override
    public int remove(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeByArticle(String id) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(id);

        return mapper.deleteByExample(example);
    }

    @Override
    public int removeByAuthor(String id) {

        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();

        criteria.andAuthorIdEqualTo(id);

        return mapper.deleteByExample(example);
    }

    @Override
    public PageInfo<ReplyDTO> pageByAuthor(String id, Integer pageNum, Integer pageSize) {

        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();

        criteria.andAuthorEqualTo(id);
        example.setOrderByClause("create_time DESC");

        PageHelper.startPage(pageNum, pageSize, true);

        List<Reply> replyList = mapper.selectByExample(example);

        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);

        return reply2ReplyDTO(pageInfo);
    }

    @Override
    public List<ReplyInArticleDTO> listByArticle(String id) {
        ReplyExample example = new ReplyExample();
        ReplyExample.Criteria criteria = example.createCriteria();

        criteria.andArticleIdEqualTo(id);
        example.setOrderByClause("create_time DESC");

        List<Reply> replyList = mapper.selectByExample(example);

        return reply2ReplyInArticleDTO(replyList);
    }

    /**
     * 传入Reply集合 将其转化为ReplyInArticleDTO集合并返回
     *
     * @param list Reply集合
     * @return ReplyInArticleDTO集合
     */
    private List<ReplyInArticleDTO> reply2ReplyInArticleDTO(List<Reply> list) {

        List<ReplyInArticleDTO> result = new ArrayList<>();
        for (Reply reply : list) {
            ReplyInArticleDTO dto = new ReplyInArticleDTO();
            BeanUtils.copyProperties(reply, dto);
            result.add(dto);
        }

        return result;
    }

    /**
     * 传入Reply分页对象 将其转化为ReplyDTO分页对象并返回
     *
     * @param pageInfo Reply分页对象
     * @return ReplyDTO分页对象
     */
    private PageInfo<ReplyDTO> reply2ReplyDTO(PageInfo<Reply> pageInfo) {

        PageInfo<ReplyDTO> result = new PageInfo<>();

        List<ReplyDTO> dtoList = new ArrayList<>();

        for (Reply reply : pageInfo.getList()) {
            ReplyDTO dto = new ReplyDTO();
            BeanUtils.copyProperties(reply, dto);

            dtoList.add(dto);
        }

        BeanUtils.copyProperties(pageInfo, result);
        result.setList(dtoList);

        return result;
    }
}
