package life.zm.damdemo.damdemo.controller.admin;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import life.zm.damdemo.damdemo.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("友情链接管理")
@Controller
@RequestMapping("/admin/links")
public class LinksController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);



    @ApiOperation("友链页面")
    @GetMapping(value = "")
    public String index() {

        return "admin/links";
    }



}
