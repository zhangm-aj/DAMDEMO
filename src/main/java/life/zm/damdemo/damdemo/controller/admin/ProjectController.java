package life.zm.damdemo.damdemo.controller.admin;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import life.zm.damdemo.damdemo.Service.ProjectService;
import life.zm.damdemo.damdemo.controller.BaseController;
import life.zm.damdemo.damdemo.dto.cond.ProjectCond;
import life.zm.damdemo.damdemo.model.ProjectDomain;
import life.zm.damdemo.damdemo.utils.APIResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Api("项目管理")
@Controller
@RequestMapping("admin/project")
public class ProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @ApiOperation("文件管理首页")
    @GetMapping(value = "")
    public String index(
            HttpServletRequest request,
            @ApiParam(name = "page", value = "页数", required = false)
            @RequestParam(name = "page", required = false, defaultValue = "1")
                    int page,
            @ApiParam(name = "limit", value = "每页数量", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "9")
                    int limit
    ) {
        ProjectCond projectCond = new ProjectCond();

        PageInfo<ProjectDomain> projects = projectService.getProjectsByCond(projectCond, page, limit);

        request.setAttribute("projects",projects);

        return "admin/project111";
    }

    //单击新建项目
    @GetMapping("/newpro")
    public String newPro(){

        return "admin/newpro";
    }
    @GetMapping("/rename/{pid}")
    public String reName( @ApiParam(name = "pid", value = "项目编号", required = true)
                              @PathVariable
                                      Integer pid,
                          HttpServletRequest request,
                          HttpSession session
    ){
       session.setAttribute("pid",pid);
       request.setAttribute("active","project");
        return "admin/rename";
    }



    //单击保存项目
    @PostMapping(value = "/newpro")
    @ResponseBody
    public APIResponse savePro(
            @RequestParam String pname

    ) {

        if (StringUtils.isNotBlank(pname)) {
            ProjectDomain projectDomain = new ProjectDomain();
            projectDomain.setPname(pname);

            ProjectDomain existProject = projectService.findProjectByPname(projectDomain);

            if(existProject != null){

                return APIResponse.fail("该项目名已存在");
            }else {

                projectService.saveProject(projectDomain);
                return APIResponse.success();
            }
    }
        return APIResponse.success();
    }


    @ApiOperation("文章编辑页")
    @GetMapping(value = "/{pid}")
    public String editArticle(
            @ApiParam(name = "pid", value = "项目编号", required = true)
            @PathVariable
                    Integer pid,
            HttpSession session
    ) {
        ProjectDomain projectDomain = projectService.getProjectsByPid(pid);
        session.setAttribute("projectDomain",projectDomain);
        session.setAttribute("pid",pid);
        session.setAttribute("folderId",0);
        return "/admin/bimface";
    }
    @ApiOperation("删除文章")
    @PostMapping("/delete")
    @ResponseBody
    public APIResponse deleteProject(
            @ApiParam(name = "pid", value = "文章ID", required = true)
            @RequestParam(name = "pid", required = true)
                    Integer pid,
            HttpServletRequest request
    ) {
        // 删除文章
        projectService.deleteArticleById(pid);
        // 写入日志

        return APIResponse.success();
    }


    @ApiOperation("编辑保存文章")
    @PostMapping("/modify")
    @ResponseBody
    public APIResponse modifyProject(
            @RequestParam(name = "param", required = true)String pname,
            @RequestParam(name = "param1", required = true)Integer pid
            ){

        if (StringUtils.isNotBlank(pname)) {
            ProjectDomain projectDomain = new ProjectDomain();
            projectDomain.setPname(pname);
            projectDomain.setPid(pid);
            ProjectDomain existProject = projectService.findProjectByPname(projectDomain);

            if(existProject != null && pid==existProject.getPid()){
                return APIResponse.fail("没有做任何修改");
            }else if(existProject != null && pid!=existProject.getPid()){
                return APIResponse.fail("该项目名已存在");
            }else{
                projectService.updateProject(projectDomain);
                return APIResponse.success();
            }
        }
        return APIResponse.success();

    }

}
