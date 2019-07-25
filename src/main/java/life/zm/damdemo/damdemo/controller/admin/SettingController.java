package life.zm.damdemo.damdemo.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.zm.damdemo.damdemo.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("系统设置管理")
@Controller
@RequestMapping("admin/setting")
public class SettingController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);


    @ApiOperation("进入设置页")
    @GetMapping(value = "")
    public String index() {

        return "admin/setting";
    }


}
