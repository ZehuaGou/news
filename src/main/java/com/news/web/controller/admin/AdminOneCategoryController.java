package com.news.web.controller.admin;

import com.news.web.dto.OneCategoryDTO;
import com.news.web.form.OneCategoryUpdateForm;
import com.news.web.service.OneCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("admin/oneCategory")
public class AdminOneCategoryController {

    @Autowired
    private OneCategoryService oneCategoryService;

    /**
     * 显示所有的一级分类信息
     *
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Model model) {

        //获得所有一级分类dto信息
        List<OneCategoryDTO> dtoList = oneCategoryService.listAll();
        model.addAttribute("dtoList", dtoList);

        return "admin/oneCategory";
    }

    /**
     * 获得一级分类详细信息并显示
     *
     * @param oneCategoryId
     * @param model
     * @return
     */
    @GetMapping("{oneCategoryId}/detail")
    public String detail(@PathVariable Integer oneCategoryId, Model model) {

        OneCategoryDTO dto = oneCategoryService.getOne(oneCategoryId);
        model.addAttribute("dto", dto);

        return "admin/oneCategoryDetail";
    }

    /**
     * 对一级分类进行修改
     *
     * @param oneCategoryId
     * @param form
     * @return
     */
    @PostMapping("{oneCategoryId}/update")
    public String update(@PathVariable Integer oneCategoryId, OneCategoryUpdateForm form) {

        form.setOneCategoryId(oneCategoryId);
        oneCategoryService.update(form);

        return "redirect:/admin/oneCategory/" + oneCategoryId + "/detail";
    }
}
