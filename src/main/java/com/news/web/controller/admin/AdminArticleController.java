package com.news.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.AdminArticleTitleDTO;
import com.news.web.dto.ArticleDTO;
import com.news.web.dto.ArticleUpdateDTO;
import com.news.web.dto.OneCategoryDTO;
import com.news.web.enums.NumberOfDataEnum;
import com.news.web.enums.PathEnum;
import com.news.web.form.ArticleSaveForm;
import com.news.web.form.ArticleUpdateForm;
import com.news.web.model.AdminInfo;
import com.news.web.service.ArticleService;
import com.news.web.service.OneCategoryService;
import com.news.web.utils.ImgUtil;
import com.news.web.utils.ResultVOUtil;
import com.news.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("admin/article")
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OneCategoryService oneCategoryService;

    /**
     * 显示所有文章界面
     *
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("{pageNum}/list")
    public String list(@PathVariable Integer pageNum, Model model) {

        PageInfo<AdminArticleTitleDTO> pageInfo = articleService.pageAll(pageNum,
                NumberOfDataEnum.ADMIN_ARTICLE.getNumber());

        model.addAttribute("pageInfo", pageInfo);

        return "admin/articles";
    }

    /**
     * 根据关键字查询相应新闻信息
     *
     * @param keyWords
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("{keyWords}/{pageNum}/search")
    public String search(@PathVariable String keyWords, @PathVariable Integer pageNum, Model model) {

        PageInfo<AdminArticleTitleDTO> pageInfo = articleService.pageByBlurryAdmin(keyWords, pageNum,
                NumberOfDataEnum.ADMIN_ARTICLE.getNumber());

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyWords", keyWords);

        return "admin/searchArticles";
    }

    /**
     * 删除文章内容
     *
     * @param articleId
     * @return
     */
    @GetMapping("delete/{articleId}")
    public String delete(@PathVariable String articleId) {

        articleService.remove(articleId);

        return "redirect:/admin/article/1/list";
    }

    /**
     * 显示文章详情
     *
     * @param articleId
     * @param model
     * @return
     */
    @GetMapping("{articleId}/detail")
    public String detail(@PathVariable String articleId, Model model) {

        List<OneCategoryDTO> oneCategoryDTOList = oneCategoryService.listAll();

        ArticleDTO dto = articleService.getOne(articleId);

        model.addAttribute("dto", dto);
        model.addAttribute("oneCategoryDTOList", oneCategoryDTOList);

        return "admin/articleDetail";
    }

    /**
     * 修改文章内容
     *
     * @param form
     * @param request
     * @return
     */
    @PostMapping(value = "{articleId}/update", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultVO update(@RequestBody ArticleUpdateForm form, HttpServletRequest request) {

        //得到项目路径 + 图片存储相对路径
        String imgPath = request.getContextPath() + PathEnum.IMG_UPLOAD_RELATIVE_PATH.getPath();

        //将要保存的图片路径去掉其中的路径 只留下图片名称
        List<String> imgNames = removeProjectPath(imgPath, form.getImgNames());
        form.setImgNames(imgNames);

        articleService.update(form);


        return ResultVOUtil.success();
    }

    /**
     * 跳转到发布文章页面
     *
     * @return
     */
    @GetMapping("jump2publish")
    public String jump2publish(Model model) {

        List<OneCategoryDTO> oneCategoryDTOList = oneCategoryService.listAll();

        model.addAttribute("oneCategoryDTOList", oneCategoryDTOList);

        return "admin/publishArticle";
    }

    /**
     * 保存文章内容
     *
     * @param form
     * @param session
     * @param request
     * @return
     */
    @PostMapping(value = "save", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultVO save(@RequestBody ArticleSaveForm form, HttpSession session, HttpServletRequest request) {

        //得到项目路径 + 图片存储相对路径
        String imgPath = request.getContextPath() + PathEnum.IMG_UPLOAD_RELATIVE_PATH.getPath();

        AdminInfo adminInfo = (AdminInfo) session.getAttribute("existAdmin");

        form.setAuthor(adminInfo.getName());
        form.setAuthorId(adminInfo.getAdminId());

        //将要保存的图片路径去掉其中的路径 只留下图片名称
        List<String> imgNames = removeProjectPath(imgPath, form.getImgNames());
        form.setImgNames(imgNames);

        articleService.save(form);

        return ResultVOUtil.success();
    }

    /**
     * 上传图片
     *
     * @param list
     * @param request
     * @return
     */
    @PostMapping("upload/image")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("images") List<MultipartFile> list, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>(10);
        //错误代码
        int errno = 0;
        //存放数据
        String[] data = new String[0];
        try {
            String context = request.getContextPath();
            data = ImgUtil.upload(list, context).toArray(new String[0]);
        } catch (Exception e) {
            errno = 1;
            e.printStackTrace();
        }
        map.put("errno", errno);
        map.put("data", data);
        return map;
    }

    /**
     * 将文件去掉其中的路径 只留下文件名
     *
     * @param path      要去掉的路径
     * @param filenames 文件路径集合
     * @return 新的文件路径集合
     */
    private List<String> removeProjectPath(String path, List<String> filenames) {

        List<String> result = new ArrayList<>();

        for (String filePath : filenames) {
            if (filePath.startsWith(filePath)) {
                result.add(filePath.substring(path.length()));
            }
        }

        return result;
    }
}
