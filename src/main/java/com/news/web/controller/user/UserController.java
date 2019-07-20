package com.news.web.controller.user;

import com.news.web.form.UserLoginForm;
import com.news.web.form.UserSaveForm;
import com.news.web.model.UserInfo;
import com.news.web.service.UserService;
import com.news.web.vo.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("jump2login")
    public String jump2login() {
        return "user/login";
    }

    /**
     * 新用户注册
     *
     * @param form
     * @param bir  传入的生日日期
     * @return
     * @throws ParseException
     */
    @PostMapping("save")
    public String save(UserSaveForm form, String bir) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        form.setBirthday(sdf.parse(bir));
        userService.save(form);

        return "redirect:/user/jump2login";
    }

    /**
     * 验证登录用户账号密码
     * produces = {"application/json;charset=UTF-8"} 设定返回json值有关配置
     *
     * @param form
     * @param session
     * @return
     */
    @PostMapping(value = "login", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResultVO login(@RequestBody UserLoginForm form, HttpSession session) {

        UserInfo userInfo = userService.checkUserLogin(form);

        if (userInfo != null) {
            session.setAttribute("existUser", userInfo);

            return new LoginResultVO(true, "success");
        } else {
            return new LoginResultVO(false, "用户名或密码错误！");
        }
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("jump2register")
    public String jump2register() {
        return "user/register";
    }


    /**
     * 注销用户
     *
     * @param session
     * @return
     */
    @GetMapping("quit")
    public String quit(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/article/index";
    }
}
