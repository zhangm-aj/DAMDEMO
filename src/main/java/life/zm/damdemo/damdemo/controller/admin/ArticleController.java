package life.zm.damdemo.damdemo.controller.admin;



import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import life.zm.damdemo.damdemo.Service.ContentService;
import life.zm.damdemo.damdemo.controller.BaseController;
import life.zm.damdemo.damdemo.dto.cond.ContentCond;
import life.zm.damdemo.damdemo.dto.cond.MetaCond;
import life.zm.damdemo.damdemo.model.ContentDomain;
import life.zm.damdemo.damdemo.model.UserDomain;
import life.zm.damdemo.damdemo.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api("文章管理")
@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);



    @Autowired
    private ContentService contentService;


    @ApiOperation("文章页")
    @GetMapping(value = "")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "15")
                    int limit
    ) {
        PageInfo<ContentDomain> articles = contentService.getArticlesByCond(new ContentCond(), page, limit);
        request.setAttribute("articles",articles);
        return "admin/article_list";
    }

    @ApiOperation("发布新文章页")
    @GetMapping(value = "/publish")
    public String newArticle(HttpServletRequest request) {
        return "admin/article_edit";
    }

    @ApiOperation("文章编辑页")
    @GetMapping(value = "/{cid}")
    public String editArticle(
            @ApiParam(name = "cid", value = "文章编号", required = true)
            @PathVariable
                    Integer cid,
            HttpServletRequest request
    ) {
        ContentDomain content = contentService.getArticleById(cid);
        request.setAttribute("contents", content);
        MetaCond metaCond = new MetaCond();
        request.setAttribute("active", "article");
        return "admin/article_edit";
    }

    @ApiOperation("编辑保存文章")
    @PostMapping("/modify")
    @ResponseBody
    public APIResponse modifyArticle(
            HttpServletRequest request,
            @ApiParam(name = "cid", value = "文章主键", required = true)
            @RequestParam(name = "cid", required = true)
                    Integer cid,
            @ApiParam(name = "title", value = "标题", required = true)
            @RequestParam(name = "title", required = true)
                    String title,

            @ApiParam(name = "content", value = "内容", required = true)
            @RequestParam(name = "content", required = true)
                    String content,

            @ApiParam(name = "status", value = "文章状态", required = true)
            @RequestParam(name = "status", required = true)
                    String status,
            @ApiParam(name = "tags", value = "标签", required = false)
            @RequestParam(name = "tags", required = false)
                    String tags
    ) {
        ContentDomain contentDomain = new ContentDomain();
        contentDomain.setTitle(title);

        contentDomain.setContent(content);

        contentDomain.setStatus(status);
        contentDomain.setTags(tags);

       // contentDomain.setAllowComment(allowComment ? 1: 0);
        contentService.updateArticleById(contentDomain);

        return APIResponse.success();
    }


    @ApiOperation("发布新文章")
    @PostMapping(value = "/publish")
    @ResponseBody
    public APIResponse publishArticle(
            @ApiParam(name = "title", value = "标题", required = true)
            @RequestParam(name = "title", required = true)
                    String title,
            @ApiParam(name = "content", value = "内容", required = true)
            @RequestParam(name = "content", required = true)
                    String content,

            @ApiParam(name = "status", value = "文章状态", required = true)
            @RequestParam(name = "status", required = true)
                    String status,

            @ApiParam(name = "tags", value = "文章标签", required = false)
            @RequestParam(name = "tags", required = false)
                    String tags,
            HttpServletRequest request
    ) {
        ContentDomain contentDomain = new ContentDomain();
        contentDomain.setTitle(title);
        contentDomain.setContent(content);
        contentDomain.setStatus(status);
        contentDomain.setTags(tags);

        //contentDomain.setHits(1);
        //contentDomain.setCommentsNum(0);
        // 只允许博客文章有分类，防止作品被收入分类
        UserDomain users = this.user(request);
        contentDomain.setAuthorId(users.getUid());

        //contentDomain.setAllowComment(allowComment ? 1 : 0);

        // 添加文章
        contentService.addArticle(contentDomain);

        return APIResponse.success();
    }

    @ApiOperation("删除文章")
    @PostMapping("/delete")
    @ResponseBody
    public APIResponse deleteArticle(
            @ApiParam(name = "cid", value = "文章ID", required = true)
            @RequestParam(name = "cid", required = true)
                    Integer cid,
            HttpServletRequest request
    ) {
        // 删除文章
        contentService.deleteArticleById(cid);
        // 写入日志
        //logService.addLog(LogActions.DEL_ARTICLE.getAction(), cid+"",request.getRemoteAddr(),this.getUid(request));
        return APIResponse.success();
    }


}
