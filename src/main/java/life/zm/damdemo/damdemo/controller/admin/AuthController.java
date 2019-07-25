package life.zm.damdemo.damdemo.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import life.zm.damdemo.damdemo.Service.UserService;
import life.zm.damdemo.damdemo.constant.LogActions;
import life.zm.damdemo.damdemo.constant.WebConst;
import life.zm.damdemo.damdemo.controller.BaseController;
import life.zm.damdemo.damdemo.model.UserDomain;
import life.zm.damdemo.damdemo.utils.APIResponse;
import life.zm.damdemo.damdemo.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api("登录相关接口")
@Controller
@RequestMapping("/admin")
public class AuthController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;



    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String userLogin(@RequestParam(name = "username")
                                        String username,
                            @RequestParam(name = "password")
            String password, HttpServletRequest request){

        UserDomain existUserDomain = userService.login(username,password);
        if(existUserDomain != null){
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, existUserDomain);
            return "redirect:/admin/index";
        }else {
            return "redirect:/loginanjian";
        }
    }


    @RequestMapping(value = "/logout")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 移除session
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        // 设置cookie值和时间为空
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            // 跳转到登录页面
            response.sendRedirect("/loginanjian");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("退出失败",e);
        }
    }



}
