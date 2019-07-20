package com.news.web.service.impl;

import com.news.web.mapper.ImgMapper;
import com.news.web.model.Img;
import com.news.web.model.ImgExample;
import com.news.web.service.ImgService;
import com.news.web.utils.ImgUtil;
import com.news.web.utils.KeyUtil;
import com.news.web.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Paul Lee
 */
@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgMapper mapper;

    @Override
    public int save(String path, String articleId) {

        Img img = new Img();
        img.setImgId(KeyUtil.genUniqueKey());
        img.setAbsolutePath(path);
        img.setArticleId(articleId);

        return mapper.insert(img);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int remove(String id) {

        Img img = mapper.selectByPrimaryKey(id);
        //获得图片在服务器下路径
        String path = PathUtil.imgPath() + img.getAbsolutePath();
        //删除服务器下图片
        ImgUtil.delete(path);

        //删除数据库中图片数据
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeByArticle(String id) {
        ImgExample example = new ImgExample();
        ImgExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(id);
        List<Img> list = mapper.selectByExample(example);

        //统计移除总数
        int count = 0;

        for (Img img : list) {
            int num = remove(img.getImgId());
            count += num;
        }
        return count;
    }
}
