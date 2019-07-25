package life.zm.damdemo.damdemo.controller;

import life.zm.damdemo.damdemo.Service.UserService;

import life.zm.damdemo.damdemo.model.UserDomain;
import life.zm.damdemo.damdemo.utils.TaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FirstPageController {

    @Autowired
    private UserService userService;

    //默认首页localhost:8887
    @GetMapping("/")
    public String firstPage(){
        return "firstpage";
    }

    @GetMapping("/loginanjian")
    public String login(){
        return "admin/login";
    }
    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    //通过登录名看是否存在
    //ajax校验。看是否存在该用户名
    @RequestMapping(value = "/userCheckCode",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> userCheckCode(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        String username = request.getParameter("username");
        UserDomain u = userService.findUserByCode(username);
        if(u != null){
            map.put("result","no");
        }else{
            map.put("result","yes");
        }
        return  map;
    }
    //用户注册
    @RequestMapping(value = "/userRegist",method = RequestMethod.POST)
    public String userRegist(UserDomain userDomain1){
        UserDomain userDomain = new UserDomain();
        userDomain.setUsername(userDomain1.getUsername());
        //密码加密
        userDomain.setPassword(TaleUtils.MD5encode(userDomain1.getUsername()+userDomain1.getPassword()));
        userDomain.setScreenName(userDomain1.getScreenName());
        userDomain.setEmail(userDomain1.getEmail());
        userDomain.setCreated(new Date(System.currentTimeMillis()));
        userService.addUser(userDomain);
        return "redirect:/loginanjian";
    }
    //check email is normal？验证邮箱是否合法
    @RequestMapping(value = "/userCheckEmail",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> userCheckEmail(@RequestParam String email){
        Map<String,Object> mapEmail = new HashMap<>();
        boolean b = TaleUtils.isEmail(email);
        if(b){
            mapEmail.put("result","yes");
        }else{
            mapEmail.put("result","no");
        }
       return mapEmail;
    }

}
