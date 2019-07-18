package life.zm.damdemo.damdemo.controller;

import life.zm.damdemo.damdemo.Service.UserService;
import life.zm.damdemo.damdemo.model.User;
import life.zm.damdemo.damdemo.result.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*
    * 注册提交：1验证注册 2 发送邮箱 3验证失败重定向到注册页面
    * 注册页获取，根据account对象为依据判断是否注册页获取请求
    * */
    @RequestMapping("/regist")
    public String accountsRegister(User account, ModelMap modelMap){
        if(account==null || account.getName()==null){
            return "regist111";
        }
        //用户验证
        ResultMsg resultMsg = UserHelper.validate(account);
        if(resultMsg.isSuccess()&& userService.addAccount(account)){
            return "login";
        }else{
            return "redirect:regist?"+resultMsg.asUrlParams();
        }
    }
    @RequestMapping("/verify")
    public String verify(String key){
        boolean result = userService.enable(key);
        if(result){
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        }else{
            return "redirect:/rigist?"+ResultMsg.errorMsg("激活失败，请确认链接是否过期");
        }

    }
}
