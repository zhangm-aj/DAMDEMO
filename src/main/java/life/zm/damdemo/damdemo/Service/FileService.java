package life.zm.damdemo.damdemo.Service;

import com.bimface.api.bean.response.FileTranslateBean;
import com.bimface.api.bean.response.databagDerivative.DatabagDerivativeBean;
import com.bimface.exception.BimfaceException;
import com.bimface.file.bean.FileBean;
import life.zm.damdemo.damdemo.model.ExampleQuickFile;

import java.text.ParseException;
import java.util.List;

public interface FileService {
    /**
     * 查询列表
     *
     * @return
     * @param suffix
     * @param translateStatus
     */
    List<ExampleQuickFile> getFileList(String suffix, String translateStatus,int projectId,Long folderId);

    /**
     * file入库
     *
     * @param fileBean
     * @return
     * @throws ParseException
     */
    ExampleQuickFile store(FileBean fileBean,int projectId,Long folderId,double version) throws ParseException;

    /**
     * 发起转换
     *
     * @param fileId
     * @return
     * @throws BimfaceException
     */
    FileTranslateBean translate(Long fileId) throws BimfaceException;

    /**
     * 生成离线数据包
     *
     * @param fileId
     * @return
     * @throws BimfaceException
     */
    DatabagDerivativeBean databag(Long fileId) throws BimfaceException;

    /**
     * delete
     *
     * @param fileId
     */
    void delete(Long fileId);

    /**
     * 新增文件夹，需指定父文件夹id和项目id
     * @param fileName
     * @param folderId
     * @param projectId
     */
    void createFolder(String fileName, Long folderId, int projectId);

    String getFile(Long id);

    List<ExampleQuickFile> findFileByName(String name);
}
