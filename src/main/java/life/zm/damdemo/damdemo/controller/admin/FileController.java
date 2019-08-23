package life.zm.damdemo.damdemo.controller.admin;
import com.bimface.api.bean.response.FileTranslateBean;
import com.bimface.api.bean.response.databagDerivative.DatabagDerivativeBean;
import com.bimface.exception.BimfaceException;
import com.bimface.file.bean.FileBean;
import com.bimface.file.bean.UploadPolicyBean;
import com.bimface.sdk.BimfaceClient;
import com.bimface.sdk.bean.request.OfflineDatabagRequest;
import life.zm.damdemo.damdemo.Service.FileService;
import life.zm.damdemo.damdemo.model.ExampleQuickFile;
import life.zm.damdemo.damdemo.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dup, 2017-12-20
 */
@RestController
@RequestMapping("/admin/project/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private BimfaceClient bimfaceClient;

    @GetMapping("/list")
    public List<ExampleQuickFile> getFileList(@RequestParam(required = false) String suffix,
                                              @RequestParam(required = false) String translateStatus,
                                              @RequestParam(required = false) int projectId,
                                              @RequestParam(required = false) Long folderId,HttpSession session) {
        session.setAttribute("folderId",folderId);
        session.setAttribute("name",fileService.getFile(folderId));
        return fileService.getFileList(suffix, translateStatus,projectId,folderId);
    }

    @GetMapping("/policy")
    public UploadPolicyBean getOssPolicy(@RequestParam String fileName) throws BimfaceException {
        return bimfaceClient.getPolicy(fileName);
    }

    @GetMapping("/download_url")
    public String getDownloadUrl(@RequestParam Long fileId) throws BimfaceException {
        return bimfaceClient.getDownloadUrl(fileId);
    }

    @PostMapping("{projectId}/{folderId}")
    public ExampleQuickFile storeFile( FileBean fileBean,@PathVariable int projectId,@PathVariable Long folderId ) throws ParseException {
        String name = fileBean.getName();
        List<ExampleQuickFile> list = fileService.findFileByName(name);

        double version = 0.0;
        if(list != null){
            for(int i = 0;i<list.size();i++){

                version = Math.max(version,list.get(i).getVersion());

            }
        }

        return fileService.store(fileBean,projectId,folderId,version+1);
    }

    @GetMapping("/view_token")
    public String getViewToken(@RequestParam Long fileId) throws BimfaceException {
        return bimfaceClient.getViewTokenByFileId(fileId);
    }

    @PutMapping("translate")
    public FileTranslateBean translate(@RequestParam Long fileId) throws BimfaceException {
        return fileService.translate(fileId);
    }

    @PutMapping("translate/list")
    public List<FileTranslateBean> translateFiles(@RequestParam Long[] fileIds) throws BimfaceException {
        List<FileTranslateBean> translateBeans = new ArrayList<>();
        for (Long fileId : fileIds) {
            translateBeans.add(translate(fileId));
        }
        return translateBeans;
    }

    @PutMapping("/databag")
    public DatabagDerivativeBean databag(@RequestParam Long fileId) throws BimfaceException {
        return fileService.databag(fileId);
    }

    @PutMapping("/databag/list")
    public List<DatabagDerivativeBean> databags(@RequestParam Long[] fileIds) throws BimfaceException {
        List<DatabagDerivativeBean> offlineDatabagBeans = new ArrayList<>();
        for (Long fileId : fileIds) {
            offlineDatabagBeans.add(databag(fileId));
        }
        return offlineDatabagBeans;
    }

    @GetMapping("/databag_url")
    public String databagUrl(@RequestParam Long fileId) throws BimfaceException {
        OfflineDatabagRequest request = new OfflineDatabagRequest();
        request.setFileId(fileId);
        return bimfaceClient.getOfflineDatabagUrl(request);
    }

    @DeleteMapping("")
    public String delete(@RequestParam Long fileId) throws BimfaceException {
        fileService.delete(fileId);
        return "success";
    }

    @DeleteMapping("/list")
    public String deletes(@RequestParam Long[] fileIds) throws BimfaceException {
        for (Long fileId:fileIds){
            fileService.delete(fileId);
        }
        return "success";
    }
    @RequestMapping(value="/newFolder", method=RequestMethod.GET)
    public ModelAndView newFolder(Long folderId, int projectId, HttpSession session){
        session.setAttribute("folderId",folderId);
        session.setAttribute("projectId",projectId);
        ModelAndView mv = new ModelAndView("admin/newfolder");
        return mv;
    }
   @PostMapping("saveFolder")
    public APIResponse saveFolder(String fileName,HttpSession session){
        fileService.createFolder(fileName,(Long)session.getAttribute("folderId"),(Integer)session.getAttribute("projectId"));
        return APIResponse.success();
    }
    @GetMapping("preview")
    public ModelAndView getPreview(Long fileId){
        ModelAndView mv = new ModelAndView("admin/preview");
        return mv;
    }

}
