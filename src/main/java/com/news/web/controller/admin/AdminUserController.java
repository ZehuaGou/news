package com.news.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.news.web.dto.UserDTO;
import com.news.web.enums.NumberOfDataEnum;
import com.news.web.form.UserUpdateForm;
import com.news.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * 显示用户列表页面
     *
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("{pageNum}/list")
    public String list(@PathVariable Integer pageNum, Model model) {

        PageInfo<UserDTO> pageInfo = userService.pageAll(pageNum, NumberOfDataEnum.USER.getNumber());

        model.addAttribute("pageInfo", pageInfo);

        return "admin/users";
    }

    /**
     * 显示用户详情信息
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId}/detail")
    public String detail(@PathVariable String userId, Model model) {

        UserDTO dto = userService.getOne(userId);

        model.addAttribute("dto", dto);
        return "admin/userDetail";
    }

    /**
     * 修改用户信息，并重新跳转到详情页
     *
     * @param userId
     * @param form
     * @return
     */
    @PostMapping("{userId}/update")
    public String update(@PathVariable String userId, UserUpdateForm form) {

        form.setUserId(userId);
        userService.update(form);

        return "redirect:/admin/user/" + userId + "/detail";
    }

    @GetMapping("delete/{userId}")
    public String delete(@PathVariable String userId) {
        userService.remove(userId);

        return "redirect:/admin/user/1/list";
    }
}
