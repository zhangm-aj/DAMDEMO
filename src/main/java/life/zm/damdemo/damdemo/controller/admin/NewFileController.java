package life.zm.damdemo.damdemo.controller.admin;
import life.zm.damdemo.damdemo.Service.NewFileService;
import life.zm.damdemo.damdemo.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("file/")
public class NewFileController {
    @Autowired
    NewFileService fileService;
    @RequestMapping("")
    public String getAllFile(Integer folderId, Integer projectId, HttpServletRequest request){
        List<File> file = fileService.getAllFile(folderId,projectId);
        request.setAttribute("file",file);
        return "admin/attach";
    }
    @RequestMapping("createFolder")
    @ResponseBody
    public String createFolder(String fileName,int folderId,int projectId){
        fileService.createFolder(fileName,folderId,projectId);
        return "success";
    }
    @RequestMapping("renameFolder")
    @ResponseBody
    public String renameFolder(String fileName,int fileId){
        fileService.updateFolderName(fileName,fileId);
        return "success";
    }
}

