package com.news.web.controller.user;

import com.news.web.form.ReplySaveForm;
import com.news.web.model.UserInfo;
import com.news.web.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Paul Lee
 */
@Controller
@RequestMapping("reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 保存回复消息
     *
     * @param form
     * @param session
     * @return
     */
    @PostMapping("save")
    public String save(ReplySaveForm form, HttpSession session) {

        //从session中获得用户对象
        UserInfo userInfo = (UserInfo) session.getAttribute("existUser");

        //存入用户名
        form.setAuthor(userInfo.getUsername());
        //存入用户id
        form.setAuthorId(userInfo.getUserId());

        replyService.save(form);

        //重新跳转至此文章
        return "redirect:/article/" + form.getArticleId() + "/detail";
    }
}
