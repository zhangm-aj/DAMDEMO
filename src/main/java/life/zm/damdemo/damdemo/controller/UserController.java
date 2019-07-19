package life.zm.damdemo.damdemo.controller;

import life.zm.damdemo.damdemo.Service.UserService;
import life.zm.damdemo.damdemo.mapper.UserMapper;
import life.zm.damdemo.damdemo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private User user = new User();
    //通过登录名看是否存在
    @RequestMapping(value = "/userCheckCode",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> userCheckCode(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        String user_code = request.getParameter("user_code");
        User u = userService.findUserByCode(user_code);
        if(u != null){
            map.put("result","no");
        }else{
            map.put("result","yes");
        }
        return  map;
            }
    @RequestMapping(value = "/userRegist",method = RequestMethod.POST)
     public String userRegist(User user1){
        user.setUser_code(user1.getUser_code());
        user.setUser_password(user1.getUser_password());
        user.setUser_name(user1.getUser_name());
        user.setUser_enable(1);
        user.setUser_createTime(new Date(System.currentTimeMillis()));
        userService.addUser(user);
        return "redirect:/login";
     }
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String userLogin(User user2,HttpServletRequest request){
        User existUser = userService.userLogin(user2);
        if(existUser != null){
            request.getSession().setAttribute("user",existUser);
            return "redirect:/firstpage";
        }else {
            return "redirect:/login";
        }
    }
    @GetMapping("/firstpage")
    public String firstPage(){
        return "firstpage";
    }
    @RequestMapping(value = "/userQuit",method = RequestMethod.POST)
    public String userQuit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/login";
    }
    }


