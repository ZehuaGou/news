package com.news.web.controller.admin;

import com.news.web.form.AdminLoginForm;
import com.news.web.model.AdminInfo;
import com.news.web.service.AdminService;
import com.news.web.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 验证管理员登录账号密码
     * produces = {"application/json;charset=UTF-8"} 设定返回json值有关配置
     *
     * @param form
     * @param session
     * @return
     */
    @PostMapping(value = "login", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResultVO login(@RequestBody AdminLoginForm form, HttpSession session) {

        AdminInfo adminInfo = adminService.checkUserLogin(form);

        if (adminInfo != null) {
            session.setAttribute("existAdmin", adminInfo);

            return new LoginResultVO(true, "success");
        } else {
            return new LoginResultVO(false, "用户名或密码错误！");
        }
    }

    @GetMapping("index")
    public String index() {
        return "redirect:/admin/oneCategory/list";
    }
}
