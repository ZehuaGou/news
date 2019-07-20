package com.news.web.controller.user;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.ArticleDTO;
import com.news.web.dto.ArticleTitleDTO;
import com.news.web.dto.OneCategoryDTO;
import com.news.web.enums.NumberOfDataEnum;
import com.news.web.form.ArticleSaveForm;
import com.news.web.model.UserInfo;
import com.news.web.service.ArticleService;
import com.news.web.service.OneCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Paul Lee
 * 有关文章的控制层类
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OneCategoryService oneCategoryService;

    /**
     * 首页
     * 根据相应一级分类、页码、每页数据量，将相应信息返回至View
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String index(Model model, HttpSession session) {

        //需要将一级分类查询到的结果存入session，方便后面操作
        List<OneCategoryDTO> dtoList = oneCategoryService.listAll();

        PageInfo<ArticleTitleDTO> pageInfo =
                articleService.pageByOneCategory(1, 1, NumberOfDataEnum.ONE_CATEGORY.getNumber());

        addInformation(model);

        model.addAttribute("pageInfo", pageInfo);
        //前端需要通过一级分类来判断使用哪种一级分类进行翻页
        model.addAttribute("oneCategory", 1);
        session.setAttribute("dtoList", dtoList);

        return "user/articleList";
    }

    /**
     * 根据相应一级分类、页码、每页数据量，将相应信息返回至View
     *
     * @param oneCategory
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("{oneCategory}/{pageNum}/list")
    public String list(@PathVariable Integer oneCategory, @PathVariable Integer pageNum,
                       Model model) {

        PageInfo<ArticleTitleDTO> pageInfo = articleService.pageByOneCategory(oneCategory, pageNum,
                NumberOfDataEnum.ONE_CATEGORY.getNumber());

        addInformation(model);

        model.addAttribute("pageInfo", pageInfo);
        //前端需要通过一级分类来判断使用哪种一级分类进行翻页
        model.addAttribute("oneCategory", oneCategory);

        return "user/articleList";
    }

    /**
     * 根据相应关键词、页码、每页数据量，将相应信息返回至View
     *
     * @param keyWords
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("search/{keyWords}/{pageNum}")
    public String search(@PathVariable String keyWords,
                         @PathVariable Integer pageNum, Model model) {

        PageInfo<ArticleTitleDTO> pageInfo =
                articleService.pageByBlurry(keyWords, pageNum, NumberOfDataEnum.ONE_CATEGORY.getNumber());

        addInformation(model);

        model.addAttribute("pageInfo", pageInfo);
        //前端需要通过关键词来判断进行翻页
        model.addAttribute("keyWords", keyWords);

        return "user/articleList";
    }

    /**
     * 根据文章id返回当前文章详详细内容
     *
     * @param articleId
     * @param model
     * @return
     */
    @GetMapping("{articleId}/detail")
    public String detail(@PathVariable String articleId, Model model) {

        //得到该文章详细内容
        ArticleDTO dto = articleService.getOne(articleId);

        articleService.increase(articleId);

        //查询与该文章相关的文章
        PageInfo<ArticleTitleDTO> relevant = articleService.pageByOneCategory(dto.getOneCategoryId(), 1,
                NumberOfDataEnum.RELEVANT.getNumber());

        model.addAttribute("dto", dto);
        model.addAttribute("relevant", relevant.getList());

        return "user/articleDetail";
    }

    /**
     * 添加新的文章内容
     *
     * @param form
     * @param session
     * @return
     */
    @PostMapping("save")
    public String save(ArticleSaveForm form, HttpSession session) {

        UserInfo userInfo = (UserInfo) session.getAttribute("existUser");

        form.setAuthorId(userInfo.getUserId());
        form.setAuthor(userInfo.getUsername());
        articleService.save(form);

        //TODO 暂时不做这个功能

        return "articleList";
    }

    /**
     * 将最新以及最热门的文章存入Model中
     *
     * @param model
     */
    private void addInformation(Model model) {
        //查询最近发布的文章
        List<ArticleTitleDTO> recentList = articleService.listByRecent(NumberOfDataEnum.RECENT.getNumber());

        //查询最热门的文章
        List<ArticleTitleDTO> hotList = articleService.listByClickVolume(NumberOfDataEnum.HOT.getNumber());

        model.addAttribute("recentList", recentList);
        model.addAttribute("hotList", hotList);
    }
}
