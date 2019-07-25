package life.zm.damdemo.damdemo.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import life.zm.damdemo.damdemo.controller.BaseController;

import life.zm.damdemo.damdemo.utils.TaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api("文件管理")
@Controller
@RequestMapping("admin/attach")
public class AttachController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    public static final String CLASSPATH = TaleUtils.getUploadFilePath();






    @ApiOperation("文件管理首页")
    @GetMapping(value = "")
    public String index(

    ) {

        return "admin/attach";
    }





}
