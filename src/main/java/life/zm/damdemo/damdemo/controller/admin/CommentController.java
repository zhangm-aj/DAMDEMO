package life.zm.damdemo.damdemo.controller.admin;

import com.github.pagehelper.PageInfo;

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

@Api("评论相关接口")
@Controller
@RequestMapping("/admin/comments")
public class CommentController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);


    @ApiOperation("进入评论列表页")
    @GetMapping(value = "")
    public String index(

    ) {

        return "admin/comment_list";
    }



}
